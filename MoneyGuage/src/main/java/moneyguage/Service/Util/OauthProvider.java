package moneyguage.Service.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.apis.LinkedInApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

import moneyguage.Service.Util.SocialProvider.SocialServiceHandler;

public final class OauthProvider {

    private static final String GOOGLE_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
    private static final String GOOGLE_PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json";

    private static final String FACEBOOK_SCOPE = "email";
    private static final String FB_PROTECTED_RESOURCE_URL = "https://graph.facebook.com/me?fields=first_name,last_name,gender,email";
    
    private static final String LINKEDIN_SCOPE = "r_basicprofile r_emailaddress";
    private static final String LINKEDIN_PROTECTED_RESOURCE_URL = "https://api.linkedin.com/v1/people/~:(email-address,id,first-name,last-name,public-profile-url)?format=json";

    public static SocialServiceHandler buildGoogleUrl(
            String GOOGLE_CLIENT_ID,
            String GOOGLE_CLIENT_SECRET,
            String GOOGLE_REDIRECT_URL) {
        final String secretState = "secret" + new Random().nextInt(999_999);

        OAuth20Service service = new ServiceBuilder()
                .apiKey(GOOGLE_CLIENT_ID)
                .apiSecret(GOOGLE_CLIENT_SECRET)
                .scope("email") // replace with desired scope
                .state(secretState)
                .scope(GOOGLE_SCOPE)
                .callback(GOOGLE_REDIRECT_URL)
                .build(GoogleApi20.instance());//LinkedInApi20.instance()
        final Map<String, String> additionalParams = new HashMap<>();
//        additionalParams.put("access_type", "offline");
        //force to reget refresh token (if usera are asked not the first time)
//        additionalParams.put("prompt", "consent");
        final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
        return new SocialServiceHandler(service, authorizationUrl, GOOGLE_PROTECTED_RESOURCE_URL,"given_name","family_name","gender","email");
    }

    public static SocialServiceHandler buildFacebookUrl(
            String FB_CLIENT_ID,
            String FB_CLIENT_SECRET,
            String FB_REDIRECT_URL) {
        final String secretState = "secret" + new Random().nextInt(999_999);

        OAuth20Service service = new ServiceBuilder()
                .apiKey(FB_CLIENT_ID)
                .apiSecret(FB_CLIENT_SECRET)
                .state(secretState)
                .callback(FB_REDIRECT_URL)
                .scope(FACEBOOK_SCOPE)
                .build(FacebookApi.instance());
        final Map<String, String> additionalParams = new HashMap<>();
        //force to reget refresh token (if users are asked not the first time)
        final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
        return new SocialServiceHandler(service, authorizationUrl, FB_PROTECTED_RESOURCE_URL,"first_name","last_name","gender","email");
    }
    
    public static SocialServiceHandler buildLinkedInUrl(
            String LINKEDIN_CLIENT_ID,
            String LINKEDIN_CLIENT_SECRET,
            String LINKEDIN_REDIRECT_URL) {
        final String secretState = "secret" + new Random().nextInt(999_999);

        OAuth20Service service = new ServiceBuilder()
                .apiKey(LINKEDIN_CLIENT_ID)
                .apiSecret(LINKEDIN_CLIENT_SECRET)
                .state(secretState)
                .callback(LINKEDIN_REDIRECT_URL)
                .scope(LINKEDIN_SCOPE)
                .build(LinkedInApi20.instance());
        final Map<String, String> additionalParams = new HashMap<>();
        //force to reget refresh token (if users are asked not the first time)
        final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
        return new SocialServiceHandler(service, authorizationUrl, LINKEDIN_PROTECTED_RESOURCE_URL,"firstName","lastName","","emailAddress");
    }
}

