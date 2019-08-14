package com.test.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.UserData;
import com.test.util.StaticVariable;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserData userData = new ObjectMapper().readValue(request.getInputStream(), UserData.class);
			if(null == userData){
				throw new RuntimeException("null coy");
			}
			logger.warn("userdata " + userData.toString());
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userData.getUsername(),
					userData.getPassword(), new ArrayList<>()));
		} catch (Exception e) {
			String errMsg = StaticVariable.starckTraceToString(e);
			throw new RuntimeException(errMsg);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = Jwts.builder().setSubject(((User) authResult.getPrincipal()).getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + StaticVariable.JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, StaticVariable.SECRET).compact();
		response.addHeader(StaticVariable.HEADER_STRING, StaticVariable.TOKEN_PREFIX + token);
	}

}
