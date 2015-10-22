package com.engage.bao;

import hibernate.bean.Post;
import hibernate.bean.User;
import hibernate.dao.PostDAO;
import hibernate.dao.UserDAO;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

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
			
			String response = InstaAPIWrapper.callTimeLine(InstaAPIEndPoints.TIMELINE_FEED.replaceAll("\\{user-id\\}", userId), InstaAPIEndPoints.ACCESS_TOKEN, 
					Utility.get30DayOldTimeStamp(), maxId);
			
			if(response==null){
				break;
			}
			
			JSONObject object=null;
			try {
				object = Utility.convertToJSON(response);
			} catch (ParseException e) {
				logger.error("Unable to parse JSON", e);
			}
			
			
			JSONArray dataJson = (JSONArray)object.get("data");
			//System.out.println(dataJson);
			
			for(int i=0; i<dataJson.size(); i++){
				JSONObject dataJsonObj = (JSONObject)dataJson.get(i);
				
				
				User user = new User();
				user.setUserId(userId);
				User existingUser = udao.getUserDetails(user);
				if(existingUser!=null){
					user.setId(existingUser.getId());
				}
				
				udao.update(user);
				
				Post post = new Post();
				post.setOwnerId(userId);
				System.out.println((String)dataJsonObj.get("id"));
				post.setPostId((String)dataJsonObj.get("id"));
				System.out.println("Querying with "+post);
				Post exisitngPost = dao.getPostDetails(post);
				if(exisitngPost!=null){
					System.out.println("Found existingPost = "+exisitngPost);
					post.setId(exisitngPost.getId());
				}else{
					System.out.println("No existingPost Found");
				}
				dao.update(post);
			}
			
			
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
