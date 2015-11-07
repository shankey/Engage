package com.engage.bao;

import java.util.ArrayList;
import java.util.List;

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
	PostDAO pdao = PostDAO.getPostDao();
	
	public void getLikesData(String postId, String accessToken) throws Exception{
		
			String response = InstaAPIWrapper.call(InstaAPIEndPoints.POST_LIKES.replaceAll("\\{media-id\\}", postId), InstaAPIEndPoints.getAccessToken());
			
			
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
			String errorCode = (String)metaObject.get("code").toString();

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
				existingPost.setErrorLikes(1);
				pdao.update(existingPost);
				return;
			}
			
			
			JSONArray dataJson = (JSONArray)object.get("data");
			System.out.println(dataJson);
			
			List<Likes> likeList = new ArrayList<Likes>();
			
			for(int i=0; i<dataJson.size(); i++){
				JSONObject dataJsonObj = (JSONObject)dataJson.get(i);
				
				String likeHandle = (String) dataJsonObj.get("username");
				String likeId = (String) dataJsonObj.get("id");
				
				Likes like = new Likes();
				like.setLikeHandle(likeHandle);
				like.setLikeId(likeId);
				like.setPostId(postId);
				likeList.add(like);
				
				
			}
			dao.batchUpdate(likeList);
	}

}
