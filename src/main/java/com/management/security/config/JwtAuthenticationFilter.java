package com.management.security.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtTokenUtil;

	private static final Logger log = LogManager.getLogger();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader(JwtConstants.HEADER_STRING);
		log.info("Token :: " + header);
		String username = null;
		String jwtToken = null;
		Claims claims = null;
		if (header != null && header.startsWith(JwtConstants.TOKEN_PREFIX)) {
			jwtToken = header.replace(JwtConstants.TOKEN_PREFIX, "");

			claims = jwtTokenUtil.getJwtClaims(jwtToken);
			username = claims.getSubject();
		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					"", new ArrayList<>());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			logger.info("authenticated user " + username + ", setting security context");
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

}
