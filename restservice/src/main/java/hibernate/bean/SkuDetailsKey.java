package hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SkuDetailsKey {
	
	private String sku;
	private String marketPlace;
	
	
	@Column(name = "SKU")
	public String getSku() {
		return sku;
	}



	public void setSku(String sku) {
		this.sku = sku;
	}



	@Column(name = "MARKETPLACE")
	public String getMarketPlace() {
		return marketPlace;
	}



	public void setMarketPlace(String marketPlace) {
		this.marketPlace = marketPlace;
	}

}
