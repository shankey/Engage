package com.engage.common;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Utility {
	
	public static int SECOND=1000;
	
	public static Boolean isNull(String str){
		
		if(str==null || str.isEmpty()){
			return true;
		}
		
		return false;
	}
	

	
	public static String cleanWebString(String sp){
		sp = sp.replace("\u00a0", " ");
		sp = sp.trim();
		return sp;
	}
	
	public static Long get30DayOldTimeStamp(){
		long unixTime = System.currentTimeMillis() / 1000L;
		long unixTime30DayPrevious = unixTime - (100 * 24 * 60 * 60);
		
		return unixTime30DayPrevious;
	}
	
	public static JSONObject convertToJSON(String str) throws ParseException{
		JSONParser parser = new JSONParser();
      
			JSONObject object = (JSONObject)parser.parse(str);
			System.out.println(object);
			
			return object;

	}

}