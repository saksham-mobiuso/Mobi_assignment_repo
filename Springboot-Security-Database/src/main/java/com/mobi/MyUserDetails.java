package com.mobi;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mobi.models.Authority;
import com.mobi.models.User;

public class MyUserDetails implements UserDetails{
	
	private String userName;
	private String password;
	private Authority authority;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails() {
	
	}

	public MyUserDetails(User user) {
		this.userName=user.getUserName();
		this.password=user.getPassword();
		this.authority=user.getAuthority();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
