package hibernate.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Common.Utility;
import hibernate.bean.SkuDetails;
import hibernate.bean.Url;

public class SkuDetailsDAO {
	
	private static Logger logger = Logger.getLogger(SkuDetailsDAO.class);
	
	private SkuDetailsDAO(){
		
	}
	
	private static SkuDetailsDAO dao = new SkuDetailsDAO();
	
	public static SkuDetailsDAO getSkuDetailsDAO(){
		return dao;
	}
	
	public List<SkuDetails> getSkuDetails(SkuDetails details){
		
		Session session=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(SkuDetails.class);

			if (!Utility.isNull(details.getSkuDetailsKey().getSku())) {
				cr.add(Restrictions.eq("skuDetailsKey.sku", details.getSkuDetailsKey().getSku()));
			}

			if (!Utility.isNull(details.getSkuDetailsKey().getMarketPlace())) {
				cr.add(Restrictions.eq("skuDetailsKey.marketPlace", details.getSkuDetailsKey().getMarketPlace()));
			}

			if (!Utility.isNull(details.getTitle())) {
				cr.add(Restrictions.eq("title", details.getTitle()));
			}

			if (!Utility.isNull(details.getAvailable())) {
				cr.add(Restrictions.eq("available", details.getAvailable()));
			}
			
			if (details.getLastUpdated()!=null) {
				cr.add(Restrictions.ge("lastUpdated", details.getLastUpdated()));
			}

			return (List<SkuDetails>) cr.list();
		} catch (Exception e) {
			logger.error("Error while getSkuDetails", e);
			return null;
		} finally {
			if(session!=null){
				session.close();
			}
			
		}
	}
	
	public void saveOrUpdate(SkuDetails skuDetails){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();

			tx.begin();
			session.saveOrUpdate(skuDetails);
			tx.commit();

		} catch (Exception e) {
			logger.error("Error in saveOrUpdate SkuDetails", e);
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}

		}
	
	}
	

}
