package com.management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.constant.UrlMapping;
import com.management.dto.AuthRequest;
import com.management.dto.AuthResponse;
import com.management.dto.ManagerDto;
import com.management.entity.Manager;
import com.management.exception.CustomException;
import com.management.http.response.CustomHttpResponse;
import com.management.http.response.ResponseMaker;
import com.management.security.config.JWTUtil;
import com.management.service.ManagerDetailService;

/*
 * @author Dhiraj R Patil
 * 
 *  AuthenticationController class includes API for saving Manager details,
 *  for authenticating manager details, and for generating token for the further requests.
 */
@RestController
@RequestMapping(UrlMapping.AUTH_URL)
@SuppressWarnings("rawtypes")
public class AuthenticationController {

	@Autowired
	private ManagerDetailService managerDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping(UrlMapping.SIGN_UP)
	public ResponseEntity<CustomHttpResponse> signUpManager(@Valid @RequestBody ManagerDto managerDto) {
		managerDetailService.save(managerDto);
		return new ResponseMaker().successResponse("Manager added successfully", HttpStatus.OK);
	}

	@PostMapping(UrlMapping.LOGIN)
	public ResponseEntity<CustomHttpResponse<AuthResponse>> logIn(@Valid @RequestBody AuthRequest authRequest) {
		try {

			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
			Manager user = managerDetailService.findByUserMail(authRequest.getUserName());
			final String token = jwtUtil.generateToken(authentication, user);
			return new ResponseMaker().successResponse(new AuthResponse(token), "Login successfully", HttpStatus.OK);
		} catch (Exception e) {
			throw new CustomException("Bad credentials provided.", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}
	}
}
