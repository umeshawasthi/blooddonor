package com.raisonne.bd.custom.preAuthentication;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.raisonne.bd.action.donor.DonorSearch;
import com.raisonne.bd.pojo.DonorProfile;
import com.raisonne.bd.pojo.DonorRoles;
import com.raisonne.bd.service.donor.DonorService;

public class UserAssembler {
	static	Logger log = Logger.getLogger(UserAssembler.class);
	
	static UserDetails buildUserFromUserEntity(DonorProfile donorProfile){
				
		
		String username = donorProfile.getEmail();
	    String password = "N/A";
	    String donorRoles=donorProfile.getDonorRoles().getDonorRoles();
	    boolean enabled = donorProfile.getDonorRoles().isUserEnabled();
	    boolean accountNonExpired = donorProfile.getDonorRoles().isAccountExpired();
	    boolean credentialsNonExpired = donorProfile.getDonorRoles().isCredentialExpired();
	    boolean accountNonLocked = donorProfile.getDonorRoles().isAccountLocked();

	    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    if(donorRoles.contains(","))
	    {
	    	log.info("Retriving authorities for multiple user roles");
	    String []roleArray=StringUtils.commaDelimitedListToStringArray(donorRoles);
	    for (String role : roleArray) {
	      authorities.add(new GrantedAuthorityImpl(role));
	    }
	    }
	    else
	    {
	    	log.info("Retriving authorities for single user role");
	    	authorities.add(new GrantedAuthorityImpl(donorRoles));
	    }
	    User user = new User(username, password, enabled,accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	    return user;
	}

	
	
}
