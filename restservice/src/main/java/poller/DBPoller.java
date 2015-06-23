package poller;

import java.util.List;

import hibernate.bean.Url;
import hibernate.util.UrlDAO;
import Common.Utility;

public class DBPoller {
	
	public void pollDB() throws InterruptedException{
		
		while(true){
			
			// if the queue is not empty then try to poll DB and fill queue
			if(Queues.getUrlQueue().isEmpty()){
				UrlDAO dao = new UrlDAO();
				
				//poll for a few items
				List<Url> list = dao.getOutdatedUrlDetails();
				
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

}
