package com.engage.bao;

import hibernate.bean.User;
import hibernate.dao.PostDAO;
import hibernate.dao.UserDAO;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.api.wrapper.InstaAPIWrapper;
import com.engage.common.Utility;

public class UserInfoBAO {
	private static Logger logger = Logger.getLogger(UserInfoBAO.class);
	UserDAO udao = UserDAO.getUserDao();
	
	public void getUserData(String userId) throws Exception{
		String response = InstaAPIWrapper.call(InstaAPIEndPoints.PROFILE_BIO.replaceAll("\\{user-id\\}", userId), 
				InstaAPIEndPoints.getAccessToken());
		System.out.println("bansal "+response);
		
		if(response==null){
			return;
		}
		
		JSONObject object=null;
		try {
			object = Utility.convertToJSON(response);
			
		} catch (ParseException e) {
			logger.error("Unable to parse JSON", e);
		}
		
		JSONObject dataJsonObj = (JSONObject)object.get("data");
		
		User user = new User();
		user.setUserId(userId);
		
		
		User existingUser = udao.getUserDetails(user);
		if(existingUser!=null){
			if(dataJsonObj != null) {
				existingUser.setHandle((String)dataJsonObj.get("username"));
				existingUser.setBioData((String)dataJsonObj.get("bio"));
				udao.update(existingUser);
			}
		}else{
			if(dataJsonObj != null) {
				user.setHandle((String)dataJsonObj.get("username"));
				user.setBioData((String)dataJsonObj.get("bio"));
				udao.update(user);
			}
			
		}
		
		
		
		System.out.println("bansal "+response);
	}
}
