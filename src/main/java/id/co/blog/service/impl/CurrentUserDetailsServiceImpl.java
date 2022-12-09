package id.co.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.co.blog.model.Users;
import id.co.blog.service.UsersService;

/**
 * @author adenurhidayat.com
 * Dec 9, 2022
 * 8:41:06 PM
 */
@Service
public class CurrentUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersService userService;
	
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException
	{
		Users user = userService.getUsersByUsername(username);
		
		if (user == null)
			throw new UsernameNotFoundException("username "+username+" not found");

		return new CurrentPrincipal(user);
	}
}
