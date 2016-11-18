package com.simplesdental.helpers.request;

import com.github.kevinsawicki.http.HttpRequest;

public class Request {
	public static final String API_OFICIAL = "https://sandbox.pagueveloz.com.br";
	public static final String API_SANDBOX = "";
	public static Boolean PRODUCTION = true;

	public static HttpRequest createRequest(RequestAuth auth, String method, String uri) {
		HttpRequest request = createRequest(method, uri);

		request.basic(auth.email, auth.token);

		return request;
	}

	public static HttpRequest createRequest(String method, String uri) {
		HttpRequest request = null;

		switch (method) {
		case "POST":
			request = HttpRequest.post(uri);
			break;
		case "DELETE":
			request = HttpRequest.delete(uri);
			break;
		case "PUT":
			request = HttpRequest.put(uri);
			break;
		case "GET":
		default:
			request = HttpRequest.get(uri);
			break;
		}

		return request.contentType("application/json");
	}

	public static String createResourceUri(String resource) {
		return getApiUri() + "/" + resource;
	}

	public static String getApiUri() {
		return PRODUCTION ? API_OFICIAL : API_SANDBOX;
	}
}
