package com.raisonne.oauth.service.api;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;



import com.raisonne.oauth.config.OAuthSignInException;
import com.raisonne.oauth.data.OAuthUserProfileData;

public interface OauthClientAPI {

	public OAuthClientRequest getRedirectionObj();
	public OAuthClientRequest getAccessTokenOAuthObj(final String authorizationCode);
	public OAuthUserProfileData getUserProfileData(final OAuthClientRequest request) throws OAuthSignInException;
	
}
