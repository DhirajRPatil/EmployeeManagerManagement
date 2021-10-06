package com.management.http.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class CustomHttpResponse<T> {

	private int code;
	private int customErrorCode;
	private HttpStatus status;
	private boolean error;
	private String message;

	private T data;

	public CustomHttpResponse() {
		super();
	}

	public CustomHttpResponse(int code, HttpStatus status, String message) {
		this.code = code;
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public boolean isError() {
		return error;
	}

	public T getData() {
		return data;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getCustomErrorCode() {
		return customErrorCode;
	}

	public void setCustomErrorCode(int customErrorCode) {
		this.customErrorCode = customErrorCode;
	}

	@Override
	public String toString() {
		return "CustomHttpResponse [message=" + message + ", code=" + code + ", status=" + status + ", error=" + error
				+ "]";
	}

}
