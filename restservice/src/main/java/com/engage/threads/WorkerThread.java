package com.engage.threads;

import org.apache.log4j.Logger;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.bao.CommentsBAO;
import com.engage.bao.LikesBAO;
import com.engage.bao.TimelineBAO;

import hibernate.bean.Post;
import hibernate.bean.QueueObject;
import hibernate.bean.User;
import hibernate.dao.PostDAO;
import hibernate.dao.UserDAO;


	public class WorkerThread implements Runnable {

	    private QueueObject object;
	    Logger logger = Logger.getLogger(WorkerThread.class);

	    public WorkerThread(QueueObject object){
	        this.object=object;
	    }

	    @Override
	    public void run() {
	    	
	    	switch (object.getType()){
			
			case 1 :
				User user = (User)object.getObj();
				try {
					new TimelineBAO().getTimelineData(user.getUserId(), false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					User existingUser = UserDAO.getUserDao().getUserDetails(user);
					existingUser.setUserError(1);
					UserDAO.getUserDao().update(existingUser);
					logger.error("Error while processing User "+user);
					
				}
				break;
			case 2 :
				Post postLike = (Post)object.getObj();
				try {
					new LikesBAO().getLikesData(postLike.getPostId(), InstaAPIEndPoints.getAccessToken());
				} catch (Exception e) {
					Post post = new Post();
					post.setPostId(postLike.getPostId());
					Post existingPost = PostDAO.getPostDao().getPostDetails(post);
					existingPost.setErrorLikes(1);
					PostDAO.getPostDao().update(existingPost);
					logger.error("Error while processing Post "+postLike);
					
				}
				break;
			case 3 :
				Post postComment = (Post)object.getObj();
				try {
					new CommentsBAO().getCommentsData(postComment.getPostId(), InstaAPIEndPoints.getAccessToken());
				} catch (Exception e) {
					Post post = new Post();
					post.setPostId(postComment.getPostId());
					Post existingPost = PostDAO.getPostDao().getPostDetails(post);
					existingPost.setErrorComment(1);
					PostDAO.getPostDao().update(existingPost);
					logger.error("Error while processing Post "+postComment);
				}
				break;
		
		}
		
	        
	    }

	    

	    @Override
	    public String toString(){
	        return this.object.toString();
	    }
	}
	
	

