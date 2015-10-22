package com.engage.bao;

import hibernate.bean.Comments;
import hibernate.bean.Likes;
import hibernate.bean.Post;
import hibernate.bean.User;
import hibernate.dao.CommentsDAO;
import hibernate.dao.LikesDAO;
import hibernate.dao.PostDAO;
import hibernate.dao.UserDAO;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.api.wrapper.InstaAPIWrapper;
import com.engage.common.Utility;

public class LikesBAO {
	
	private static Logger logger = Logger.getLogger(LikesBAO.class);
	LikesDAO dao = LikesDAO.getLikesDao();
	
	public void getLikesData(String postId, String accessToken){
		
			String response = InstaAPIWrapper.call(InstaAPIEndPoints.POST_LIKES.replaceAll("\\{media-id\\}", postId), accessToken);
			
			
			JSONObject object=null;
			try {
				object = Utility.convertToJSON(response);
			} catch (ParseException e) {
				logger.error("Unable to parse JSON", e);
			}
			
			
			if(object==null){
				return;
			}
			
			
			JSONArray dataJson = (JSONArray)object.get("data");
			System.out.println(dataJson);
			
			for(int i=0; i<dataJson.size(); i++){
				JSONObject dataJsonObj = (JSONObject)dataJson.get(i);
				String likeHandle = (String) dataJsonObj.get("username");
				String userId = (String) dataJsonObj.get("id");
				
				Likes like = new Likes();
				like.setLikeHandle(likeHandle);
				like.setUserId(userId);
				like.setPostId(postId);
				
				dao.update(like);
			}
	}

}
