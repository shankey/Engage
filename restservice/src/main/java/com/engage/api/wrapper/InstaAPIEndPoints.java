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
	public static final String ACCESS_TOKEN = "1981378059.47b3f0d.b673deeeedf941d294d383aa6db9da59";

}
