package com.app.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration 
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "userclientid";
	   private String clientSecret = "usersecret";
	   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
	   		"MIIEogIBAAKCAQEAzyDxtl3X7d5HMPKkX5CJWWDTv8cYSJxdyetixzOUgSmLe+CK\r\n" + 
	   		"ND4ig3S5iNh2ER3xbZsuQs6Na5bkp49tMRgygNRym9muy4JTvS3NzCWM/CplXx9P\r\n" + 
	   		"3fECVMX8ZRC6wSohEYvQky4Lk1HbE3naxNAgAyvLEObBFZtTfQ9K8dvw/RKJFcwJ\r\n" + 
	   		"/mjLv3UlqtVxqeMNnURKwVBptosJLpCcOx94lqO3Ebb4Z+lpUBp5rAPYNucks6Zx\r\n" + 
	   		"4uL8uCgvEHx0+eWVSVYldrqB74mhlUc26H0VJOddMk/BmByaj4+Nlv+r0xymtzea\r\n" + 
	   		"SxrRsaHUgeyVZix9gsPx0bJG5ysr2m0+Rqg6hwIDAQABAoIBADRc575qywPfha/i\r\n" + 
	   		"J5vhNlfaGq4I7yKsJSm+0CH78DcXAaQ3h7jdXGxPPfhSgMajAz3qVS+eeDIS24tg\r\n" + 
	   		"vwICVAItsyujRIU+VV0mDzjk5Vd6gFYj3FUHcMdtpfkG2a+rruB4sCoFIfgEYZXy\r\n" + 
	   		"4FORKgajM5r6OyXzNpAPJasvJyVS7dQ1oH7Nv14lCr7EdWbj3n7+4n6HYMAOm9Z9\r\n" + 
	   		"3E7QJ/oRrE+jPCwCzksrb2I9017gUM4JQP2ixpgT83+8dvIL4yVF6p2rH1aTaTGr\r\n" + 
	   		"a4ugR5sY12aJqxWxlWSK1J8JBTF3Azo/rWoDiX5TekfbXpMS5U7Bhww5/Xs2dUal\r\n" + 
	   		"Z+/WicECgYEA6ZrqsmVVFp/KN7g8i8g8KiB+Ed8iUDFv2rxfgl4rJ+9qcfcIXEOy\r\n" + 
	   		"lge0kReWqxg1YJUC7b6ilhzIFbkBS/iPL5Ig7XpDFP1bzWMyYAtizwUsq/uZG/Hx\r\n" + 
	   		"WthmkzmuOPAjTqWnWKRfrZCp+V5Xj0THNR+A4/MEMQ+/8ff2B9yxE/UCgYEA4vw/\r\n" + 
	   		"ldRwEuKJC82GqaF6Th6/B5o1hwx4kl6C44Z7EhI++yoEVm+iFbGQo4RGxRb5N/gp\r\n" + 
	   		"5bA+2iPNr/euugZcnw24P7flnCO0chfN7d7GKwAzi8TZuk1X8tufQajho216UnyR\r\n" + 
	   		"ANpDLvpTEncuFF6vMlv8oSBNRVoEALJ2fia+gwsCgYAZYCGkdUtz5LGjsdip2ntm\r\n" + 
	   		"nBb1vAZPLb+XxCSf1bjcYq5uaMY4t4XbtESSCjzsovmRXz5tMusYYVH+whT4+qwb\r\n" + 
	   		"H8WB5mkJC6iAPAlJ05LNKCO9Htx346UzGBS0Y2ygdL1xGp9+CoT2JqPAGdZrEq6M\r\n" + 
	   		"/7oRiOForuxLzYKlQfzeTQKBgBdEgEgdlLd63U2Gbe1pjh3/7Nkcp6wGTT3DzmsA\r\n" + 
	   		"WBFMKYSZlARAQT80Cn2Yi6CccEOafsD6xhaaYPVO0GxlRo1PXLm7YiAVUOP5KLTf\r\n" + 
	   		"hzTVOnNzZFZCo2dTuAmFBEw6ZrWZ60tJFGX2ZxmXefPAmPjykPz7ULBxP8PKh3b7\r\n" + 
	   		"BcBtAoGAPATl2Jzgpl6HASPh/jpoIF7CacIbEjyFeuV6xTwkTNNOPG3WmI1Ol9/K\r\n" + 
	   		"B1wE9UnWTSLberyiicv1EGz+dWEcqgpVGXub9gG2cX1X65GxEhPBDa0U0RVTniQj\r\n" + 
	   		"eTNwdRL2viaIq15CkXL4Cg40Cb7/yDgq7ieA/10raihqgTPKZ60=\r\n" + 
	   		"-----END RSA PRIVATE KEY-----";
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n" + 
	   		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzyDxtl3X7d5HMPKkX5CJ\r\n" + 
	   		"WWDTv8cYSJxdyetixzOUgSmLe+CKND4ig3S5iNh2ER3xbZsuQs6Na5bkp49tMRgy\r\n" + 
	   		"gNRym9muy4JTvS3NzCWM/CplXx9P3fECVMX8ZRC6wSohEYvQky4Lk1HbE3naxNAg\r\n" + 
	   		"AyvLEObBFZtTfQ9K8dvw/RKJFcwJ/mjLv3UlqtVxqeMNnURKwVBptosJLpCcOx94\r\n" + 
	   		"lqO3Ebb4Z+lpUBp5rAPYNucks6Zx4uL8uCgvEHx0+eWVSVYldrqB74mhlUc26H0V\r\n" + 
	   		"JOddMk/BmByaj4+Nlv+r0xymtzeaSxrRsaHUgeyVZix9gsPx0bJG5ysr2m0+Rqg6\r\n" + 
	   		"hwIDAQAB\r\n" + 
	   		"-----END PUBLIC KEY-----";
	   
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }

}