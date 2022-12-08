package id.co.blog.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import id.co.blog.model.Users;

public class CurrentPrincipal implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5762158177910016816L;
	private Users users;
	
	public CurrentPrincipal(Users users) {
		this.users = users;
	}

	public Users getAccount() {
		return users;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<>();
		if (users.getRole() != null)
			roles.add(new SimpleGrantedAuthority(users.getRole().getCode()));

		return roles;
	}

	@Override
	public String getPassword() {
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		return users.getUsername();
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
