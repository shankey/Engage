package hibernate.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import hibernate.bean.SkuDetails;
import hibernate.bean.Url;

public class UrlDAO {
	
	public List<Url> getOutdatedUrlDetails(){
		
		Url url = new Url();
		
		url.setLastUpdated(getLastDayTimestamp());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(Url.class);
		
		cr.setMaxResults(4);
		cr.add(Restrictions.le("lastUpdated", getLastDayTimestamp()));
		cr.add(Restrictions.eq("state", 0));
		
		
		List<Url> results = (List<Url>)cr.list();
		
		session.close();
		
		return results;
		
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
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		
		
			
			tx.begin();
			session.update(url);
			tx.commit();
			session.flush();
			
		
		session.close();
		
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
