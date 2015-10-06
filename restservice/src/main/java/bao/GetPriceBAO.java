package bao;

import java.util.List;

import org.apache.log4j.Logger;

import hibernate.bean.JiniboxSkuDetails;
import hibernate.bean.SkuDetails;
import hibernate.bean.SkuDetailsKey;
import hibernate.util.JiniboxSkuDetailsDAO;
import hibernate.util.SkuDetailsDAO;

public class GetPriceBAO {
	
	private SkuDetailsDAO skuDetailsDAO = SkuDetailsDAO.getSkuDetailsDAO();
	private JiniboxSkuDetailsDAO jiniboxSkuDetailsDAO = JiniboxSkuDetailsDAO.getJiniboxSkuDetailsDAO();
	private static Logger logger = Logger.getLogger(GetPriceBAO.class);
	
	public List<SkuDetails> getCompetitorPriceDetailsForSku(String sku){
		SkuDetails skuDetails = new SkuDetails();
		SkuDetailsKey skuDetailsKey = new SkuDetailsKey();
		skuDetailsKey.setSku(sku);
		skuDetails.setSkuDetailsKey(skuDetailsKey);
		
		List<SkuDetails> list = skuDetailsDAO.getSkuDetails(skuDetails);
		return list;
		
	}
	
	public JiniboxSkuDetails getPriceDetailsForSku(String sku){
		JiniboxSkuDetails skuDetails = new JiniboxSkuDetails();
		skuDetails.setSku(sku);
		
		List<JiniboxSkuDetails> list = jiniboxSkuDetailsDAO.getJiniBoxDetail(skuDetails);
		
		if(list!=null && list.size()!=0)
			return list.get(0);
		
		return null;
		
	}
	
	public Double publishPrice(String sku){
		JiniboxSkuDetails skuDetails = new JiniboxSkuDetails();
		skuDetails.setSku(sku);
		
		List<JiniboxSkuDetails> list = jiniboxSkuDetailsDAO.getJiniBoxDetail(skuDetails);
		
		if(list!=null && list.size()!=0){
			JiniboxSkuDetails details = list.get(0);
			System.out.println("publish price = "+details.getOverridePrice());
			if(details.getOverridePrice() !=null){
				return details.getOverridePrice();
			}else{
				if(details.getOurPrice()!=null){
					return details.getOurPrice();
				}
			}
		}
			
		logger.error("No Price Found for SKU "+sku);
		return null;
	}

}
