package com.raisonne.bd.custom.preAuthentication;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raisonne.bd.pojo.DonorProfile;
import com.raisonne.bd.service.donor.DonorService;

@Service("myUserDetailsService")
public class UserDetailService implements AuthenticationUserDetailsService {
	Logger log = Logger.getLogger(UserDetailService.class);
	private DonorService donorService;

	
	@Override
	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		UserDetails userDetails = null;
		try {
			log.info("Getting pre authenticated user detail for user "+token.getName());
			DonorProfile DonorProfile = donorService
					.loadDonorRoleDetailsByEmaiId(token.getName());
			userDetails = UserAssembler.buildUserFromUserEntity(DonorProfile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (userDetails == null) {
			throw new UsernameNotFoundException("Could not load user: "
					+ token.getName());
		}
		
		return userDetails;
	}

	public DonorService getDonorService() {
		return donorService;
	}

	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}

}
