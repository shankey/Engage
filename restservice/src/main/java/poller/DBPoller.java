package poller;

import hibernate.bean.User;
import hibernate.dao.UserDAO;

import java.util.List;

import org.apache.log4j.Logger;

import com.engage.common.Utility;

public class DBPoller implements Runnable {
	
	private static DBPoller dbpoller = new DBPoller(); 
	private static Logger logger = Logger.getLogger(DBPoller.class);
	public boolean isPolling = false;
	
	
	public void pollDB() throws InterruptedException{
		isPolling = true;
		UserDAO dao = UserDAO.getUserDao();
		List<User> list;
		while(!Thread.currentThread().isInterrupted()){
			logger.info("inside while of pollDB "+ Thread.currentThread().isInterrupted());
			// if the queue is not empty then try to poll DB and fill queue
			if(Queues.getUrlQueue().isEmpty()){
				logger.info("pollDB - empty queue");
				
				//poll for a few items
				User user = new User();
				user.setStatus(0);
				list = dao.zeroHandles(user);
				logger.info("polled DB list of " + list.size());
				
				for(User li : list){
					logger.info(li.getHandle());
				}
				//Insert them in a queue
				//Adding all items to a queue.
				// Use an external queue when moving to a distributed system.
				//This currently works on a single box.
				if(list!=null && !list.isEmpty()){
					Queues.getUrlQueue().addAll(list);
				}else{
					//if list is empty or null, it means
					// all the rows were upto date in the DB
					// and there are no more rows to be processed
					
					//You can put a delay here TOO but 
					// i dont see a need as were polling once only in one minute.
					
				}
				
			}
			Thread.sleep(Utility.SECOND*60); // 1 minute wait
		}
	}

	@Override
	public void run() {
		try {
			if(!isPolling){
				dbpoller.pollDB();
			}
		} catch (InterruptedException e) {
			isPolling=false;
			logger.error("unable to start DBPoller", e);
		}
		
	}
	


}
