package id.co.blog.service;

import id.co.blog.dto.TokenResponse;
import id.co.blog.dto.UsernamePasswordRequest;

public interface TokenService {
	TokenResponse createToken(UsernamePasswordRequest usernamePasswordRequest);
}
