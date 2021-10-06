package com.management.http.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("rawtypes")
public class ResponseMaker {

	private <T> CustomHttpResponse<T> getObj(HttpStatus status, String message) {
		return new CustomHttpResponse<>(status.value(), status, message);
	}

	public <T> ResponseEntity<CustomHttpResponse<T>> successResponse(T data, String message, HttpStatus status) {
		CustomHttpResponse<T> response = getObj(status, message);
		response.setData(data);

		return ResponseEntity.ok(response);
	}

	public ResponseEntity<CustomHttpResponse> successResponse(String message, HttpStatus status) {
		return ResponseEntity.ok(getObj(status, message));
	}

	public ResponseEntity<CustomHttpResponse> errorResponse(String message, HttpStatus status) {
		CustomHttpResponse response = getObj(status, message);
		response.setError(true);

		return ResponseEntity.ok(response);
	}

}
