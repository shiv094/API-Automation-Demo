package com.apidemo.automation.framework.exceptions;

public class JsonNodeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String errorMessage;

	public JsonNodeNotFoundException(String message, Exception ex) {
		super(message, ex.getCause());
		this.errorMessage = message;
	}

	public JsonNodeNotFoundException(String message) {
		super(message);
		this.errorMessage = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
