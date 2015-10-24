package com.engage.threads;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.bao.CommentsBAO;
import com.engage.bao.LikesBAO;
import com.engage.bao.TimelineBAO;

import hibernate.bean.Post;
import hibernate.bean.QueueObject;
import hibernate.bean.User;


	public class WorkerThread implements Runnable {

	    private QueueObject object;

	    public WorkerThread(QueueObject object){
	        this.object=object;
	    }

	    @Override
	    public void run() {
	    	
	    	switch (object.getType()){
			
			case 1 :
				User user = (User)object.getObj();
				new TimelineBAO().getTimelineData(user.getUserId());
				break;
			case 2 :
				Post postLike = (Post)object.getObj();
				new LikesBAO().getLikesData(postLike.getPostId(), InstaAPIEndPoints.ACCESS_TOKEN);
				break;
			case 3 :
				Post postComment = (Post)object.getObj();
				new CommentsBAO().getCommentsData(postComment.getPostId(), InstaAPIEndPoints.ACCESS_TOKEN);
				break;
		
		}
		
	        
	    }

	    

	    @Override
	    public String toString(){
	        return this.object.toString();
	    }
	}
	
	

