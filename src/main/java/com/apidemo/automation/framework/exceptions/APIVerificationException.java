package com.apidemo.automation.framework.exceptions;

public class APIVerificationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String errorMessage;

	public APIVerificationException(String message, Exception ex) {
		super(message, ex.getCause());
		this.errorMessage = message;
	}

	public APIVerificationException(String message) {
		super(message);
		this.errorMessage = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
