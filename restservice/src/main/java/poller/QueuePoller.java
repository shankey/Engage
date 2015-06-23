package poller;

import java.sql.Timestamp;
import java.util.Date;

import com.jaunt.JauntException;

import Scrapping.InputConverter;
import Scrapping.Scrapper;
import bean.ScrapInput;
import hibernate.bean.Url;
import hibernate.util.UrlDAO;
import Common.Utility;

public class QueuePoller {
	
public void pollDB() throws InterruptedException, JauntException{
		
		UrlDAO dao = new UrlDAO();
		Scrapper scrapper = new Scrapper();
	
		while(true){
			
			// if the queue is not empty then try to poll DB and fill queue
			if(!Queues.getUrlQueue().isEmpty()){
				
				Url url = Queues.getUrlQueue().poll();
				
				//put parsing logic and saving new skudetails row here
				ScrapInput scrapInput = new ScrapInput(url.getUrl(),
						InputConverter.convert(url.getTitlePattern()), 
						InputConverter.convert(url.getSellingPricePattern()), 
						InputConverter.convert(url.getListPricePattern()), 
						InputConverter.convert(url.getAvailabilityPattern()));
				
				scrapper.scrape(scrapInput);
				
				url.setLastUpdated(new Timestamp(new Date().getTime()));
				dao.update(url);
						
			}
			Thread.sleep(Utility.SECOND*60); // 1 minute wait
		}
	}

}
