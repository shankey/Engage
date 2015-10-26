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
	

	public static String[] accessTokenArray = new String[]{
		"1981378059.47b3f0d.b673deeeedf941d294d383aa6db9da59", "1981378059.1fb234f.4a3c1f2247574dbba368c6fd97770413",
		"1684391253.1fb234f.a8428e90016949c9be89f43741d8aca9", "2237079458.1fb234f.a4b171ec6df54c6dbfe0fedf328086e4",
		"2129444282.1fb234f.68a01dfff8be49d58d449517b640c5f5", "2252074779.1fb234f.71be2229504d43e4ac133d7abb30b649"};

	public static int currentAccessTokenNo = -1; 
	
	public static String getAccessToken() {
		currentAccessTokenNo++;
		if(currentAccessTokenNo >= accessTokenArray.length) {
			currentAccessTokenNo = 0;
		}
		return accessTokenArray[currentAccessTokenNo];
	}


}
