package poller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;

import scrapping.InputConverter;
import scrapping.Scrapper;
import bean.ScrapInput;
import bean.ScrapOutput;
import hibernate.bean.SkuDetails;
import hibernate.bean.Url;
import hibernate.util.SkuDetailsDAO;
import hibernate.util.UrlDAO;
import Common.Utility;

public class QueuePoller implements Runnable {
	
	private static QueuePoller queuepoller = new QueuePoller(); 
	private static Logger logger = Logger.getLogger(QueuePoller.class);
	public boolean isPolling = false;
	
public void pollQueue() throws InterruptedException, IOException{
	
		
		UrlDAO urlDao = UrlDAO.getUrlDao();
		SkuDetailsDAO skuDetailsDAO = SkuDetailsDAO.getSkuDetailsDAO();
		Scrapper scrapper = new Scrapper();
		isPolling = true;
		
		
		Url url;
		ScrapInput scrapInput;
		ScrapOutput output;
		while(!Thread.currentThread().isInterrupted()){
			System.out.println("inside while of pollQueuue");
			// if the queue is not empty then try to poll DB and fill queue
			if(!Queues.getUrlQueue().isEmpty()){
				System.out.println("Queue was not empty");
				url = Queues.getUrlQueue().poll();
				System.out.println(url);
				
				//put parsing logic and saving new skudetails row here
				scrapInput = new ScrapInput(url.getUrl(),
						InputConverter.convert(url.getTitlePattern()), 
						InputConverter.convert(url.getSellingPricePattern()), 
						InputConverter.convert(url.getListPricePattern()), 
						InputConverter.convert(url.getAvailabilityPattern()));
				
				output = scrapper.scrape(scrapInput);
				System.out.println(output);
				Thread.sleep(10000);
				
				skuDetailsDAO.saveOrUpdate(getSkuDetails(url, output));
				
				url.setLastUpdated(new Timestamp(new Date().getTime()));
				urlDao.update(url);
						
			}
			Thread.sleep(Utility.SECOND*5); // 1 minute wait
		}
	}

	public SkuDetails getSkuDetails(Url url, ScrapOutput output){
		SkuDetails details = new SkuDetails();
		details.setSku(url.getSku());
		details.setMarketPlace(url.getMarketplace());
		details.setUrlId(url.getId());
		
		if(output.getTitle()!=null){
			details.setTitle(output.getTitle());
		}
		
		if(output.getAvailablity()!=null){
			details.setAvailable(output.getAvailablity());
		}
		
		if(output.getListPrice()!=null){
			System.out.println("ListPrice = "+output.getListPrice());
			details.setListPrice(Double.parseDouble(Utility.cleanWebString(output.getListPrice()).trim()));
		}
		
		if(output.getSellingPrice()!=null){
			
			details.setSellingPrice(Double.parseDouble(Utility.cleanWebString(output.getSellingPrice()).trim()));
		}
		
		System.out.println("before saving -> "+ details);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return details;
		
	}

	@Override
	public void run() {
		try {
			if(!isPolling){
				queuepoller.pollQueue();
			}
		} catch (InterruptedException e) {
			logger.error("Unable to launch queuepoller", e);
		} catch (IOException e) {
			logger.error("Unable to launch queuepoller", e);
		}
		
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
	  System.out.println("Destroying DB Poller");
	  isPolling = false;
	}

}
