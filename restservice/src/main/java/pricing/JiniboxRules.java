package pricing;

import java.util.List;

import org.apache.log4j.Logger;

import hibernate.bean.JiniboxSkuDetails;
import hibernate.util.JiniboxSkuDetailsDAO;

public class JiniboxRules {
	
	private static Logger log = Logger.getLogger(JiniboxRules.class);
	
	JiniboxSkuDetailsDAO dao = JiniboxSkuDetailsDAO.getJiniboxSkuDetailsDAO();
	
	
	public Double generateOurPrice(String sku){
		
		JiniboxSkuDetails details = new JiniboxSkuDetails();
		details.setSku(sku);
		List<JiniboxSkuDetails> list = dao.getJiniBoxDetail(details);
		
		if(list!=null && list.size()==1){
			JiniboxSkuDetails jiniboxSkuDetails = list.get(0);
			Double listPrice = jiniboxSkuDetails.getListPrice()==null?0:jiniboxSkuDetails.getListPrice();
			log.info("ListPrice = "+listPrice);
			Double ourPrice;
			Double purchaseDiscountPercentage = jiniboxSkuDetails.getPurchageDiscountPercentage()==null
					?0:jiniboxSkuDetails.getPurchageDiscountPercentage();
			purchaseDiscountPercentage = purchaseDiscountPercentage/100;
			log.info("PurchaseDiscountPrice = "+purchaseDiscountPercentage);
			
			Double variableCostPercentage = jiniboxSkuDetails.getVariableCostPercentage()==null?
					0:jiniboxSkuDetails.getVariableCostPercentage();
			variableCostPercentage = variableCostPercentage/100;
			log.info("Variable Cost Percentage = "+variableCostPercentage);
			
			Double variableCostExtra = jiniboxSkuDetails.getVariableCostExtra()==null?
					0:jiniboxSkuDetails.getVariableCostExtra();
			log.info("Variable Cost Extra = "+variableCostExtra);
			
			Double contributionMarginPercentage = jiniboxSkuDetails.getContributionMarginPercentage()==null?
					0:jiniboxSkuDetails.getContributionMarginPercentage();
			contributionMarginPercentage = contributionMarginPercentage/100;
			log.info("Contribution Margin Percetage = "+contributionMarginPercentage);
			
			Double floorPricePercentage = jiniboxSkuDetails.getFloorPricePercentage()==null?
					0:jiniboxSkuDetails.getFloorPricePercentage();
			floorPricePercentage = floorPricePercentage / 100;
			log.info("Floor Price Percentage = "+floorPricePercentage);
			
			Double cogs = listPrice * (1-purchaseDiscountPercentage);
			log.info("cogs = "+cogs);
			Double variableCost = (listPrice * variableCostPercentage) + variableCostExtra;
			log.info("Variable Cost = "+variableCost);
			
			Double totalCost = cogs + variableCost;
			log.info("Total Cost"+ totalCost);
			
			Double contributionMarginPrice = 
					Math.min(totalCost/ ( 1- contributionMarginPercentage), listPrice);
			log.info("Contribution Margin Price = "+contributionMarginPrice);	
			
			Double floorPrice = totalCost / (1 - floorPricePercentage);
			log.info("Floor Price = "+floorPrice);
			
			if(jiniboxSkuDetails.getCompetitorPriceIndex()!=null){
				ourPrice = Math.max(floorPrice, Math.min(contributionMarginPrice, jiniboxSkuDetails.getCompetitorPriceIndex()));
			}else{
				ourPrice = Math.max(floorPrice, contributionMarginPrice);
			}
			log.info("Our Price = "+ourPrice);
			
			jiniboxSkuDetails.setOurPrice(ourPrice);
			
			
			dao.saveOrUpdate(jiniboxSkuDetails);
			
			return ourPrice;
			
		}else{
			log.info("For the SKU "+sku+" list was null or list.size was not equal to 1");
			return null;
		}
		
	}
	
	private JiniboxSkuDetails getNewObject(JiniboxSkuDetails details){
		log.info("jinibox object is = "+details);
		JiniboxSkuDetails obj = new JiniboxSkuDetails();
		obj.setSku(details.getSku());
		obj.setTitle(details.getTitle());
		obj.setCategory(details.getCategory());
		log.info("listPrice = "+details.getListPrice());
		obj.setListPrice(details.getListPrice());
		obj.setPurchageDiscountPercentage(details.getPurchageDiscountPercentage());
		obj.setCompetitorPriceIndex(details.getCompetitorPriceIndex());
		obj.setContributionMarginPercentage(details.getContributionMarginPercentage());
		obj.setFloorPricePercentage(details.getFloorPricePercentage());
		obj.setVariableCostExtra(details.getVariableCostExtra());
		obj.setVariableCostPercentage(details.getVariableCostPercentage());
		
		return obj;
	}

}
