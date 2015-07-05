package scrapping;

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
import bean.ScrapOutput;
import bean.StepPattern;

public class Scrapper {
	
	public ScrapOutput scrape(ScrapInput input) throws IOException{
		
		URL urlu = new URL(input.getUrl());
		Document doc = Jsoup.parse(urlu, 60000); // retry mechanism
		
		ScrapOutput output = new ScrapOutput();
		output.setTitle(selectBestParse(input.getTitlePattern(), doc).text());
		output.setListPrice(selectBestParse(input.getListPricePattern(), doc).text());
		output.setSellingPrice(selectBestParse(input.getSellingPricePattern(), doc).text());
		output.setAvailablity(selectBestParse(input.getAvailabilityPattern(), doc).text());
		
		return output;
		
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
