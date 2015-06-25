package bean;

import java.util.List;
import java.util.Map;

public class ScrapInput {
	
	String url;
	Map<Integer, List<StepPattern>> titlePattern;
	Map<Integer, List<StepPattern>> sellingPricePattern;
	Map<Integer, List<StepPattern>> listPricePattern;
	Map<Integer, List<StepPattern>> availabilityPattern;

	public ScrapInput(String url, Map<Integer, List<StepPattern>> titlePattern,
			Map<Integer, List<StepPattern>> sellingPricePattern,
			Map<Integer, List<StepPattern>> listPricePattern,
			Map<Integer, List<StepPattern>> availabilityPattern) {
		super();
		this.url = url;
		this.titlePattern = titlePattern;
		this.sellingPricePattern = sellingPricePattern;
		this.listPricePattern = listPricePattern;
		this.availabilityPattern = availabilityPattern;
	}

	public ScrapInput() {
		
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<Integer, List<StepPattern>> getTitlePattern() {
		return titlePattern;
	}

	public void setTitlePattern(Map<Integer, List<StepPattern>> titlePattern) {
		this.titlePattern = titlePattern;
	}

	public Map<Integer, List<StepPattern>> getSellingPricePattern() {
		return sellingPricePattern;
	}

	public void setSellingPricePattern(
			Map<Integer, List<StepPattern>> sellingPricePattern) {
		this.sellingPricePattern = sellingPricePattern;
	}

	public Map<Integer, List<StepPattern>> getListPricePattern() {
		return listPricePattern;
	}

	public void setListPricePattern(Map<Integer, List<StepPattern>> listPricePattern) {
		this.listPricePattern = listPricePattern;
	}

	public Map<Integer, List<StepPattern>> getAvailabilityPattern() {
		return availabilityPattern;
	}

	public void setAvailabilityPattern(
			Map<Integer, List<StepPattern>> availabilityPattern) {
		this.availabilityPattern = availabilityPattern;
	}
	
}
