/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.oauth.config;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * This class is responsible for Contacting the OAuth service provider to get a request token
 * This request token is first step to start hand shaking process to acess user prublic profile
 * and validate your user with Oauth service
 * 
 * @author Umesh Awasthi
 * @version 1.0
 * @since 24-11-2011
 *
 */
public class OAuthGetRequestToken {

	
	/**
	 * Contact OAuth service provider for a request token. OAuth service provider verifies that the requesting application has been registered with them 
	 * or is using an approved signature. 
	 * The request token acquired with this request must be first authorized by the end user and then exchanged for an access token. 
	 * A request token cannot be used to get access to a OAuth service provider service.
	 * 
	 * make sure to preserve request token being given by the service as the same request token should be used to 
	 * get AccessToken to access services.
	 * 
	 * @param {@link OAuthService} for respected service provider
	 * @return Token being obtained from respected service provider
	 */
	public Token getRequestToken(OAuthService oAuthService){
		
		Token requestToken = oAuthService.getRequestToken();
		return requestToken;
		
	}
	
	/**
	 * @param serviceProviderPrefix for e.g google, yahoo
	 * @param authrizationToken
	 * @return Authorization URL where user will be redirected to authorize him/her self
	 */
	public String getAuthorizationUrl(OAuthService oAuthService, Token token){
		
		return  oAuthService.getAuthorizationUrl(token); 
							
	}
}
