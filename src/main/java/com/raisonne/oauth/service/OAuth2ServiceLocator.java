/**
 * 
 */
package com.raisonne.oauth.service;

/**
 * @author Admin
 *
 */
public interface OAuth2ServiceLocator {
  public OAuth2ServiceInterface lookup(final String serviceName);
}
