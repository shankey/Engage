package Scrapping;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
		
		
		System.out.println(selectBestParse(input.getTitlePattern(), doc).text()); // try catch error handling
		System.out.println(selectBestParse(input.getListPricePattern(), doc).text());
		if(selectBestParse(input.getSellingPricePattern(), doc)!=null)
		System.out.println(selectBestParse(input.getSellingPricePattern(), doc).text());
		System.out.println(selectBestParse(input.getAvailabilityPattern(), doc).text());

		/*System.out.println(parse(input.getAvailabilityPattern(), userAgent) + parse(input.getAvailabilityPattern(), userAgent).getText().trim());
		System.out.println(parse(input.getListPricePattern(), userAgent) + parse(input.getListPricePattern(), userAgent).getText());
		System.out.println(parse(input.getTitlePattern(), userAgent) + parse(input.getTitlePattern(), userAgent).getText());*/
	}
	
	/* There are various patterns in a single pattern string
	 * The first pattern that provides a not null , not empty result we return it.
	 * This is pure alogrithmic method which will return the first not null not empty result
	 * So if instead of a price we are returning a title string it will not make the intellegent
	 * decision
	 * */
	private Element selectBestParse(Map<Integer, List<StepPattern>> manySteps, Document doc){
		Element result = null;
		
		for(int i=1; i<=manySteps.size(); i++){
				result = parse(manySteps.get(i), doc);
				
				
				if(!(result==null || result.text()==null || result.text().trim().isEmpty())){
					return result;
				}
		}
		
		return null;
	}
	
	private Element parse(List<StepPattern> steps, Document doc){
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
			
			case CONTAINS_ATTRIBUTE:
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
			
			case CONTAINS_TEXT:
				if(element==null){
					return null;
				}else{
					if(!Pattern.compile(pattern).matcher(element.text().trim()).find()){
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
