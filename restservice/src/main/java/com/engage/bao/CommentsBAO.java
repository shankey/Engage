package com.engage.bao;

import java.util.ArrayList;
import java.util.List;

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
	PostDAO pdao = PostDAO.getPostDao();
	
	public void getCommentsData(String postId, String accessToken) throws Exception{
		
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
			
			JSONObject metaObject =  (JSONObject)object.get("meta");
			String errorCode = (String)metaObject.get("code");

			Post post = new Post();
			post.setPostId(postId);
			if(errorCode.equals("429")){
				
				Post existingPost = pdao.getPostDetails(post);
				existingPost.setStatus(0);
				pdao.update(existingPost);
				return;
			}
			
			if(!errorCode.equals("200")){
				Post existingPost = pdao.getPostDetails(post);
				existingPost.setErrorComment(1);
				pdao.update(existingPost);
				return;
			}
			
			
			JSONArray dataJson = (JSONArray)object.get("data");
			System.out.println(dataJson);
			
			List<Comments> commentList = new ArrayList<Comments>();
			
			for(int i=0; i<dataJson.size(); i++){
				JSONObject dataJsonObj = (JSONObject)dataJson.get(i);
				JSONObject fromJson = (JSONObject) dataJsonObj.get("from");
				String commentHandle = (String) fromJson.get("username");
				String commentId = (String) dataJsonObj.get("id");
				
				Comments comment = new Comments();
				comment.setCommentHandle(commentHandle);
				comment.setCommentId(commentId);
				comment.setPostId(postId);
				
				commentList.add(comment);
			}
			dao.batchUpdate(commentList);
	}

}
