package scrapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Common.Steps;
import bean.StepPattern;

public class InputConverter {
	
	public static Map<Integer, List<StepPattern>> convert(String pattern){
		Map<Integer, List<StepPattern>> stepPatternsMap = new HashMap<Integer, List<StepPattern>>();
		String[] variousPatterns = pattern.split(":OR:");
		
		int index=1;
		for(String variousPattern : variousPatterns){
			
			String[] stepPatternSplits = variousPattern.split("::");
			List stepPatterns = new ArrayList<StepPattern>();
			for(String stepPatternSplit: stepPatternSplits){
				String[] stepPatternString = stepPatternSplit.split(";;");
				StepPattern stepPattern = new StepPattern();
				stepPattern.setPattern(stepPatternString[0]);
				stepPattern.setStep(Steps.valueOf(stepPatternString[1]));
				stepPatterns.add(stepPattern);
			}
			stepPatternsMap.put(index, stepPatterns);
			index++;
		}
		
		
		return stepPatternsMap;
		
	}

}
