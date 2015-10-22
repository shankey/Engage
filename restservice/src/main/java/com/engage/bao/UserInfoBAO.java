package com.engage.bao;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.api.wrapper.InstaAPIWrapper;
import com.engage.common.Utility;

public class UserInfoBAO {
	
	private static Logger logger = Logger.getLogger(TimelineBAO.class);
	
	public void getTimelineData(String id){
		
		String maxId = null;
		
		while(true){
			
			String response = InstaAPIWrapper.callTimeLine(InstaAPIEndPoints.TIMELINE_FEED.replaceAll("\\{user-id\\}", "781685528"), InstaAPIEndPoints.ACCESS_TOKEN, 
					Utility.get30DayOldTimeStamp(), maxId);
			
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
				System.out.println(dataJsonObj.get("id"));
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
