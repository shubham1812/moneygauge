package moneyguage.Service.Util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

@Named
@SessionScoped
public class SocialProvider implements Serializable {

	private final Map<String, SocialServiceHandler> handlerMap = new ConcurrentHashMap<String, SocialServiceHandler>();

	public static class SocialServiceHandler {

		private final OAuth20Service socialLoginService;
		private final String socialNetworkUrl;
		private final String protectedResourceUrl;

		private final String firstNameKey;
		private final String emailKey;
		private final String lastNameKey;
		private final String genderKey;

		public SocialServiceHandler(OAuth20Service socialLoginService, String socialNetworkUrl,
				String protectedResourceUrl, String firstNameKey, String lastNameKey, String genderKey,
				String emailKey) {
			this.socialLoginService = socialLoginService;
			this.socialNetworkUrl = socialNetworkUrl;
			this.protectedResourceUrl = protectedResourceUrl;
			this.firstNameKey = firstNameKey;
			this.emailKey = emailKey;
			this.lastNameKey = lastNameKey;
			this.genderKey = genderKey;
		}

		public OAuth20Service getSocialLoginService() {
			return socialLoginService;
		}

		public String getSocialNetworkUrl() {
			return socialNetworkUrl;
		}

		public String getProtectedResourceUrl() {
			return protectedResourceUrl;
		}

		public String getFirstNameKey() {
			return firstNameKey;
		}

		public String getLastNameKey() {
			return lastNameKey;
		}

		public String getEmailKey() {
			return emailKey;
		}

		public String getGenderKey() {
			return genderKey;
		}
	}

	private static final String SOCIAL_NETWORKS = "SOCIAL_NETWORKS";

	public String buildUrl(String provider) {
		this.handlerMap.put(provider, getSocialNetworkUrl(provider));
		return this.handlerMap.get(provider).getSocialNetworkUrl();
	}


	private SocialServiceHandler getSocialNetworkUrl(String provider) {

		/*
		 * String clientId = ""; String secret = ""; String redirectUrl = "";
		 */

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		/*if ((request.getParameter("page") != null && request.getParameter("page").equals("search"))
				|| (PrettyContext.getCurrentInstance().getCurrentMapping() != null
						&& PrettyContext.getCurrentInstance().getCurrentMapping().getPattern().equals("/search"))) {
			redirectUrl = redirectUrl.concat("&page=search");
		}*/

		if ("facebook".equalsIgnoreCase(provider)) {
			return OauthProvider.buildFacebookUrl("109788459924721", "6c7c9c42e53fbce3c1f39a521a8cd810",
					"http://localhost:8080/portfolio/callback?provider=facebook");
		}
		if ("google".equalsIgnoreCase(provider)) {
			return OauthProvider.buildGoogleUrl(
					"1087717293883-f4fp06aurtfg6vc99v4imqhjip4s7sst.apps.googleusercontent.com",
					"gCd_aTZ76b1OB45DCvxQeIHE", "http://localhost:8080/portfolio/callback?provider=google");
		}
		if ("linkedin".equalsIgnoreCase(provider)) {
			return OauthProvider.buildLinkedInUrl("860i2npqa3gb4q", "parVACqlBLEA5MZL",
					"http://localhost:8080/portfolio/callback?provider=linkedin");
		}
		if ("twitter".equalsIgnoreCase(provider)) {
			throw new UnsupportedOperationException(provider + " is not implemented yet");
		}
		throw new UnsupportedOperationException(provider + " is not implemented yet");
	}

	public Map<String, String> getSocialData(String code, String provider) {
		try {
			// get handler from the map using the passed provider key
			final OAuth2AccessToken accessToken = this.handlerMap.get(provider).getSocialLoginService()
					.getAccessToken(code);

			final OAuthRequest request = new OAuthRequest(Verb.GET,
					this.handlerMap.get(provider).getProtectedResourceUrl());
			this.handlerMap.get(provider).getSocialLoginService().signRequest(accessToken, request);
			final Response response = this.handlerMap.get(provider).getSocialLoginService().execute(request);
			Map<String, String> dataReturnedBySocialProvider = new ObjectMapper().readValue(response.getBody(),
					HashMap.class);
			Map<String, String> result = new HashMap<String, String>();
			result.put("first_name", dataReturnedBySocialProvider.get(this.handlerMap.get(provider).getFirstNameKey()));
			result.put("last_name", dataReturnedBySocialProvider.get(this.handlerMap.get(provider).getLastNameKey()));
			result.put("gender", dataReturnedBySocialProvider.get(this.handlerMap.get(provider).getGenderKey()));
			result.put("email", dataReturnedBySocialProvider.get(this.handlerMap.get(provider).getEmailKey()));
			return result;
		} catch (Exception ex) {
		}
		return null;
	}
}
