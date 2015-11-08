package com.engage.bao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.engage.common.Utility;

import hibernate.bean.Comments;
import hibernate.bean.Engagement;
import hibernate.bean.Likes;
import hibernate.dao.CommentsDAO;
import hibernate.dao.EngageDAO;
import hibernate.dao.LikesDAO;

public class EngagementBAO {
	
	LikesDAO likesDAO = LikesDAO.getLikesDao();
	CommentsDAO commentsDAO = CommentsDAO.getCommentsDao();
	EngageDAO engageDAO = EngageDAO.getEngageDAO();
	
	public void saveEngagementScores(String userId){
		
		Engagement deleteEngage = new Engagement();
		deleteEngage.setUserId(userId);
		engageDAO.batchDelete(deleteEngage);
		
		List<Engagement> li = generateEngagementScores(userId);
		
		engageDAO.batchUpdate(li);
		
	}
	
	public List<Engagement> generateEngagementScores(String userId){
		Long postCreated = Utility.get30DayOldTimeStamp();
		String sqlLike = "select * from Likes where PostId in (select PostId from Post where OwnerId='"+ userId +"' and PostCreated < " + postCreated + " )";
		List<Likes> likeList = likesDAO.getAllByNative(sqlLike);
		
		String sqlComment = "select * from Comments where PostId in (select PostId from Post where OwnerId='"+ userId +"' )";
		List<Comments> commentList = likesDAO.getAllByNative(sqlComment);
		
		Map<String, Integer> engageScoreMap = new HashMap<String, Integer>();
		Map<String, String> followerHandleIdMap = new HashMap<String, String>();
		
		for(Likes like : likeList){
			if(engageScoreMap.containsKey(like.getLikeHandle())){
				Integer currentScore = engageScoreMap.get(like.getLikeHandle());
				currentScore++;
				engageScoreMap.put(like.getLikeHandle(), currentScore);
			}else{
				engageScoreMap.put(like.getLikeHandle(), 1);

			}
		}
		
		for(Comments comment : commentList){
			if(engageScoreMap.containsKey(comment.getCommentHandle())){
				Integer currentScore = engageScoreMap.get(comment.getCommentHandle());
				currentScore++;
				engageScoreMap.put(comment.getCommentHandle(), currentScore);
			}else{
				engageScoreMap.put(comment.getCommentHandle(), 1);
			}
		}
		
		List<Engagement> engagementList = new ArrayList<Engagement>(); 
		
		for(String key : engageScoreMap.keySet()){
			Integer score = engageScoreMap.get(key);
			
			Engagement entry = new Engagement();
			entry.setEngageScore(score);
			entry.setFollowerHandle(key);
			entry.setUserId(userId);
		}
		
		return engagementList;
		
	}

}
