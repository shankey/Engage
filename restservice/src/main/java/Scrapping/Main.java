package Scrapping;

import java.io.IOException;

import bean.ScrapInput;


public class Main {
	
	public static void main(String args[]) throws IOException{
		
		String url = "http://www.amazon.in/Saco-Pouch-Xiaomi-Power-10400/dp/B00R9IOZ8Y/ref=sr_1_7?s=electronics&ie=UTF8&qid=1435174923&sr=1-7&keywords=power+bank";
		String url2 = "http://www.amazon.in/Pampers-Baby-Diapers-Small-Count/dp/B00ESY0ANG/ref=sr_1_1?ie=UTF8&qid=1430113788&sr=8-1&keywords=Pampers+baby+dry";
		String titlePattern = "span[id=productTitle];;FIND_FIRST";
		String ourPricePattern = "td:contains(Price:);;FIND_FIRST::;;NEXT_SIBLING_NODE::;;FIRST_CHILD_NODE::[0-9];;CONTAINS_TEXT:OR:td:contains(Price:);;FIND_FIRST::;;NEXT_SIBLING_NODE::[0-9];;CONTAINS_TEXT";
		String sellingPricePattern = "td:contains(Sale:);;FIND_FIRST::;;NEXT_SIBLING_NODE::;;FIRST_CHILD_NODE";
		String availabilityPattern = "div[id=availability];;FIND_FIRST::;;FIRST_CHILD_NODE";
		
		//String url2 = "http://www.amazon.in/Pampers-Medium-Size-Diapers-Count/dp/B00AWMC2BQ/ref=sr_1_fkmr1_1?ie=UTF8&qid=1430113843&sr=8-1-fkmr1&keywords=Pampers+baby+dry+medium+42";
		String url3 = "http://www.amazon.in/Pampers-Large-Size-Diapers-Count/dp/B00AWMC2YS/ref=sr_1_fkmr0_2?ie=UTF8&qid=1430113881&sr=8-2-fkmr0&keywords=Pampers+baby+dry+large";
		
		
		ScrapInput input = new ScrapInput(url, 
				InputConverter.convert(titlePattern), 
				InputConverter.convert(sellingPricePattern),
				InputConverter.convert(ourPricePattern),
				InputConverter.convert(availabilityPattern));
		Scrapper sc = new Scrapper();
		sc.scrape(input);
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
