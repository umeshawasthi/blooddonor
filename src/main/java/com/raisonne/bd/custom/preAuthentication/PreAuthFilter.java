package com.raisonne.bd.custom.preAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.raisonne.bd.constant.DonorServiceConstants;
import com.raisonne.bd.dto.donor.DonorCredentialDTO;

/**
 * This class is written to to extract the necessary information on the
 * principal from the incoming request, rather than to authenticate them.
 * External authentication systems may provide this information via request data
 * such as headers or cookies which the pre-authentication system can extract.
 * It is assumed that the external system is responsible for the accuracy of the
 * data and preventing the submission of forged values.
 * 
 * @author vivek
 * 
 */
public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

	Logger log = Logger.getLogger(PreAuthFilter.class);

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute(DonorServiceConstants.USER_CREDENTIAL) != null) {
			return "N/A";// ((DonorCredentialDTO)session.getAttribute(DonorServiceConstants.USER_CREDENTIAL)).getDonorPassword();
		} else
			return null;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute(DonorServiceConstants.USER_CREDENTIAL) != null) {
			log.info("Getting pre authenticated principal");
			return ((DonorCredentialDTO) session
					.getAttribute(DonorServiceConstants.USER_CREDENTIAL))
					.getDonorEmail();
		} else
			return null;
	}
}