package hibernate.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jinibox_sku_details")
public class JiniboxSkuDetails {
	
	private Integer id;
	private String sku;
	private String title;
	private String category;
	private Double listPrice;
	private Double purchageDiscountPercentage;
	private Double variableCostPercentage;
	private Double variableCostExtra;
	private Double contributionMarginPercentage;
	private Double floorPricePercentage;
	private Double competitorPriceIndex;
	private Double ourPrice;
	
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
	
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "CATEGORY")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

	@Column(name = "LIST_PRICE")
	public Double getListPrice() {
		return listPrice;
	}
	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}
	

	@Column(name = "PURCHASE_DISCOUNT_PERCENTAGE")
	public Double getPurchageDiscountPercentage() {
		return purchageDiscountPercentage;
	}
	public void setPurchageDiscountPercentage(Double purchageDiscountPercentage) {
		this.purchageDiscountPercentage = purchageDiscountPercentage;
	}
	

	@Column(name = "VARIABLE_COST_PERCENTAGE")
	public Double getVariableCostPercentage() {
		return variableCostPercentage;
	}
	public void setVariableCostPercentage(Double variableCostPercentage) {
		this.variableCostPercentage = variableCostPercentage;
	}
	

	@Column(name = "VARIABLE_COST_EXTRA")
	public Double getVariableCostExtra() {
		return variableCostExtra;
	}
	public void setVariableCostExtra(Double variableCostExtra) {
		this.variableCostExtra = variableCostExtra;
	}
	

	@Column(name = "CONTRIBUTION_MARGIN_PERCENTAGE")
	public Double getContributionMarginPercentage() {
		return contributionMarginPercentage;
	}
	public void setContributionMarginPercentage(Double contributionMarginPercentage) {
		this.contributionMarginPercentage = contributionMarginPercentage;
	}
	

	@Column(name = "FLOOR_PRICE_PERCENTAGE")
	public Double getFloorPricePercentage() {
		return floorPricePercentage;
	}
	public void setFloorPricePercentage(Double floorPricePercentage) {
		this.floorPricePercentage = floorPricePercentage;
	}
	

	@Column(name = "COMPETITOR_PRICE_INDEX")
	public Double getCompetitorPriceIndex() {
		return competitorPriceIndex;
	}
	public void setCompetitorPriceIndex(Double competitorPriceIndex) {
		this.competitorPriceIndex = competitorPriceIndex;
	}
	

	@Column(name = "OUR_PRICE")
	public Double getOurPrice() {
		return ourPrice;
	}
	public void setOurPrice(Double ourPrice) {
		this.ourPrice = ourPrice;
	}
	
	@Override
	public String toString(){
		return sku + " " + category +  " " + title;
	}
	
	
}
