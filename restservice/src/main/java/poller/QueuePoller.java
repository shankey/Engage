package poller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;

import scrapping.InputConverter;
import scrapping.Scrapper;
import bean.ScrapInput;
import bean.ScrapOutput;
import hibernate.bean.SkuDetails;
import hibernate.bean.SkuDetailsKey;
import hibernate.bean.Url;
import hibernate.util.SkuDetailsDAO;
import hibernate.util.UrlDAO;
import Common.Utility;

public class QueuePoller implements Runnable {
	
	private static QueuePoller queuepoller = new QueuePoller(); 
	private static Logger logger = Logger.getLogger(QueuePoller.class);
	public boolean isPolling = false;
	
public void pollQueue() throws InterruptedException{
	
		
		UrlDAO urlDao = UrlDAO.getUrlDao();
		SkuDetailsDAO skuDetailsDAO = SkuDetailsDAO.getSkuDetailsDAO();
		Scrapper scrapper = new Scrapper();
		isPolling = true;
		
		
		Url url;
		ScrapInput scrapInput;
		ScrapOutput output;
		while(!Thread.currentThread().isInterrupted()){
			logger.info("inside while of pollQueuue");
			// if the queue is not empty then try to poll DB and fill queue
			if(!Queues.getUrlQueue().isEmpty()){
				logger.info("Queue was not empty");
				url = Queues.getUrlQueue().poll();
				logger.info(url);
				
				//put parsing logic and saving new skudetails row here
				scrapInput = new ScrapInput(url.getUrl(),
						InputConverter.convert(url.getTitlePattern()), 
						InputConverter.convert(url.getSellingPricePattern()), 
						InputConverter.convert(url.getListPricePattern()), 
						InputConverter.convert(url.getAvailabilityPattern()));
				
				try {
					output = scrapper.scrape(scrapInput);
					logger.info(output);

					SkuDetails skuDetails = getSkuDetails(url, output);
					List<SkuDetails> skuDetailsfromDb = skuDetailsDAO
							.getSkuDetails(skuDetails);
					if (skuDetailsfromDb != null && skuDetailsfromDb.size() > 0) {
						skuDetails.setId(skuDetailsfromDb.get(0).getId());
					}
					skuDetailsDAO.saveOrUpdate(skuDetails);

					url.setLastUpdated(new Timestamp(new Date().getTime()));
					urlDao.update(url);
				} catch (Exception e) {
					logger.error("Problem in scrapping/saving "+ url.getUrl() + " " + url.getSku() + " "+ url.getMarketplace(), e);
				}
						
			}
			Thread.sleep(Utility.SECOND*60); // 1 minute wait
		}
	}

	public SkuDetails getSkuDetails(Url url, ScrapOutput output){
		SkuDetails details = new SkuDetails();
		SkuDetailsKey skuDetailsKey = new SkuDetailsKey();
		skuDetailsKey.setSku(url.getSku());
		skuDetailsKey.setMarketPlace(url.getMarketplace());
		
		details.setSkuDetailsKey(skuDetailsKey);
		details.setUrlId(url.getId());
		
		if(output.getTitle()!=null){
			details.setTitle(output.getTitle());
		}
		
		if(output.getAvailablity()!=null){
			details.setAvailable(output.getAvailablity());
		}
		
		if(output.getListPrice()!=null){
			logger.info("ListPrice = "+output.getListPrice());
			details.setListPrice(Double.parseDouble(Utility.cleanWebString(output.getListPrice()).trim()));
		}
		
		if(output.getSellingPrice()!=null){
			
			details.setSellingPrice(Double.parseDouble(Utility.cleanWebString(output.getSellingPrice()).trim()));
		}
		
		logger.info("before saving -> "+ details);
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
		}
		
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
	  logger.info("Destroying DB Poller");
	  isPolling = false;
	}

}
