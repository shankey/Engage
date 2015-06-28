package Scrapping;

import java.io.IOException;

import bean.ScrapInput;


public class Main {
	
	public static void main(String args[]) throws IOException{
		
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
		
		
		
		ScrapInput input = new ScrapInput(url, 
				InputConverter.convert(titlePattern), 
				InputConverter.convert(sellingPricePattern),
				InputConverter.convert(listPricePattern),
				InputConverter.convert(availabilityPattern));
		Scrapper sc = new Scrapper();
		sc.scrape(input);
		
		ScrapInput input2 = new ScrapInput(url4, 
				InputConverter.convert(titlePattern3), 
				InputConverter.convert(sellingPricePattern3),
				InputConverter.convert(listPricePattern3),
				InputConverter.convert(availabilityPattern3));
		Scrapper sc2 = new Scrapper();
		sc2.scrape(input2);
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
