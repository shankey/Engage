package hibernate.dao;

import java.util.List;

import hibernate.bean.User;
import hibernate.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDAO {
	
	private static Logger logger = Logger.getLogger(UserDAO.class);
	private static UserDAO dao = new UserDAO();
	
	private UserDAO(){
		
	}
	
	public static UserDAO getUserDao(){
		return dao;
	}
	
	public User getUserDetails(User user){
		
		try{

			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(User.class);
			
			
			cr.add(Restrictions.le("userId", user.getUserId()));

			User result = (User)cr.uniqueResult();
		
			session.close();
		
			return result;
		}catch(Exception e){
			logger.error("Error while getting User "+ user, e);
			return null;
		}
	}
	
	public List<User> zeroHandles(User user){
		
		try{

			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(User.class);
			
			cr.setMaxResults(100);
			cr.add(Restrictions.le("status", user.getStatus()));

			List<User> result = (List<User>)cr.list();
		
			session.close();
		
			return result;
		}catch(Exception e){
			logger.error("Error while getting User "+ user, e);
			return null;
		}
		
	}
	
	
	public void update(User user){
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();	
			tx.begin();
			session.merge(user);
			tx.commit();
				
		}catch(Exception e){
			logger.error("Error while updating User "+ user, e);
			
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
	}
	

}
