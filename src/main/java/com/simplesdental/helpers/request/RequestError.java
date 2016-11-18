package com.simplesdental.helpers.request;

public class RequestError extends Error {
	private static final long serialVersionUID = 1L;

	private String message = null;

	public RequestError(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
