package com.engage.api.wrapper;

import java.util.HashMap;

//public class CallAPI(String api, HashMap<String, String>()){
//	api + forEach(string, value)->append
//	//return JSON;
//}

public class InstaAPIWrapper {

	public static void main(String[] args) {
//		String resourceURL = "https://api.instagram.com/v1/users/1981378059/?access_token=1981378059.47b3f0d.b673deeeedf941d294d383aa6db9da59";
		
		String url = null;
        HashMap<String, String> postData = new HashMap<>();
        postData.put("access_token", "1981378059.47b3f0d.b673deeeedf941d294d383aa6db9da59");
        
        url = InstaAPIEndPoints.SCHEME_HTTPS + InstaAPIEndPoints.TIMELINE_URL;

//        try {
//            apiClient.writePostData(apiClient.getPostDataString1(postData));
//        }catch (Exception e){
//            Log.d("SERVERUTILITIES/POST", "JsonException");
//        }
        String response = null;
        try{
            ApiClient apiClient = new ApiClient(ApiClient.CallType.HTTPS_GET, url, postData, false);
        	response = apiClient.executeCall();
            apiClient.disconnect();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        if(response != null)
        	System.out.println(response);
	}
}
