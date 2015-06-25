package hibernate.util;
 
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import hibernate.bean.Test;
import hibernate.bean.Url;

import org.hibernate.Session;
 
public class HibernateTestApp {
	
	public static void main2(String[] args) {
		System.out.println("Maven + Hibernate + Oracle");
		
 
		UrlDAO dao = new UrlDAO();
		System.out.println("result set -> "+dao.getOutdatedUrlDetails().size());
		
		for(Url url: dao.getOutdatedUrlDetails()){
			System.out.println(url);
		}
		

		
	}
}