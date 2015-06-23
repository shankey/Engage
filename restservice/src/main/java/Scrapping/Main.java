package Scrapping;

import bean.ScrapInput;

import com.jaunt.JauntException;

public class Main {
	
	public static void main2(String args[]) throws JauntException{
		
		String url = "http://www.amazon.in/Pampers-Baby-Diapers-Small-Count/dp/B00ESY0ANG/ref=sr_1_1?ie=UTF8&qid=1430113788&sr=8-1&keywords=Pampers+baby+dry";
		String titlePattern = "<span id=productTitle>;;FIND_FIRST";
		String pricePattern = "<td>Price:;;FIND_FIRST::;;NEXT_ELEMENT";
		String availabilityPattern = "<div id=availability>;;FIND_FIRST::<span>;;FIND_FIRST";
		
		String url2 = "http://www.amazon.in/Pampers-Medium-Size-Diapers-Count/dp/B00AWMC2BQ/ref=sr_1_fkmr1_1?ie=UTF8&qid=1430113843&sr=8-1-fkmr1&keywords=Pampers+baby+dry+medium+42";
		String url3 = "http://www.amazon.in/Pampers-Large-Size-Diapers-Count/dp/B00AWMC2YS/ref=sr_1_fkmr0_2?ie=UTF8&qid=1430113881&sr=8-2-fkmr0&keywords=Pampers+baby+dry+large";
		
		
		
		
		Scrapper sc = new Scrapper();
	/*	sc.scrape(input1);
		sc.scrape(input2);
		sc.scrape(input3);*/
		
	}
	
	

}
