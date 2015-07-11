package hibernate.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Common.Utility;
import hibernate.bean.SkuDetails;
import hibernate.bean.Url;

public class SkuDetailsDAO {
	
	private SkuDetailsDAO(){
		
	}
	
	private static SkuDetailsDAO dao = new SkuDetailsDAO();
	
	public static SkuDetailsDAO getSkuDetailsDAO(){
		return dao;
	}
	
	public List<SkuDetails> getSkuDetails(SkuDetails details){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(SkuDetails.class);
		
		if(!Utility.isNull(details.getSku())){
			cr.add(Restrictions.eq("sku", details.getSku()));
		}
		
		if(!Utility.isNull(details.getMarketPlace())){
			cr.add(Restrictions.eq("marketPlace", details.getMarketPlace()));
		}
		
		if(!Utility.isNull(details.getTitle())){
			cr.add(Restrictions.eq("title", details.getTitle()));
		}
		
		if(!Utility.isNull(details.getAvailable())){
			cr.add(Restrictions.eq("available", details.getAvailable()));
		}
		
		return (List<SkuDetails>)cr.list();
	}
	
	public void saveOrUpdate(SkuDetails skuDetails){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		
		
			
			tx.begin();
			session.saveOrUpdate(skuDetails);
			tx.commit();
			session.flush();
			
		
		session.close();
		
	}
	

}
