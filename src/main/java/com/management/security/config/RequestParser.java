package com.management.security.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class RequestParser {

	@Autowired
	private HttpServletRequest httpRequest;

	@Autowired
	private JWTUtil jwtTokenUtil;

	protected Claims getClaims() {
		String header = httpRequest.getHeader(JwtConstants.HEADER_STRING);
		String authToken = header.replace(JwtConstants.TOKEN_PREFIX, "");
		return jwtTokenUtil.getJwtClaims(authToken);
	}

	public String getmanagerMailId() {
		return (String) getClaims().get("sub");
	}

}
