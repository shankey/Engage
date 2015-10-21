package com.engage.common;



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
		long unixTime30DayPrevious = unixTime - (30 * 24 * 60 * 60);
		
		return unixTime30DayPrevious;
	}
	
//	public static convertToJSON(String str){
//		JsonParser parser = new JsonPar
//		
//		
//	}

}