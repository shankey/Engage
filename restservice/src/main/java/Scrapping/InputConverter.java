package Scrapping;

import java.util.ArrayList;
import java.util.List;

import Common.Steps;
import bean.StepPattern;

public class InputConverter {
	
	public static List<StepPattern> convert(String pattern){
		List<StepPattern> stepPatterns = new ArrayList<StepPattern>();
		String[] stepPatternSplits = pattern.split("::");
		
		for(String stepPatternSplit: stepPatternSplits){
			String[] stepPatternString = stepPatternSplit.split(";;");
			StepPattern stepPattern = new StepPattern();
			stepPattern.setPattern(stepPatternString[0]);
			stepPattern.setStep(Steps.valueOf(stepPatternString[1]));
			stepPatterns.add(stepPattern);
		}
		
		return stepPatterns;
		
	}

}
