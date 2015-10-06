package hibernate.bean;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sku_details")
public class SkuDetails {
	
	@EmbeddedId
	private SkuDetailsKey skuDetailsKey;
	private Integer id;
	private Integer urlId;
	private Double listPrice;
	private Double sellingPrice;
	private String title;
	private String available;
	private Timestamp lastUpdated;


	public SkuDetails() {
	}

	public SkuDetailsKey getSkuDetailsKey() {
		return skuDetailsKey;
	}

	public void setSkuDetailsKey(SkuDetailsKey skuDetailsKey) {
		this.skuDetailsKey = skuDetailsKey;
	}


	public SkuDetails(Integer id, Integer urlId,
			Double listPrice, Double sellingPrice,
			String title, String available, SkuDetailsKey skuDetailsKey) {
		super();
		this.id = id;
		this.skuDetailsKey = skuDetailsKey;
		this.urlId = urlId;
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





	@Column(name = "URL_ID")
	public Integer getUrlId() {
		return urlId;
	}



	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
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
		return this.skuDetailsKey.getSku() + " " + this.skuDetailsKey.getMarketPlace() +  " " + this.listPrice + " " + this.sellingPrice + this.lastUpdated;
	}
	
	@Column(name = "LAST_UPDATED")
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
