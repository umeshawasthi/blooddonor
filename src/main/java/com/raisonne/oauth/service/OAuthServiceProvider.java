/**
 * 
 */
package com.raisonne.oauth.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raisonne.oauth.service.api.OauthClientAPI;

/**
 * @author Admin
 *
 */
public class OAuthServiceProvider {
	
	private static final Logger LOG=LoggerFactory.getLogger(OAuthServiceProvider.class);
	private Map<String, ? extends OauthClientAPI> oAuthServiceAPI;
	
	@SuppressWarnings("unchecked")
	public <T> T getSelectedOAuthClientAPI(final String apiClassName){
		LOG.info("returning Oauth client API class for key {} ",apiClassName);
		return (T) oAuthServiceAPI.get(apiClassName);
	}

	public Map<String, ? extends OauthClientAPI> getoAuthServiceAPI() {
		return oAuthServiceAPI;
	}

	public void setoAuthServiceAPI(
			Map<String, ? extends OauthClientAPI> oAuthServiceAPI) {
		this.oAuthServiceAPI = oAuthServiceAPI;
	}

	

}
