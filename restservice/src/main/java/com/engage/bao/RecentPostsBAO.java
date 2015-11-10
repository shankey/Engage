package com.engage.bao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.api.wrapper.InstaAPIWrapper;
import com.engage.common.Utility;

import hibernate.bean.Post;
import hibernate.bean.User;

public class RecentPostsBAO {
	private static Logger logger = Logger.getLogger(RecentPostsBAO.class);
	
	public void getRecentPosts(String userId, int N) throws Exception{
		
		String maxId = null;
		
		while(true){
			String response;
			if(N == 0) {
				break;
			}
			else if(N >= 20) {
				response = InstaAPIWrapper.callTimeLine(InstaAPIEndPoints.TIMELINE_FEED.replaceAll("\\{user-id\\}", userId),
						InstaAPIEndPoints.getAccessToken(), null, maxId, null);
				N = N - 20;
			}
			else {
				response = InstaAPIWrapper.callTimeLine(InstaAPIEndPoints.TIMELINE_FEED.replaceAll("\\{user-id\\}", userId),
						InstaAPIEndPoints.getAccessToken(), null, maxId, N+"");
				N = 0;
			}
			
			
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
			//System.out.println(dataJson);
			for(int i=0; i<dataJson.size(); i++){
				JSONObject dataJsonObj = (JSONObject)dataJson.get(i);
				
				String postURL = (String)dataJsonObj.get("link");
				JSONObject captionObj = (JSONObject)dataJsonObj.get("caption");
				String captionText = (String)captionObj.get("text");
				
				System.out.println("Post " + i);
				System.out.println("URL is " + postURL);
				System.out.println("Caption is " + captionText);
				System.out.println();
			}
						
			JSONObject paginationJson = (JSONObject) object.get("pagination");
			
			if(paginationJson==null){
				break;
			}
			
			//System.out.println(paginationJson);
			maxId = (String)paginationJson.get("next_max_id");
			
			if(maxId==null){
				break;
			}
			
		}
		
	}
}
