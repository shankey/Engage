package poller;

import hibernate.bean.Url;
import hibernate.util.UrlDAO;

import org.apache.log4j.Logger;


public class QueuePoller implements Runnable {
	
	private static QueuePoller queuepoller = new QueuePoller(); 
	private static Logger logger = Logger.getLogger(QueuePoller.class);
	public boolean isPolling = false;
	
public void pollQueue() throws InterruptedException{
	
		
		UrlDAO urlDao = UrlDAO.getUrlDao();
		isPolling = true;
		
		
		Url url;
		while(!Thread.currentThread().isInterrupted()){
			logger.info("inside while of pollQueuue "+Thread.currentThread().isInterrupted());
			// if the queue is not empty then try to poll DB and fill queue
			while(!Queues.getUrlQueue().isEmpty()){
				logger.info("Queue was not empty");
				url = Queues.getUrlQueue().poll();
				logger.info(url);
				
				
				
				
					
			}
			//Thread.sleep(Utility.SECOND*60); // 1 minute wait
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
