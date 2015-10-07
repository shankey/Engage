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
import Common.Utility;
import bean.ScrapInput;
import bean.ScrapOutput;
import bean.StepPattern;

public class Scrapper {
	
	public ScrapOutput scrape(ScrapInput input) throws IOException{
		
		URL urlu = new URL(input.getUrl());
		Document doc = Jsoup.parse(urlu, 60000); // retry mechanism
		
		ScrapOutput output = new ScrapOutput();
		
		output.setTitle(getParsedValue(input.getTitlePattern(), doc));
		output.setListPrice(getParsedValue(input.getListPricePattern(), doc));
		output.setSellingPrice(getParsedValue(input.getSellingPricePattern(), doc));
		output.setAvailablity(getParsedValue(input.getAvailabilityPattern(), doc));
		
		return output;
		
	}
	
	private String getParsedValue(Map<Integer, List<StepPattern>> pattern, Document doc){
		
		if(pattern == null){
			return null;
		}
		
		Element output = selectBestParse(pattern, doc);
		if(output!=null){
			return Utility.cleanWebString(output.text());
		}
		return null;
		
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
			
				try{
					result = parse(manySteps.get(i), doc);
				}catch(Exception e){
					System.out.println("ERROR at Pattern no "+i+" Pattern Vaues "+ manySteps);
					e.printStackTrace();
				}
				
				
				
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
