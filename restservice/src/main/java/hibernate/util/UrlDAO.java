package hibernate.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;




import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import hibernate.bean.SkuDetails;
import hibernate.bean.Url;

public class UrlDAO {
	
	private static Logger logger = Logger.getLogger(UrlDAO.class);
	
	private static UrlDAO dao = new UrlDAO();
	
	private UrlDAO(){
		
	}
	
	public static UrlDAO getUrlDao(){
		return dao;
	}
	
	public List<Url> getOutdatedUrlDetails(){
		
		try{
			
		
		Url url = new Url();
		
		url.setLastUpdated(getLastDayTimestamp());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(Url.class);
		
		cr.setMaxResults(100);
		cr.add(Restrictions.le("lastUpdated", getLastDayTimestamp()));
		
		
		List<Url> results = (List<Url>)cr.list();
		
		session.close();
		
		return results;
		}catch(Exception e){
			logger.error("Error while getting outdatedUrls", e);
			return null;
		}
		
	}
	
	private Timestamp getLastDayTimestamp(){
		Timestamp ts = new Timestamp(new Date().getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		cal.add(Calendar.HOUR, -24);
		ts.setTime(cal.getTime().getTime()); // or
		System.out.println("checking against ->" +ts);
		return ts;
	}
	
	public void update(Url url){
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();
			
			
				
				tx.begin();
				session.update(url);
				tx.commit();
				
				
		}catch(Exception e){
			logger.error("Error while updating URL", e);
			
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
			
		}
		
		
	}
	
	public void update(List<Url> urlList){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		
		for(Url url: urlList){
			
			tx.begin();
			session.update(url);
			tx.commit();
			session.flush();
			
		}
		session.close();
		
	}

}
