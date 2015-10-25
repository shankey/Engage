package com.engage.bao;

import hibernate.bean.Comments;
import hibernate.bean.Post;
import hibernate.bean.User;
import hibernate.dao.CommentsDAO;
import hibernate.dao.PostDAO;
import hibernate.dao.UserDAO;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.api.wrapper.InstaAPIWrapper;
import com.engage.common.Utility;

public class CommentsBAO {
	
	private static Logger logger = Logger.getLogger(CommentsBAO.class);
	CommentsDAO dao = CommentsDAO.getCommentsDao();
	
	public void getCommentsData(String postId, String accessToken){
		
			String response = InstaAPIWrapper.call(InstaAPIEndPoints.POST_COMMENTS.replaceAll("\\{media-id\\}", postId), InstaAPIEndPoints.getAccessToken());
			
			
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
				JSONObject fromJson = (JSONObject) dataJsonObj.get("from");
				String commentHandle = (String) fromJson.get("username");
				String commentId = (String) dataJsonObj.get("id");
				
				Comments comment = new Comments();
				comment.setCommentHandle(commentHandle);
				comment.setCommentId(commentId);
				comment.setPostId(postId);
				
				dao.update(comment);
			}
	}

}
