package com.paperuni.demo;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.paperuni.demo.model.TdUserinfo;
import com.paperuni.demo.model.TdUserinfoRepository;

public class CustomUser extends User {
	
	@Autowired
	TdUserinfoRepository tdUserinfoRepository;
	
	private final int userID;

	public int getUserID() {
		return userID;
	}
	
	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities, int userID)	{
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userID = userID;
	}	

	public TdUserinfo loadUserinfo(){
		TdUserinfo userinfo = tdUserinfoRepository.findOne(this.getUserID());
		return userinfo;
	}

}
