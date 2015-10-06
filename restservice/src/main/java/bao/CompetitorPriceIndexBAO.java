package bao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import hibernate.bean.CompetitorMatchBean;
import hibernate.bean.JiniboxSkuDetails;
import hibernate.bean.SkuDetails;
import hibernate.bean.SkuDetailsKey;
import hibernate.util.JiniboxSkuDetailsDAO;
import hibernate.util.SkuDetailsDAO;

public class CompetitorPriceIndexBAO {
	
	Logger log = Logger.getLogger(CompetitorPriceIndexBAO.class);
	
	SkuDetailsDAO dao = SkuDetailsDAO.getSkuDetailsDAO();
	JiniboxSkuDetailsDAO jiniboxSkuDetailsDAO = JiniboxSkuDetailsDAO.getJiniboxSkuDetailsDAO();
	
	public void saveCompetitorPriceIndex(String sku){
		
		CompetitorMatchBean returnBean = getMedianCompetitorPrice(sku);
		Double competitorPriceIndex = returnBean.getPrice();
		String matchedCompetitor = returnBean.getMarketPlace();
		
		JiniboxSkuDetails detail = new JiniboxSkuDetails();
		detail.setSku(sku);
		List<JiniboxSkuDetails> list = jiniboxSkuDetailsDAO.getJiniBoxDetail(detail);
		if(list!=null && list.size()!=0){
			JiniboxSkuDetails jiniboxSkuDetails = list.get(0);
			jiniboxSkuDetails.setCompetitorPriceIndex(competitorPriceIndex);
			jiniboxSkuDetails.setCompetitorMatch(matchedCompetitor);
			
			jiniboxSkuDetailsDAO.saveOrUpdate(jiniboxSkuDetails);
		}else{
			JiniboxSkuDetails jiniboxSkuDetails = new JiniboxSkuDetails();
			jiniboxSkuDetails.setSku(sku);
			jiniboxSkuDetails.setCompetitorPriceIndex(competitorPriceIndex);
			jiniboxSkuDetails.setCompetitorMatch(matchedCompetitor);
			jiniboxSkuDetailsDAO.saveOrUpdate(jiniboxSkuDetails);
			log.error("creating new entry in jinibox_sku_details = "+sku);
		}
	}
	
	public CompetitorMatchBean getMedianCompetitorPrice(String sku){
		
		SkuDetails details = new SkuDetails();
		SkuDetailsKey skuDetailsKey = new SkuDetailsKey();
		skuDetailsKey.setSku(sku);
		details.setSkuDetailsKey(skuDetailsKey);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -30);
		long thirtyDaysAgo = cal.getTimeInMillis();
		details.setLastUpdated(new Timestamp(thirtyDaysAgo));
		
		List<SkuDetails> skuDetailsList = dao.getSkuDetails(details);
		
		Map<String, List<Double>> marketSkuDetailsMap = new HashMap<String, List<Double>>();
		
		Double min = null;
		String marketMatch=null;
		
		for(SkuDetails skuDetails: skuDetailsList){
			if(skuDetails.getSellingPrice()!=null){
				if(marketSkuDetailsMap.containsKey(skuDetails.getSkuDetailsKey().getMarketPlace())){
					marketSkuDetailsMap.get(skuDetails.getSkuDetailsKey().getMarketPlace()).add(new Double(skuDetails.getSellingPrice()));
				}else{
					List<Double> list = new ArrayList<Double>();
					list.add(new Double(skuDetails.getSellingPrice()));
					marketSkuDetailsMap.put(skuDetails.getSkuDetailsKey().getMarketPlace(), list);
				}
			}
		}
		
		for(String market: marketSkuDetailsMap.keySet()){
			List<Double> li = marketSkuDetailsMap.get(market);
			Collections.sort(li);
			if(li.size()>0){
				Double tempMin = li.get((li.size()/2));
				if(tempMin!=null && (min==null || min>tempMin)){
					min=tempMin;
					marketMatch = market;
				}
			}
			
		}
		
		CompetitorMatchBean returnBean = new CompetitorMatchBean();
		returnBean.setPrice(min);
		returnBean.setMarketPlace(marketMatch);
		
		return returnBean;
		
	}

}
