package hibernate.dao;

import java.util.List;

import hibernate.bean.Likes;
import hibernate.bean.User;
import hibernate.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class LikesDAO {
	
	private static Logger logger = Logger.getLogger(LikesDAO.class);
	private static LikesDAO dao = new LikesDAO();
	
	private LikesDAO(){
		
	}
	
	public static LikesDAO getLikesDao(){
		return dao;
	}
	
	public List<Likes> getLikesDetails(Likes likes){
		
		try{

			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(User.class);
			
			cr.add(Restrictions.le("postId", likes.getPostId()));

			List<Likes> result = (List<Likes>)cr.list();
			session.close();
		
			return result;
		}catch(Exception e){
			logger.error("Error while getting likes "+ likes, e);
			return null;
		}
		
	}
	
	public void batchUpdate(List<Likes> likes){
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();	
			tx.begin();
			int i=0;
			for(Likes like : likes){
				
				session.merge(like);
				i++;
				if(i%40==0){
					session.flush();
					session.clear();
				}
			}
			
			tx.commit();
				
		}catch(Exception e){
			logger.error("Error while updating User "+ likes, e);
			
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
	}
	
	
	
	public void update(Likes likes){
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();	
			tx.begin();
			session.merge(likes);
			tx.commit();
				
		}catch(Exception e){
			logger.error("Error while updating Likes "+ likes, e);
			
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
	}
	
}
