package hibernate.dao;

import hibernate.bean.Engagement;
import hibernate.bean.Likes;
import hibernate.bean.User;
import hibernate.util.HibernateUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class EngageDAO {
	
	private static Logger logger = Logger.getLogger(EngageDAO.class);
	private static EngageDAO dao = new EngageDAO();
	
	private EngageDAO(){
		
	}
	
	public static EngageDAO getEngageDAO(){
		return dao;
	}
	
	public List getAllByUserId(String sql){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List results = query.list();
		
		session.close();
		return results;
	}
	
	public List<Engagement> getEngageDetails(Engagement engage){
		
		try{

			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(User.class);
			
			cr.add(Restrictions.le("userId", engage.getUserId()));

			List<Engagement> result = (List<Engagement>)cr.list();
			session.close();
		
			return result;
		}catch(Exception e){
			logger.error("Error while getting likes "+ engage, e);
			return null;
		}
		
	}
	
	public void batchUpdate(List<Engagement> engagements){
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();	
			tx.begin();
			int i=0;
			for(Engagement engage : engagements){
				
				session.merge(engage);
				i++;
				if(i%40==0){
					session.flush();
					session.clear();
				}
			}
			
			tx.commit();
				
		}catch(Exception e){
			logger.error("Error while updating User "+ engagements, e);
			
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
	}
	
	public void batchDelete(Engagement engage){
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();	
			tx.begin();
			
			
				
			session.delete(engage);
			session.flush();
			session.clear();
				
			
			
			tx.commit();
				
		}catch(Exception e){
			logger.error("Error while updating User "+ engage, e);
			
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
	}

}
