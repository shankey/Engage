package com.engage.api.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class CallAPI(String api, HashMap<String, String>()){
	api + forEach(string, value)->append
	//return JSON;
}

public class InstaAPIWrapper {

	public static void main(String[] args) {
		String resourceURL = "https://api.instagram.com/v1/users/1981378059/?access_token=1981378059.47b3f0d.b673deeeedf941d294d383aa6db9da59";

		HttpGet get = new HttpGet(resourceURL);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		int code = -1;
		try {
			response = client.execute(get);
			if(response.getEntity().getContentLength() > 0) {
				StringBuilder sb = new StringBuilder();
				try {
				    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 65728);
				    String line = null;

				    while ((line = reader.readLine()) != null) {
				        sb.append(line);
				    }
				}
				catch (IOException e) { e.printStackTrace(); }
				catch (Exception e) { e.printStackTrace(); }

				System.out.println("finalResult " + sb.toString());
			}
			code = response.getStatusLine().getStatusCode();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			get.releaseConnection();
		}
	}
	
}
