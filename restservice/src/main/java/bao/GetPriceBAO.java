package bao;

import java.util.List;

import hibernate.bean.SkuDetails;
import hibernate.bean.SkuDetailsKey;
import hibernate.util.SkuDetailsDAO;

public class GetPriceBAO {
	
	private SkuDetailsDAO skuDetailsDAO = SkuDetailsDAO.getSkuDetailsDAO();
	
	public List<SkuDetails> getPriceDetailsForSku(String sku){
		SkuDetails skuDetails = new SkuDetails();
		SkuDetailsKey skuDetailsKey = new SkuDetailsKey();
		skuDetailsKey.setSku(sku);
		skuDetails.setSkuDetailsKey(skuDetailsKey);
		
		List<SkuDetails> list = skuDetailsDAO.getSkuDetails(skuDetails);
		return list;
		
	}

}
