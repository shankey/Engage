package com.engage.api.wrapper;

public class InstaAPIEndPoints {
	
	public static final String SCHEME_HTTPS = "https://";
    public static final String BASE_URL = "api.instagram.com";

    public static final String POST_LIKES = "/v1/media/{media-id}/likes";
    public static final String POST_COMMENTS = "/v1/media/{media-id}/comments";
    public static final String TIMELINE_FEED = "/v1/users/{user-id}/media/recent";
    public static final String INSTA_FOLLOWERS = "/v1/users/{user-id}/followed-by";
    public static final String PROFILE_BIO = "/v1/users/{user-id}";

	public static final String TIMELINE_URL = "/v1/users/781685528/media/recent";

//	public static final String ACCESS_TOKEN = "1981378059.47b3f0d.b673deeeedf941d294d383aa6db9da59";
	
	public static String[] accessTokenArray = new String[6];{
		accessTokenArray[0] = "1981378059.47b3f0d.b673deeeedf941d294d383aa6db9da59";
		accessTokenArray[1] = "1981378059.1fb234f.4a3c1f2247574dbba368c6fd97770413";
		accessTokenArray[2] = "1684391253.1fb234f.a8428e90016949c9be89f43741d8aca9";
		accessTokenArray[3] = "2237079458.1fb234f.a4b171ec6df54c6dbfe0fedf328086e4";
		accessTokenArray[4] = "2129444282.1fb234f.68a01dfff8be49d58d449517b640c5f5 ";
		accessTokenArray[5] = "50856010.1fb234f.f483d43ea6f843eb847409527b539922";
	}
	public static int currentAccessTokenNo = -1; 
	
	public static String getAccessToken() {
		currentAccessTokenNo++;
		if(currentAccessTokenNo == accessTokenArray.length) {
			currentAccessTokenNo = 0;
		}
		return accessTokenArray[currentAccessTokenNo];
	}

}
