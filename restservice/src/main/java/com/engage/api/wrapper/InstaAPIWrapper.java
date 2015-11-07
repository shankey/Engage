package com.engage.api.wrapper;

import java.util.HashMap;

import ch.qos.logback.classic.Logger;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

public class InstaAPIWrapper {
	
	public static String call(String api, String accessToken) throws Exception{
		HashMap<String, String> postData = new HashMap<>();
        postData.put("access_token", accessToken);
        

        String url = InstaAPIEndPoints.SCHEME_HTTPS + InstaAPIEndPoints.BASE_URL + api;

        String response = null;
        try{
            ApiClient apiClient = new ApiClient(ApiClient.CallType.HTTPS_GET, url, postData, false);
        	response = apiClient.executeCall();
            apiClient.disconnect();
        } catch(Exception e) {
        	throw e;
        }
        
        if(response != null){
        	System.out.println(response);
        }
        
        return response;
        	
	}
	
	public static String callTimeLine(String api, String accessToken, Long timeStamp, String maxId){
		HashMap<String, String> postData = new HashMap<>();
        postData.put("access_token", accessToken);
        postData.put("min_timestamp", timeStamp.toString());
        if(maxId != null) {
            postData.put("max_id", maxId.toString());
        }
        
        String url = InstaAPIEndPoints.SCHEME_HTTPS + InstaAPIEndPoints.BASE_URL + api;

        String response = null;
        try{
            ApiClient apiClient = new ApiClient(ApiClient.CallType.HTTPS_GET, url, postData, false);
        	response = apiClient.executeCall();
            apiClient.disconnect();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        if(response != null){
        	//System.out.println(response);
        }
        
        return response;
        	
	}
	
	public static String getFollowers(String api, String accessToken, String nextCursor){
		HashMap<String, String> postData = new HashMap<>();
        postData.put("access_token", accessToken);
        if(nextCursor != null && !nextCursor.equals("")) {
            postData.put("cursor", nextCursor);
        }
        
        String url = InstaAPIEndPoints.SCHEME_HTTPS + InstaAPIEndPoints.BASE_URL + api;

        String response = null;
        try{
            ApiClient apiClient = new ApiClient(ApiClient.CallType.HTTPS_GET, url, postData, false);
        	response = apiClient.executeCall();
            apiClient.disconnect();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        if(response != null){
        	//System.out.println(response);
        }
        
        return response;
        	
	}	
		

	public static void main2(String[] args) {		
		
		long unixTime = System.currentTimeMillis() / 1000L;
		long unixTime30DayPrevious = unixTime - (30 * 24 * 60 * 60);
        String response = callTimeLine(InstaAPIEndPoints.TIMELINE_URL, "1981378059.47b3f0d.b673deeeedf941d294d383aa6db9da59", 
        		unixTime30DayPrevious, null);
        
        System.out.println(response);
        
//        JSONParser parser = new JSONParser();
//        try {
//			JSONObject object = (JSONObject)parser.parse(response);
//			System.out.println(object);
//			
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
