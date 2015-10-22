package hibernate.dao;

import hibernate.bean.Post;
import hibernate.bean.User;
import hibernate.util.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class PostDAO {
	
	private static Logger logger = Logger.getLogger(PostDAO.class);
	private static PostDAO dao = new PostDAO();
	
	private PostDAO(){
		
	}
	
	public static PostDAO getPostDao(){
		return dao;
	}
	
	public Post getPostDetails(Post post){
		
		try{

			Session session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(Post.class);
			
			if(post.getPostId()!=null){
				cr.add(Restrictions.le("postId", post.getPostId()));
			}
			
			if(post.getOwnerId()!=null){
				cr.add(Restrictions.le("userId", post.getOwnerId()));
			}

			Post result = (Post)cr.uniqueResult();
			session.close();
		
			return result;
		}catch(Exception e){
			logger.error("Error while getting post "+ post, e);
			return null;
		}
		
	}
	
	
	public void update(Post post){
		Session session=null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.getTransaction();	
			tx.begin();
			session.merge(post);
			tx.commit();
				
		}catch(Exception e){
			logger.error("Error while updating User "+ post, e);
			
		}finally{
			if(session!=null){
				session.flush();
				session.close();
			}
		}
	}
	

}
