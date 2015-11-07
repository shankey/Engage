package com.engage.bao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import hibernate.bean.Post;
import hibernate.bean.QueueObject;
import hibernate.bean.User;
import hibernate.dao.PostDAO;
import hibernate.dao.UserDAO;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import poller.Queues;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.api.wrapper.InstaAPIWrapper;
import com.engage.common.Utility;

public class TimelineBAO {
	
	private static Logger logger = Logger.getLogger(TimelineBAO.class);
	PostDAO dao = PostDAO.getPostDao();
	UserDAO udao = UserDAO.getUserDao();
	
	private Boolean checkStatusCodes(String errorCode, User user){
		if(errorCode.equals("429")){
			
			User existingUser = udao.getUserDetails(user);
			existingUser.setStatus(0);
			udao.update(existingUser);
			return false;
		}
		
		if(!errorCode.equals("200")){
			User existingUser = udao.getUserDetails(user);
			existingUser.setUserError(1);
			udao.update(existingUser);
			
			return false;
		}
		
		return true;
	}
	
	private void saveNumberOfLikesAndCommentsOnPost(JSONObject dataJsonObj, Post post){
		
		//Stoing likes count
		JSONObject likesJson = (JSONObject)dataJsonObj.get("likes");
		if(likesJson!=null){
			Long likesCount = (Long)likesJson.get("count");
			if(likesCount!=null){
				post.setLikes(likesCount);
			}
			else {
				post.setLikes(0l);
			}
		}
		

		//Storing the Comments count
		JSONObject commentsJson = (JSONObject)dataJsonObj.get("comments");
		
		if(commentsJson!=null){
			Long commentsCount = (Long)commentsJson.get("count");
			if(commentsCount!=null){
				post.setComments(commentsCount);
			}
			else {
				post.setComments(0l);
			}
		}
	}
	
	public void getTimelineData(String userId, Boolean isImmediate) throws Exception{
		
		String maxId = null;
		
		while(true){
			
			String response = InstaAPIWrapper.callTimeLine(InstaAPIEndPoints.TIMELINE_FEED.replaceAll("\\{user-id\\}", userId),
					InstaAPIEndPoints.getAccessToken(), Utility.get30DayOldTimeStamp(), maxId);
			
			
			if(response==null){
				break;
			}
			
			JSONObject object=null;
			try {
				object = Utility.convertToJSON(response);
			} catch (ParseException e) {
				logger.error("Unable to parse JSON", e);
			}
			
			JSONObject metaObject =  (JSONObject)object.get("meta");
			String errorCode = (String)metaObject.get("code").toString();

			User user = new User();
			user.setUserId(userId);
			
			if(!checkStatusCodes(errorCode, user)){
				return;
			}
			
			//save user personal data
			new UserInfoBAO().getUserData(userId);
			
			JSONArray dataJson = (JSONArray)object.get("data");
			//System.out.println(dataJson);
			List<Post> batchedSave = new ArrayList<Post>();
			for(int i=0; i<dataJson.size(); i++){
				JSONObject dataJsonObj = (JSONObject)dataJson.get(i);
				
				//Amits code to save comments and likes
				Post post = new Post();
				
				post.setOwnerId(userId);
				post.setPostId((String)dataJsonObj.get("id"));
				
				
				post.setPostCreated(Timestamp.valueOf((String)dataJsonObj.get("created_time")));
				saveNumberOfLikesAndCommentsOnPost(dataJsonObj, post);
				
				System.out.println(post.toString());
				Post exisitngPost = dao.getPostDetails(post);
				
				//Old Post
				if(exisitngPost!=null){
					
					//If Likes have changed
					if(exisitngPost.getLikes()!=post.getLikes()){
						exisitngPost.setLikes(post.getLikes());
						if(isImmediate){
							//trigger comments and likes
							new LikesBAO().getLikesData(post.getPostId(), InstaAPIEndPoints.getAccessToken());
						}
					}
					
					//If comments have changed
					if(exisitngPost.getComments()!=post.getComments()){
						if(exisitngPost.getComments()!=post.getComments()){
							exisitngPost.setComments(post.getComments());
							if(isImmediate){
								//trigger comments and likes
								new CommentsBAO().getCommentsData(post.getPostId(), InstaAPIEndPoints.getAccessToken());
							}
						}	
					}
					if(!isImmediate){
						exisitngPost.setStatus(0);
					}
					
					batchedSave.add(exisitngPost);
					
				//New Post
				}else{
					if(isImmediate){
						new LikesBAO().getLikesData(post.getPostId(), InstaAPIEndPoints.getAccessToken());
						new CommentsBAO().getCommentsData(post.getPostId(), InstaAPIEndPoints.getAccessToken());
					}else{
						post.setStatus(0);
					}
					
					batchedSave.add(post);
				}	
			}
			
			
			
			dao.batchUpdate(batchedSave);
			
			JSONObject paginationJson = (JSONObject) object.get("pagination");
			
			if(paginationJson==null){
				break;
			}
			
			//System.out.println(paginationJson);
			maxId = (String)paginationJson.get("next_max_id");
			
			if(maxId==null){
				break;
			}
			
		}
		
	}

}
