package hibernate.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import poller.DBPoller;
import poller.QueuePoller;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.bao.RecentPostsBAO;
import com.engage.bao.TimelineBAO;
import com.engage.common.Log;
 
 
public class HibernateTestApp {
	
	public static void main(String[] args) throws Exception {
////		new TimelineBAO().getTimelineData("781685528");

		new Log();
		System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
		System.setProperty("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "INFO");
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		
//		
//		Thread qThread;
//		Thread dbThread;
//		qThread = new Thread(new QueuePoller());
//        dbThread = new Thread(new DBPoller());
//     
//       qThread.start();
//       dbThread.start();
		
//		System.out.println(InstaAPIEndPoints.getAccessToken());
//		System.out.println(InstaAPIEndPoints.getAccessToken());
//		System.out.println(InstaAPIEndPoints.getAccessToken());
//		System.out.println(InstaAPIEndPoints.getAccessToken());
//		System.out.println(InstaAPIEndPoints.getAccessToken());
//		System.out.println(InstaAPIEndPoints.getAccessToken());
//		
//		new UserInfoBAO().getUserData("781685528");
		
		new TimelineBAO().getTimelineData("2261333272", true);
		
//		new Log();
//		System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
//		System.setProperty("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "INFO");
//		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
//		
//		
//		Thread qThread;
//		Thread dbThread;
//		qThread = new Thread(new QueuePoller());
//        dbThread = new Thread(new DBPoller());
//     
//       qThread.start();
//       dbThread.start();
//		
//		new UserInfoBAO().getUserData("781685528");


	}
}
