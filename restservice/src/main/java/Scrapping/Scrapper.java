package Scrapping;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import Common.Steps;
import bean.ScrapInput;
import bean.StepPattern;

public class Scrapper {
	
	public void scrape(ScrapInput input) throws IOException{
		/*final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage(input.getUrl());
	    final String pageAsXml = page.asXml();
	    List<DomNode> node = (List<DomNode>) page.getByXPath("//span[@id=priceblock_saleprice]");
	    for(DomNode nod: node){
	    	System.out.println("nema"+ nod.getNodeValue() + nod.getTextContent() + nod.getAttributes().getNamedItem("class"));
	    }*/
	    
		//userAgent.doc.apply("butterflies");            //apply form input (starting at first editable field)
		//userAgent.doc.submit("Google Search");         //click submit button labelled "Google Search"
		
		URL urlu = new URL(input.getUrl());
		Document doc = Jsoup.parse(urlu, 60000); // retry mechanism
		
		System.out.println(parse(input.getTitlePattern(), doc).text()); // try catch error handling
		System.out.println(parse(input.getListPricePattern(), doc).text());
		System.out.println(parse(input.getSellingPricePattern(), doc).text());
		System.out.println(parse(input.getAvailabilityPattern(), doc).text());

		/*System.out.println(parse(input.getAvailabilityPattern(), userAgent) + parse(input.getAvailabilityPattern(), userAgent).getText().trim());
		System.out.println(parse(input.getListPricePattern(), userAgent) + parse(input.getListPricePattern(), userAgent).getText());
		System.out.println(parse(input.getTitlePattern(), userAgent) + parse(input.getTitlePattern(), userAgent).getText());*/
	}
	
	private Element parse(Map<Integer, List<StepPattern>> steps, Document doc){
		Element element = null;
		for(StepPattern step: steps){
			String pattern = step.getPattern();
			
			switch (step.getStep()) {
			
			case FIND_FIRST:
				if(element==null){
					element = doc.select(pattern).first();
				}else{
					element = element.select(pattern).first();
				}
				
				break;
				
			case GET_PARENT:
				if(element==null){
					return null;
				}else{
					element = element.parent();
				}
				
				break;
				
			case NEXT_SIBLING_NODE: // sibling
				if(element==null){
					return null;
				}else{
					element = element.nextElementSibling();
				}
				break;
				
			case FIRST_CHILD_NODE:
				if(element==null){
					return null;
				}else{
					element = element.child(0);
				}
				break;
			
			case CONTAINS:
				if(element==null){
					return null;
				}else{
					String attribute = pattern.split("*")[0];
					String checkString = pattern.split("*")[1];
					if(!element.attr(attribute).contains(checkString)){
						return null;
					}
				}
				break;
				
			default:
				break;
			}

		}
		
		return element;
	}

}
