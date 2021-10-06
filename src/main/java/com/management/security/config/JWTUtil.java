package com.management.security.config;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.management.entity.Manager;
import com.management.exception.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JWTUtil {

	public String generateToken(Authentication authentication, Manager manager) {
		final String userFirstName = manager.getFirstName();
		final String lastFirstName = manager.getLastName();
		return Jwts.builder().claim(JwtConstants.FIRST_NAME, userFirstName).claim(JwtConstants.LAST_NAME, lastFirstName)
				.setSubject(authentication.getName()).signWith(SignatureAlgorithm.HS256, JwtConstants.SIGNING_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).compact();
	}

	public Claims getJwtClaims(String jwtToken) {
		try {

			final JwtParser jwtParser = Jwts.parser().setSigningKey(JwtConstants.SIGNING_KEY);

			final Jws<?> claimsJws = jwtParser.parseClaimsJws(jwtToken);

			return (Claims) claimsJws.getBody();
		} catch (ExpiredJwtException e) {
			throw new CustomException("The JwtToken is expired and not valid anymore", HttpStatus.FORBIDDEN, e);
		} catch (SignatureException e) {
			throw new CustomException("Invalid JwtToken Signature. Message: " + e.getMessage(), HttpStatus.FORBIDDEN,
					e);
		} catch (MalformedJwtException e) {
			throw new CustomException("Invalid JwtToken Format. Message: " + e.getMessage(), HttpStatus.FORBIDDEN, e);
		} catch (Exception e) {
			throw new CustomException("an error occured during parsing JwtToken. Message: " + e.getMessage(),
					HttpStatus.FORBIDDEN, e);
		}

	}
}
