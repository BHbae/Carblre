package com.carblre.handler.exception;


import org.springframework.http.HttpStatus;

// 로그인 여부 화긴
public class UnAuthorizedException extends RuntimeException {
	
	private HttpStatus status;
	
	// throw new UnAuthorizedException( , )
	public UnAuthorizedException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}