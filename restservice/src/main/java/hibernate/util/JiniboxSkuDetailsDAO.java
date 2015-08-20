package hibernate.util;

import java.util.List;

import hibernate.bean.JiniboxSkuDetails;
import hibernate.bean.SkuDetails;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Common.Utility;

public class JiniboxSkuDetailsDAO {
	
private static Logger logger = Logger.getLogger(JiniboxSkuDetailsDAO.class);
	
	private JiniboxSkuDetailsDAO(){
		
	}
	
	private static JiniboxSkuDetailsDAO dao = new JiniboxSkuDetailsDAO();
	
	public static JiniboxSkuDetailsDAO getJiniboxSkuDetailsDAO() {
		return dao;
	}
	
	public List<JiniboxSkuDetails> getJiniBoxDetail(JiniboxSkuDetails details){
		
		Session session=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(JiniboxSkuDetails.class);

			if (!Utility.isNull(details.getSku())) {
				cr.add(Restrictions.eq("sku", details.getSku()));
			}

			if (!Utility.isNull(details.getCategory())) {
				cr.add(Restrictions.eq("category", details.getCategory()));
			}

			if (!Utility.isNull(details.getTitle())) {
				cr.add(Restrictions.eq("title", details.getTitle()));
			}

			if (details.getId()!=null) {
				cr.add(Restrictions.eq("id", details.getId()));
			}
			
			return (List<JiniboxSkuDetails>) cr.list();
		} catch (Exception e) {
			logger.error("Error while getSkuDetails", e);
			return null;
		} finally {
			if(session!=null){
				session.close();
			}
			
		}

	}
	
	public void saveOrUpdate(JiniboxSkuDetails details){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();

			tx.begin();
			logger.info("trying to merge");
			session.merge(details);
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
