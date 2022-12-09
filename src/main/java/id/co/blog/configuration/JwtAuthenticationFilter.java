package id.co.blog.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

public class JwtAuthenticationFilter implements Filter{
	private static final String TOKEN_HEADER_PREFIX = "Bearer ";

	private JwtTokenVerifier jwtTokenVerifier;

	public JwtAuthenticationFilter(JwtTokenVerifier jwtTokenVerifier) {
		this.jwtTokenVerifier = jwtTokenVerifier;
	}

	private String getToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_HEADER_PREFIX))
			return bearerToken.substring(TOKEN_HEADER_PREFIX.length());

		return null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String token = getToken(req);

		if (token != null) {
			Authentication authentication = jwtTokenVerifier.isVerfiied(token);
			if (authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
	}
}
