package hibernate.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "url")
public class Url implements Comparable<Url> {
	
	@Id
	private Integer id;
	private String url;
	private String sku;
	private String listPricePattern;
	private String sellingPricePattern;
	private String availabilityPattern;
	private String titlePattern;
	private Timestamp lastUpdated;
	private String marketplace;
	
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "URL")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	@Column(name = "LAST_UPDATED")
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
	@Column(name = "MARKETPLACE")
	public String getMarketplace() {
		return marketplace;
	}
	public void setMarketplace(String marketplace) {
		this.marketplace = marketplace;
	}
	
	@Column(name = "LIST_PRICE_PATTERN") 
	public String getListPricePattern() {
		return listPricePattern;
	}
	public void setListPricePattern(String listPricePattern) {
		this.listPricePattern = listPricePattern;
	}
	
	@Column(name = "SELLING_PRICE_PATTERN")
	public String getSellingPricePattern() {
		return sellingPricePattern;
	}
	public void setSellingPricePattern(String sellingPricePattern) {
		this.sellingPricePattern = sellingPricePattern;
	}
	
	@Column(name = "AVAILABILITY_PATTERN")
	public String getAvailabilityPattern() {
		return availabilityPattern;
	}
	public void setAvailabilityPattern(String availabilityPattern) {
		this.availabilityPattern = availabilityPattern;
	}
	
	@Column(name = "TITLE_PATTERN")
	public String getTitlePattern() {
		return titlePattern;
	}
	public void setTitlePattern(String titlePattern) {
		this.titlePattern = titlePattern;
	}
	
	@Column(name = "SKU")
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String toString(){
		return this.marketplace + " " + this.sku;
	}
	
	@Override
	public int compareTo(Url o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
