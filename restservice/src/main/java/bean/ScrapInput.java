package bean;

import java.util.List;

public class ScrapInput {
	
	String url;
	List<StepPattern> titlePattern;
	List<StepPattern> sellingPricePattern;
	List<StepPattern> listPricePattern;
	List<StepPattern> availabilityPattern;

	public ScrapInput(String url, List<StepPattern> titlePattern,
			List<StepPattern> sellingPricePattern,
			List<StepPattern> listPricePattern,
			List<StepPattern> availabilityPattern) {
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




	public List<StepPattern> getTitlePattern() {
		return titlePattern;
	}




	public void setTitlePattern(List<StepPattern> titlePattern) {
		this.titlePattern = titlePattern;
	}




	public List<StepPattern> getSellingPricePattern() {
		return sellingPricePattern;
	}




	public void setSellingPricePattern(List<StepPattern> sellingPricePattern) {
		this.sellingPricePattern = sellingPricePattern;
	}




	public List<StepPattern> getListPricePattern() {
		return listPricePattern;
	}




	public void setListPricePattern(List<StepPattern> listPricePattern) {
		this.listPricePattern = listPricePattern;
	}




	public List<StepPattern> getAvailabilityPattern() {
		return availabilityPattern;
	}




	public void setAvailabilityPattern(List<StepPattern> availabilityPattern) {
		this.availabilityPattern = availabilityPattern;
	}

	
		
	
}
