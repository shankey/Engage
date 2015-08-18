package hibernate.util;
 
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import hibernate.bean.JiniboxSkuDetails;
import hibernate.bean.Test;
import hibernate.bean.Url;

import org.hibernate.Session;
 
public class HibernateTestApp {
	
	public static void main2(String[] args) {
		System.out.println("Maven + Hibernate + Oracle");
		
 
		JiniboxSkuDetailsDAO dao = JiniboxSkuDetailsDAO.getJiniboxSkuDetailsDAO();
		
		JiniboxSkuDetails details = new JiniboxSkuDetails();
		System.out.println("result set -> "+ dao.getJiniBoxDetail(details).size());
		
		for(JiniboxSkuDetails url: dao.getJiniBoxDetail(details)){
			System.out.println(url);
		}
		

		
	}
}