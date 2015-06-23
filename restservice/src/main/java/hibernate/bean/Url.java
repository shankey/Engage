package hibernate.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "url")
public class Url {
	
	@Id
	private Integer id;
	private String url;
	private String listPricePattern;
	private String sellingPricePattern;
	private String availabilityPattern;
	private String titlePattern;
	private Timestamp lastUpdated;
	private Integer state;
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
	
	@Column(name = "STATE")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	
	@Column(name = "AVAILABILITY_PRICE_PATTERN")
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
	
}
