package scrapping;

import hibernate.bean.SkuDetails;
import hibernate.bean.SkuDetailsKey;
import hibernate.util.SkuDetailsDAO;

import java.io.IOException;
import java.util.List;

import poller.DBPoller;
import poller.QueuePoller;
import bao.GetPriceBAO;
import bean.ScrapInput;
import bean.ScrapOutput;


public class Main {
	
	public static void main2(String args[]) throws IOException, InterruptedException{
		
		//amazon
		String url = "http://www.amazon.in/Saco-Pouch-Xiaomi-Power-10400/dp/B00R9IOZ8Y/ref=sr_1_7?s=electronics&ie=UTF8&qid=1435174923&sr=1-7&keywords=power+bank";
		String url2 = "http://www.amazon.in/Pampers-Baby-Diapers-Small-Count/dp/B00ESY0ANG/ref=sr_1_1?ie=UTF8&qid=1430113788&sr=8-1&keywords=Pampers+baby+dry";
		String titlePattern = "span[id=productTitle];;FIND_FIRST";
		String listPricePattern = "td:contains(Price:);;FIND_FIRST::;;NEXT_SIBLING_NODE::;;FIRST_CHILD_NODE::[0-9];;CONTAINS_TEXT:OR:td:contains(Price:);;FIND_FIRST::;;NEXT_SIBLING_NODE::[0-9];;CONTAINS_TEXT";
		String sellingPricePattern = "td:contains(Sale:);;FIND_FIRST::;;NEXT_SIBLING_NODE::;;FIRST_CHILD_NODE";
		String availabilityPattern = "div[id=availability];;FIND_FIRST::;;FIRST_CHILD_NODE";
		
		String url3 = "http://www.firstcry.com/pampers/pampers-baby-dry-diaper-newborn-to-small-46-pieces/458343/product-detail";
		String url4 = "http://www.firstcry.com/littles/littles-socks-booties-black-and-white/39101/product-detail";
		String titlePattern3 = "h1[class=p_heading p_lft dcp];;FIND_FIRST";
		String listPricePattern3 = "div[class=p_price_save p_lft dcp];;FIND_FIRST";
		String sellingPricePattern3 = "div[class=p_price p_lft dcp];;FIND_FIRST";
		String availabilityPattern3 = "div[class=p_stock_avail p_lft dcp];;FIND_FIRST";
		
		String url5 = "http://www.flipkart.com/dettol-liquid-soap-cool-pump/p/itmduzx5e3dzwhvf?pid=HWSDUZX5FVXAANK5&otracker=from-search&srno=t_4&query=dettol&ref=546448a5-0510-4c90-9d01-6dd04d6fc71c";
		String url6 = "http://www.flipkart.com/apple-iphone-6/p/itme8ra5z7yx5c9j?pid=MOBEYHZ2GY7HDHHG&ref=L%3A5567396474029687488&srno=p_8&query=iphone+6&otracker=from-search";
		String titlePattern5 = "h1[class=title];;FIND_FIRST";
		String listPricePattern5 = "div[class=pricing line];;FIND_FIRST::;;FIRST_CHILD_NODE::;;FIRST_CHILD_NODE";
		String sellingPricePattern5 = "span[class=selling-price omniture-field];;FIND_FIRST";
		String availabilityPattern5 = "div[class=out-of-stock-status];;FIND_FIRST";
	
		SkuDetails skuDetails = new SkuDetails();
		SkuDetailsKey key = new SkuDetailsKey();
		skuDetails.setId(410);
		key.setSku("TestAdi1");
		key.setMarketPlace("TestMarketplace");
		skuDetails.setSkuDetailsKey(key);
		
		SkuDetailsDAO.getSkuDetailsDAO().saveOrUpdate(skuDetails);
		
		/*List<SkuDetails> li = new GetPriceBAO().getPriceDetailsForSku("JN68310059");
		for(SkuDetails detail: li){
			System.out.println(detail);
		}*/
		/*Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				try {
					new QueuePoller().pollQueue();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		Thread t = new Thread(r1);
		t.start();
		
		new DBPoller().pollDB();
		
		
		ScrapInput input = new ScrapInput(url2, 
				InputConverter.convert(titlePattern), 
				InputConverter.convert(sellingPricePattern),
				InputConverter.convert(listPricePattern),
				InputConverter.convert(availabilityPattern));
		Scrapper sc = new Scrapper();
		ScrapOutput output = sc.scrape(input);
		System.out.println(output);
		
		ScrapInput input2 = new ScrapInput(url4, 
				InputConverter.convert(titlePattern3), 
				InputConverter.convert(sellingPricePattern3),
				InputConverter.convert(listPricePattern3),
				InputConverter.convert(availabilityPattern3));
		Scrapper sc2 = new Scrapper();
		sc2.scrape(input2);
		
		ScrapInput input3 = new ScrapInput(url6, 
				InputConverter.convert(titlePattern5), 
				InputConverter.convert(sellingPricePattern5),
				InputConverter.convert(listPricePattern5),
				InputConverter.convert(availabilityPattern5));
		Scrapper sc3 = new Scrapper();
		sc2.scrape(input3);*/
		/*URL urlu = new URL(url);
		Document doc = Jsoup.parse(urlu, 60000);

		  // img with src ending .png

		Element masthead = doc.select("td:contains(Price:)").first();
		//System.out.println(masthead.nextElementSibling().text());
		Element ele = masthead.nextElementSibling();
		System.out.println(ele.attr("class"));
		//System.out.println(masthead);
		
*/	}
	
	

}
