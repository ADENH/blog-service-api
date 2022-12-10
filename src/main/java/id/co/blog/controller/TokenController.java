package id.co.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.blog.dto.TokenResponse;
import id.co.blog.dto.UsernamePasswordRequest;
import id.co.blog.service.TokenService;

/**
 * @author adenurhidayat.com
 * Dec 10, 2022
 * 7:09:04 AM
 */
@RestController
@RequestMapping("/auth")
public class TokenController {

	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/token")
	public TokenResponse login(@RequestBody UsernamePasswordRequest usernamePasswordRequest) {
		return tokenService.createToken(usernamePasswordRequest);
	}
}
