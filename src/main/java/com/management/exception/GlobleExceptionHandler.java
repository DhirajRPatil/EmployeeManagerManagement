package com.management.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.management.http.response.CustomHttpResponse;
import com.management.http.response.ResponseMaker;

@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class GlobleExceptionHandler extends ResponseEntityExceptionHandler {

	Logger log = LogManager.getLogger();
	@Autowired
	private ResponseMaker responseMaker;

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CustomHttpResponse> customExceptionHandler(CustomException e) {

		return responseMaker.errorResponse(e.getMessage(), e.getStatus());
	}
}
