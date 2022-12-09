package id.co.blog.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;

import lombok.extern.slf4j.Slf4j;

/**
 * @author adenurhidayat.com Dec 9, 2022 7:21:36 PM
 */
@Configuration
@Slf4j
public class JwtConfiguration {
	
	@Value("${keystore.path}")
	private String keyStorePath;
	@Value("${keystore.pass}")
	private String keyStorePass;

//	@Value("classpath:key/blogapp.jks")
//	private Resource keyStoreResource;

	@Bean
	public KeyStore keyStore() {
		KeyStore keyStore;
		try {
			keyStore = KeyStore.getInstance("JKS");
			char[] password = keyStorePass.toCharArray();
			keyStore.load(new FileInputStream(keyStorePath), password);
//			keyStore.load(keyStoreResource.getInputStream(), password);
			return keyStore;
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	public JWK jwk() {
		try {
			return JWK.load(keyStore(), "blogapp", keyStorePass.toCharArray());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	public RSAKey rsaPrivateKey() {
		RSAKey rsaJWK = null;
		try {
			rsaJWK = RSAKey.load(keyStore(), "blogapp", keyStorePass.toCharArray());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		return rsaJWK;
	}

	@Bean
	public JWKSet jwkSet() {
		return new JWKSet(Arrays.asList(jwk()));
	}
}
