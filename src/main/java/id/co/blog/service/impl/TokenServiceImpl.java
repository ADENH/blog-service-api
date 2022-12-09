package id.co.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import id.co.blog.configuration.JwtTokenGenerator;
import id.co.blog.dto.TokenResponse;
import id.co.blog.dto.UsernamePasswordRequest;
import id.co.blog.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenGenerator tokenGenerator;
	
	@Override
	public TokenResponse createToken(UsernamePasswordRequest usernamePasswordRequest) {
		UsernamePasswordAuthenticationToken uPas = new UsernamePasswordAuthenticationToken(usernamePasswordRequest.getUsername(), usernamePasswordRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(uPas);
		CurrentPrincipal principal = (CurrentPrincipal) auth.getPrincipal();
		
		return tokenGenerator.generateToken(principal.getAccount());
	}

}
