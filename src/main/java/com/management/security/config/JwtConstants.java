package com.management.security.config;

public class JwtConstants {

	private JwtConstants() {
		throw new IllegalStateException("JWT Constants class.can't initiate");
	}

	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";

	public static final String FIRST_NAME = "first name";
	public static final String LAST_NAME = "last name";
	public static final String SIGNING_KEY = "SECRET_KEY";
}
