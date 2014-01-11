/**
 * 
 */
package com.raisonne.oauth.service.api;

import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.oauth.config.OAuthSignInException;
import com.raisonne.oauth.data.OAuthUserProfileData;
import com.raisonne.oauth.data.parser.JsonDataParser;

/**
 * @author Umesh
 *
 */
public class YahooOAuthStrategy implements OauthClientAPI{
	
	@Autowired
	private JsonDataParser jsonDataParser;
	
	protected final static Logger LOG=LoggerFactory.getLogger(MicrosoftOAuthStrategy.class);
	private String callbackURL="";
	@Override
	public OAuthClientRequest getRedirectionObj(){
		OAuthClientRequest request=null;
		callbackURL=BloodGroupUtils.getParameter("app.callback.url");
		LOG.info("Found application call back URL as {} ",callbackURL);
		try {
			LOG.info("Creating Client request object for MICROSOFT");
			request = OAuthClientRequest.authorizationProvider(OAuthProviderType.YAMMER).
					  setClientId(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.MICROSOFT.getProviderName())+".apiKey")).
					  setResponseType(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.GOOGLE.getProviderName())+".responseType")).
					  setScope(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.MICROSOFT.getProviderName())+".scope")).
					  setRedirectURI(callbackURL).buildQueryMessage();
			
		} catch (OAuthSystemException e) {
			
			LOG.info("Error while creating OAuthClientRequest object for MICROSOFT" , e);
		}
		return request;
	}
	
	@Override
	public OAuthClientRequest getAccessTokenOAuthObj(final String authorizationCode) {
		OAuthClientRequest request=null;
		callbackURL=BloodGroupUtils.getParameter("app.callback.url");
		try {
			LOG.info("Creating Client request object for MICROSOFT");
			request = OAuthClientRequest.tokenProvider(OAuthProviderType.MICROSOFT).
					  setGrantType(GrantType.AUTHORIZATION_CODE).
					  setClientId(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.MICROSOFT.getProviderName())+".apiKey")).
					  setClientSecret(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.MICROSOFT.getProviderName())+".apiSecret")).
					  setRedirectURI(callbackURL).
					  setCode(authorizationCode).
					  setScope(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.MICROSOFT.getProviderName())+".scope"))
					  .buildBodyMessage();
			
		} catch (OAuthSystemException e) {
			
			LOG.info("Error while creating OAuthClientRequest object for MICROSOFT" , e);
		}
		return request;
	}

	@Override
	public OAuthUserProfileData getUserProfileData(final OAuthClientRequest request) throws OAuthSignInException {
		LOG.info("Starting process to fetch acess token and user data");
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthAccessTokenResponse oAuthResponse=null;
		try {
			
			oAuthResponse=oAuthClient.accessToken(request);
				if(oAuthResponse !=null){
					String accessToken = oAuthResponse.getAccessToken();
					Long expiresIn = oAuthResponse.getExpiresIn();
					LOG.info("Successfully fected response token and expirary value : {} {} ",accessToken,expiresIn);
					OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.MICROSOFT.getProviderName())+".datarequest_url"))
			         .setAccessToken(accessToken).buildHeaderMessage();
					OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
					if (resourceResponse.getResponseCode() != 200) {
						LOG.error("OAuth failed, we have got the following error code "
								+ resourceResponse.getResponseCode());
						throw new OAuthSignInException(
								"There seems some problem in authenticate yourself with MICROSOFT");
					}
					return (OAuthUserProfileData) jsonDataParser.parseJson(
							resourceResponse.getBody(), OAuthUserProfileData.class);
			}
			
			
		 
		} catch (OAuthSystemException e) {
			LOG.error("Error while fetching access token for MICROSOFT..",e);
			
		} catch (OAuthProblemException e) {
			LOG.error("Error while fetching access token for MICROSOFT..",e);
		}
		//GoogleTokenResponse tok;
		return null;
	}

}
