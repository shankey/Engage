package com.engage.api.wrapper;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class ApiClient {
	public enum CallType {
        HTTPS_GET,
        HTTPS_POST,
        HTTPS_DELETE
    }

    private int mResponseCode = -1;
    private int mReadTimeout = 15000;
    private int mConnectTimeout = 15000;
    private HttpsURLConnection connHttps;
    private CallType callType;
    private OutputStream outputStream;
    private BufferedWriter writer;

    public int getResponseCode() {
        return mResponseCode;
    }

    public void setReadTimeout(int readTimeout) {
        this.mReadTimeout = readTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.mConnectTimeout = connectTimeout;
    }

//    public ApiClient(CallType callType, String apiUrl) throws IOException {
//        init(callType, apiUrl);
//    }
//
//    //use when you need to have duplicate keys in url query parameters
//    // "key=value1&key=value2" ,
//    // avoids hashmap single key override issue.
//    public ApiClient(CallType callType, String apiUrl, boolean includeDefaultParams) throws IOException {
//
//        if(includeDefaultParams)
//        {
//            Locale locale = Locale.getDefault();
//            Uri uri = Uri.parse(apiUrl).buildUpon().appendQueryParameter("request_locale", locale.getLanguage()).build();
//            apiUrl = uri.toString();
//        }
//
//        init(callType, apiUrl);
//    }

    public ApiClient(CallType callType, String apiUrl, HashMap<String, String> urlParams, boolean defaultUrlParams) throws IOException {

        if(urlParams != null)
            apiUrl = appendUrlParams(apiUrl, urlParams, defaultUrlParams);

        init(callType, apiUrl);
    }

    private void init(CallType callType, String apiUrl) throws IOException {

        this.callType = callType;
        URL url = new URL(apiUrl);
        switch (callType) {
            case HTTPS_GET:
                connHttps = (HttpsURLConnection) url.openConnection();
                connHttps.setReadTimeout(mReadTimeout);
                connHttps.setConnectTimeout(mConnectTimeout);
                connHttps.setRequestMethod("GET");
                try {
                    // Create the SSL connection
                    SSLContext sc;
                    sc = SSLContext.getInstance("TLS");
                    sc.init(null, null, new java.security.SecureRandom());
                    connHttps.setSSLSocketFactory(sc.getSocketFactory());
                } catch (GeneralSecurityException genE) {
                    throw new IOException();
                }
                break;
            case HTTPS_POST:
                connHttps = (HttpsURLConnection) url.openConnection();
                connHttps.setReadTimeout(mReadTimeout);
                connHttps.setConnectTimeout(mConnectTimeout);
                connHttps.setRequestMethod("POST");
//                connHttps.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                connHttps.setRequestProperty("access-token", ServerUtilities.access_token);
                connHttps.setChunkedStreamingMode(0);
                connHttps.setDoInput(true);
                connHttps.setDoOutput(true);
                try {
                    // Create the SSL connection
                    SSLContext sc;
                    sc = SSLContext.getInstance("TLS");
                    sc.init(null, null, new java.security.SecureRandom());
                    connHttps.setSSLSocketFactory(sc.getSocketFactory());
                } catch (GeneralSecurityException genE) {
                    throw new IOException();
                }
                break;
            case HTTPS_DELETE:
                connHttps = (HttpsURLConnection) url.openConnection();
                connHttps.setReadTimeout(mReadTimeout);
                connHttps.setConnectTimeout(mConnectTimeout);
//                connHttps.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                connHttps.setRequestProperty("access-token", ServerUtilities.access_token);
//                connHttps.setRequestProperty("registrationId", ServerUtilities.registrationId);
                connHttps.setRequestMethod("DELETE");
                try {
                    // Create the SSL connection
                    SSLContext sc;
                    sc = SSLContext.getInstance("TLS");
                    sc.init(null, null, new java.security.SecureRandom());
                    connHttps.setSSLSocketFactory(sc.getSocketFactory());
                } catch (GeneralSecurityException genE) {
                    throw new IOException();
                }
                break;
        }
    }

    public String executeCall() throws IOException {
    	InputStream _is;
    	mResponseCode = connHttps.getResponseCode();
        if(mResponseCode == 200) {
        	_is = connHttps.getInputStream();
        }
        else {
        	_is = connHttps.getErrorStream();
        }
        return readStream(_is);
    }

    public void disconnect() {
        connHttps.disconnect();
    }

    public void changeAccessToken() {
    	
    }
//    public String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
//        StringBuilder result = new StringBuilder();
//        boolean first = true;
//        for(Map.Entry<String, String> entry : params.entrySet()){
//            if (first)
//                first = false;
//            else
//                result.append("&");
//
//            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
//        }
//        return result.toString();
//    }

//    public String getPostDataString1(HashMap<String, String> params) throws JSONException{
//        JSONObject deviceJson = new JSONObject();
//        JSONObject data = new JSONObject();
//        for(Map.Entry<String, String> entry : params.entrySet()){
//            data.put(entry.getKey(), entry.getValue());
//        }
//        deviceJson.put("device", data);
//        Log.d("JSON", deviceJson.toString());
//        return deviceJson.toString();
//    }

    public void writePostData(String data) throws IOException{
    	if(writer == null) {
            outputStream = connHttps.getOutputStream();
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        }
        writer.write(data);
    }

    private String appendUrlParams(String url, HashMap<String, String> params, boolean defaultParams) throws UnsupportedEncodingException{
        if(defaultParams) {
            Locale locale = Locale.getDefault();
            if(params == null)
                params = new HashMap<>();
            params.put("request_locale", locale.getLanguage());
        }

        StringBuilder result = new StringBuilder(url);
        result.append("?");
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

//    public void insertHeaders(HashMap<String, String> headers) {
//    	for (Map.Entry<String, String> entry : headers.entrySet()) {
//            connHttps.setRequestProperty(entry.getKey(), entry.getValue());
//        }
//    }

    private String readStream(InputStream in) throws IOException {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
