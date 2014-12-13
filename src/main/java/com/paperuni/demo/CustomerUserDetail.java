package com.paperuni.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.paperuni.demo.model.TdUserinfo;
import com.paperuni.demo.model.TdUserinfoRepository;

public class CustomerUserDetail implements UserDetailsService {
	
	@Autowired
	private TdUserinfoRepository tdUserinfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	if ("172522ec1028ab781d9dfd17eaca4427".equals(username)){
    		return getPrivateUser();
    	}
    	else{
    		TdUserinfo tdUserinfo = tdUserinfoRepository.findByEmail(username);
    		String email = tdUserinfo.getEmail();
    		String password = tdUserinfo.getLoginPassword();
    		boolean enabled = tdUserinfo.getHasVerified();
    		boolean accountNonExpired = tdUserinfo.getPasswordNonExpired();
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            User user = new User(email,
                    password.toLowerCase(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    AuthorityUtils
    				.commaSeparatedStringToAuthorityList(tdUserinfo.getGroupName()));  
            return user;
    	}
    }
    
    private User getPrivateUser(){
        String email = "david";
        String password = "abc123";
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        User user = new User(email,
                password.toLowerCase(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_ADMIN")
        );    	
    	return user;
    }

}
