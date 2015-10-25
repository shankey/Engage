package poller;

import java.io.IOException;

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
import com.engage.common.Log;
import com.engage.common.Utility;
import com.engage.threads.ThreadPool;
import com.engage.threads.WorkerThread;


public class QueuePoller implements Runnable {
	
	private static QueuePoller queuepoller = new QueuePoller(); 
	private static Logger logger = Logger.getLogger(QueuePoller.class);
	public boolean isPolling = false;
	
public void pollQueue() throws InterruptedException, IOException{
	
		
		
		isPolling = true;
		
		
		Url url;
		while(!Thread.currentThread().isInterrupted()){
			logger.info("inside while of pollQueuue "+Thread.currentThread().isInterrupted());
			// if the queue is not empty then try to poll DB and fill queue
			while(!Queues.getQueue().isEmpty()){
				logger.info("Queue was not empty");
				QueueObject queueObject = Queues.getQueue().poll();
				
				ThreadPool.executor.execute(new WorkerThread(queueObject));
				
				logger.info(queueObject);
				Log.write(queueObject.toString() + "\n");
				
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
		} catch (InterruptedException | IOException e) {
			isPolling = false;
			logger.error("Unable to launch queuepoller or stopping queue poller", e);
		}
		
	}
	

}
