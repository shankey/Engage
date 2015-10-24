package hibernate.util;

import com.engage.bao.UserInfoBAO;
import com.engage.common.Log;
import com.mchange.v2.log.MLevel;

import hibernate.bean.Post;
import hibernate.bean.User;
import hibernate.dao.PostDAO;
import hibernate.dao.UserDAO;
import poller.DBPoller;
import poller.QueuePoller;
 
 
public class HibernateTestApp {
	
	public static void main(String[] args) {
////		new TimelineBAO().getTimelineData("781685528");
		new Log();
		
		
		Thread qThread;
		Thread dbThread;
		qThread = new Thread(new QueuePoller());
        dbThread = new Thread(new DBPoller());
     
       qThread.start();
       dbThread.start();
		
//		new UserInfoBAO().getUserData("781685528");

	}
}