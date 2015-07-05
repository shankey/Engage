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
	
	public List<SkuDetails> getSkuDetails(SkuDetails details){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(SkuDetails.class);
		
		if(Utility.isNull(details.getSku())){
			cr.add(Restrictions.eq("SKU", details.getSku()));
		}
		
		if(Utility.isNull(details.getMarketPlace())){
			cr.add(Restrictions.eq("MARKETPLACE", details.getMarketPlace()));
		}
		
		if(Utility.isNull(details.getSku())){
			cr.add(Restrictions.eq("SKU", details.getSku()));
		}
		
		if(Utility.isNull(details.getTitle())){
			cr.add(Restrictions.eq("TITLE", details.getTitle()));
		}
		
		if(Utility.isNull(details.getAvailable())){
			cr.add(Restrictions.eq("AVAILABLE", details.getAvailable()));
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
