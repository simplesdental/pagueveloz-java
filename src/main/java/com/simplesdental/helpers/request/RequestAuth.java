package com.simplesdental.helpers.request;

public class RequestAuth {
	public String email = "";
	public String token = "";

	public RequestAuth(String email, String token) {
		this.email = email;
		this.token = token;
	}
}
