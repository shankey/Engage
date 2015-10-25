package com.engage.bao;

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
	
	public void getTimelineData(String userId){
		
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
			
			new UserInfoBAO().getUserData(userId);
			
			JSONArray dataJson = (JSONArray)object.get("data");
			//System.out.println(dataJson);
			List<Post> batchedSave = new ArrayList<Post>();
			for(int i=0; i<dataJson.size(); i++){
				JSONObject dataJsonObj = (JSONObject)dataJson.get(i);
				
				//Amits code to save comments and likes
				Post post = new Post();
				
				//Storing the ownerId
				post.setOwnerId(userId);
				
				//Storing the postId
				System.out.println((String)dataJsonObj.get("id"));
				post.setPostId((String)dataJsonObj.get("id"));
				System.out.println("Querying with "+post);
				
				
				
				//Storing the Likes count
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
				
				System.out.println(post.toString());
				Post exisitngPost = dao.getPostDetails(post);
				if(exisitngPost!=null){
					
					exisitngPost.setLikes(post.getLikes());
					exisitngPost.setComments(post.getComments());
					batchedSave.add(exisitngPost);
					
				}else{
					post.setStatus(0);
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
			logger.info("Fetching Next Page of Posts For User "+userId);
			if(maxId==null){
				break;
			}
			
		}
		
	}

}