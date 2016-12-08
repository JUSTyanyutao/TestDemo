package com.mxep.service.exception;

public class ApiRequestException extends RuntimeException {

	private static final long serialVersionUID = -1680070642633783426L;

	public ApiRequestException() {
		super();
	}

	public ApiRequestException(String message) {
		super(message);
	}

	public ApiRequestException(Throwable cause) {
		super(cause);
	}

	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
