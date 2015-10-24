package poller;

import hibernate.bean.Comments;
import hibernate.bean.Likes;
import hibernate.bean.Post;
import hibernate.bean.QueueObject;
import hibernate.bean.Url;
import hibernate.bean.User;
import hibernate.util.UrlDAO;

import org.apache.log4j.Logger;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.bao.CommentsBAO;
import com.engage.bao.LikesBAO;
import com.engage.bao.TimelineBAO;
import com.engage.common.Utility;


public class QueuePoller implements Runnable {
	
	private static QueuePoller queuepoller = new QueuePoller(); 
	private static Logger logger = Logger.getLogger(QueuePoller.class);
	public boolean isPolling = false;
	
public void pollQueue() throws InterruptedException{
	
		
		
		isPolling = true;
		
		
		Url url;
		while(!Thread.currentThread().isInterrupted()){
			logger.info("inside while of pollQueuue "+Thread.currentThread().isInterrupted());
			// if the queue is not empty then try to poll DB and fill queue
			while(!Queues.getQueue().isEmpty()){
				logger.info("Queue was not empty");
				QueueObject queueObject = Queues.getQueue().poll();
				
				switch (queueObject.getType()){
				
					case 1 :
						User user = (User)queueObject.getObj();
						new TimelineBAO().getTimelineData(user.getUserId());
						break;
					case 2 :
						Post postLike = (Post)queueObject.getObj();
						new LikesBAO().getLikesData(postLike.getPostId(), InstaAPIEndPoints.ACCESS_TOKEN);
						break;
					case 3 :
						Post postComment = (Post)queueObject.getObj();
						new CommentsBAO().getCommentsData(postComment.getPostId(), InstaAPIEndPoints.ACCESS_TOKEN);
						break;
				
				}
				
				
				logger.info(queueObject);
				
			}
			Thread.sleep(Utility.SECOND*1); // 1 minute wait
		}
	}

	
	@Override
	public void run() {
		try {
			if(!isPolling){
				queuepoller.pollQueue();
			}
		} catch (InterruptedException e) {
			isPolling = false;
			logger.error("Unable to launch queuepoller or stopping queue poller", e);
		}
		
	}
	

}
