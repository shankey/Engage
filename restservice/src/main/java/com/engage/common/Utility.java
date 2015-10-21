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

}