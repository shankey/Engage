package hibernate.dao;

import java.util.List;

import hibernate.bean.Comments;
import hibernate.bean.Likes;
import hibernate.bean.Post;
import hibernate.bean.User;
import hibernate.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class CommentsDAO {
	
	private static Logger logger = Logger.getLogger(CommentsDAO.class);
	private static CommentsDAO dao = new CommentsDAO();
	
	private CommentsDAO(){
		
	}
	
	public static CommentsDAO getCommentsDao(){
		return dao;
	}
	
	public List<Comments> getCommentsDetails(Comments comments){
		
		try{

			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(Comments.class);
			
			cr.add(Restrictions.le("postId", comments.getPostId()));

			List<Comments> result = (List<Comments>)cr.list();
			session.close();
		
			return result;
		}catch(Exception e){
			logger.error("Error while getting likes "+ comments, e);
			return null;
		}
		
	}
	
	public void batchUpdate(List<Comments> comments){
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();	
			tx.begin();
			int i=0;
			for(Comments comment : comments){
				
				session.merge(comment);
				i++;
				if(i%50==0){
					session.flush();
					session.clear();
				}
			}
			
			tx.commit();
				
		}catch(Exception e){
			logger.error("Error while updating User "+ comments, e);
			
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
	}
	
	
	public void update(Comments comments){
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();	
			tx.begin();
			session.merge(comments);
			tx.commit();
				
		}catch(Exception e){
			logger.error("Error while updating Likes "+ comments, e);
			
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
	}
	
}
