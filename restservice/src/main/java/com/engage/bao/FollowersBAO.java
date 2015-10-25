package com.engage.bao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.api.wrapper.InstaAPIWrapper;
import com.engage.common.Utility;

import hibernate.bean.User;
import hibernate.dao.UserDAO;

public class FollowersBAO {
	private static Logger logger = Logger.getLogger(FollowersBAO.class);
	//	UserDAO udao = UserDAO.getUserDao();
	public void getFollowersData(String userId){
		String nextCursor = null;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("Followers-ID.txt", "UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		while(true){
			String response = InstaAPIWrapper.getFollowers(InstaAPIEndPoints.INSTA_FOLLOWERS.replaceAll("\\{user-id\\}", userId), 
					InstaAPIEndPoints.getAccessToken(), nextCursor);
			if(response==null){
				break;
			}
			JSONObject object=null;
			try {
				object = Utility.convertToJSON(response);
			} catch (ParseException e) {
				logger.error("Unable to parse JSON", e);
			}
			JSONArray dataJson = (JSONArray)object.get("data");
			for(int i=0; i<dataJson.size(); i++){
				JSONObject dataJsonObj = (JSONObject)dataJson.get(i);
				//	User user = new User();
				//
				//	//Storing the userId
				//	user.setUserId((String)dataJsonObj.get("id"));
				//	user.setStatus(0);
				//
				//	udao.update(user);
				if(writer == null) {
					break;
				}
				writer.println((String)dataJsonObj.get("id"));
			}
			JSONObject paginationJson = (JSONObject) object.get("pagination");
			if(paginationJson==null){
				break;
			}
			//System.out.println(paginationJson);
			nextCursor = (String)paginationJson.get("next_cursor");
			if(nextCursor==null){
				break;
			}
		}
		if(writer != null) {
			writer.close();
		}
	}
}
