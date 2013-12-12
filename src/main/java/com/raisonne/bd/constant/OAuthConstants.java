/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.constant;

import org.scribe.model.Token;

/**
 * A wrapper class which will hold all static final constant values being used OAuth
 * mechanism.
 * Purpose of this class is to clean up the code and to define all constants in a centeral place
 * 
 * @author Umesh Awasthi
 * @version 1.0
 * @since 04-12-2011
 *
 */
public class OAuthConstants {
	
	/**
	 * Constant defined to stop replay attack using OpenID
	 */
	public static final long ONE_HOUR = 3600000L;
	public static final long TWO_HOUR = ONE_HOUR * 2L;
	
	/**
	 * Key to store openid mac in the session so as to retrieve back the same once user is being redirected back
	 */
	public static final String ATTR_MAC = "openid_mac";
	
	/**
	 * Key to store openid alias in the session so as to retrieve back the same once user is being redirected back
	 */
	public static final String ATTR_ALIAS = "openid_alias";
	
	/**
	 * After user has authorize there self with the OAuth service provider.That provider needs to be informed where 
	 * to redirect back user.
	 * call back URL tell OAuth provider where to redirect back the user
	 */
	public static final String CALLBACK_URL = "http://travellingrants.com/blood_donors/oAuthCallBackAction?service_provider_name=";
	
	/**
	 * OpenId provider need releam which indicate the party trying to authenticate user.
	 */
	public static final String YAHOO_OPENID_RELEAM = "http://travellingrants.com";
	
	/**
	 * For some OAuth Service provider Token values should be provided as null
	 * e.g
	 * <li>Facebook</li>
	 * <li>MSN</li>
	 */
	public static final Token EMPTY_TOKEN = null;
	
	/**
	 * Key that will be used to store request token in to session
	 * so that the same can be retrieve back from the session once user will be
	 * redirected back from the OAuth/OpenID service provider.
	 */
	public static final String REQUEST_TOKEN="request_token";
	
	/**
	 * Key which will be used to store user profile being obtained from 
	 * OAuth system once user verify them self.This will only be used for
	 * new user who are not registered.Once registration process start object will be
	 * removed from the session
	 */
	public static final String OAUTH_USERPROFILE_DATA="oauth_user_profile";
	

}
