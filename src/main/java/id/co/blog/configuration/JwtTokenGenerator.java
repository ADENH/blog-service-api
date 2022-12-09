package id.co.blog.configuration;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import id.co.blog.dto.TokenResponse;
import id.co.blog.model.Users;

/**
 * @author adenurhidayat.com
 * Dec 9, 2022
 * 7:24:04 PM
 */
@Configuration
public class JwtTokenGenerator {
	public static class ClaimSet {

		public static final String ROLE_CLAIM = "role";

	}

	@Autowired
	private JWK jwk;
	@Autowired
	private RSAKey rsaPrivateKey;

	public TokenResponse generateToken(Users user) {
		Calendar expirationDate = Calendar.getInstance();
		expirationDate.add(Calendar.DATE, 1);

		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject(user.getUsername()).issuer("nyoman")
				.issueTime(new Date()).expirationTime(expirationDate.getTime())
				.claim(ClaimSet.ROLE_CLAIM, user.getRole().getCode()).build();

		SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).jwk(jwk).build(), claimsSet);

		try {
			JWSSigner signer = new RSASSASigner(rsaPrivateKey);
			signedJWT.sign(signer);
		} catch (JOSEException e) {
			e.printStackTrace();
		}

		String token = signedJWT.serialize();
		TokenResponse response = new TokenResponse(token, expirationDate.getTimeInMillis());

		return response;
	}
}
