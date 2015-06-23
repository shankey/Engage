package Scrapping;

import java.util.List;

import Common.Steps;
import bean.ScrapInput;
import bean.StepPattern;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.NotFound;
import com.jaunt.UserAgent;

public class Scrapper {
	
	public void scrape(ScrapInput input) throws JauntException{
		UserAgent userAgent = new UserAgent();         //create new userAgent (headless browser)
		userAgent.visit(input.getUrl());          //visit google
		//userAgent.doc.apply("butterflies");            //apply form input (starting at first editable field)
		//userAgent.doc.submit("Google Search");         //click submit button labelled "Google Search"
		     
		/*System.out.println(parse(input.getAvailabilityPattern(), userAgent) + parse(input.getAvailabilityPattern(), userAgent).getText().trim());
		System.out.println(parse(input.getPricePattern(), userAgent) + parse(input.getPricePattern(), userAgent).getText());
		System.out.println(parse(input.getTitlePattern(), userAgent) + parse(input.getTitlePattern(), userAgent).getText());*/
	}
	
	private Element parse(List<StepPattern> steps, UserAgent userAgent) throws NotFound{
		Element element = null;
		for(StepPattern step: steps){
			String pattern = step.getPattern();
			
			switch (step.getStep()) {
			
			case FIND_FIRST:
				if(element==null){
					element = userAgent.doc.findFirst(pattern);
				}else{
					element = element.findFirst(pattern);
				}
				
				break;
				
			case GET_PARENT:
				if(element==null){
					//THROW EXCEPTION
				}else{
					element = element.getParent();
				}
				
				break;
				
			case NEXT_ELEMENT:
				if(element==null){
					//THROW EXCEPTION
				}else{
					element = element.nextSiblingElement();
				}
				break;
				
			case NEXT_NODE:
				if(element==null){
					//THROW EXCEPTION
				}else{
					//element = element.
				}
				break;
			
			default:
				break;
			}

		}
		
		return element;
	}

}
