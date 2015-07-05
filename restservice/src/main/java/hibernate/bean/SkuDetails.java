package hibernate.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sku_details")
public class SkuDetails {
	
	@Id
	private Integer id;
	private String sku;
	private Integer urlId;
	private String marketPlace;
	private Double listPrice;
	private Double sellingPrice;
	private String title;
	private String available;

	public SkuDetails() {
	}
 
	
 
	public SkuDetails(Integer id, String sku, Integer urlId,
			String marketPlace, Double listPrice, Double sellingPrice,
			String title, String available) {
		super();
		this.id = id;
		this.sku = sku;
		this.urlId = urlId;
		this.marketPlace = marketPlace;
		this.listPrice = listPrice;
		this.sellingPrice = sellingPrice;
		this.title = title;
		this.available = available;
	}

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name = "SKU")
	public String getSku() {
		return sku;
	}



	public void setSku(String sku) {
		this.sku = sku;
	}


	@Column(name = "URL_ID")
	public Integer getUrlId() {
		return urlId;
	}



	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}


	@Column(name = "MARKETPLACE")
	public String getMarketPlace() {
		return marketPlace;
	}



	public void setMarketPlace(String marketPlace) {
		this.marketPlace = marketPlace;
	}


	@Column(name = "LIST_PRICE")
	public Double getListPrice() {
		return listPrice;
	}



	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}


	@Column(name = "SELLING_PRICE")
	public Double getSellingPrice() {
		return sellingPrice;
	}



	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}


	@Column(name = "AVAILABLE")
	public String getAvailable() {
		return available;
	}



	public void setAvailable(String available) {
		this.available = available;
	}
	
	public String toString(){
		return this.sku + " " + this.listPrice + " " + this.sellingPrice;
	}

}
