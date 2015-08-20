package hibernate.util;
 
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import hibernate.bean.JiniboxSkuDetails;
import hibernate.bean.Test;
import hibernate.bean.Url;

import org.hibernate.Session;

import pricing.JiniboxRules;
 
public class HibernateTestApp {
	
	public static void main2(String[] args) {
		System.out.println("Maven + Hibernate + Oracle");
		
 
		JiniboxSkuDetailsDAO dao = JiniboxSkuDetailsDAO.getJiniboxSkuDetailsDAO();
		
		JiniboxSkuDetails details = new JiniboxSkuDetails();
		
		new JiniboxRules().generateOurPrice("SKU2");
		
	}
}