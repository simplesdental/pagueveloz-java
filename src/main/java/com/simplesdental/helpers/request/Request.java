package com.simplesdental.helpers.request;

import java.io.IOException;

import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public class Request {
	public static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	public static final String API_OFICIAL = "https://api.pagueveloz.com.br";
	public static final String API_SANDBOX = "https://sandbox.pagueveloz.com.br";
	public static Boolean PRODUCTION = true;

	public static String createResourceUri(String resource) {
		return getApiUri() + "/" + resource;
	}

	public static String getApiUri() {
		return PRODUCTION ? API_OFICIAL : API_SANDBOX;
	}

	public static Request resource() throws IOException {
		return resource("");
	}

	public static Request resource(String resource) throws IOException {
		return new Request(resource);
	}

	private String method;

	private String uri;

	private HttpContent content;

	private HttpRequest request;

	public Request(String resource) throws IOException {
		this.request = HTTP_TRANSPORT.createRequestFactory().buildGetRequest(new GenericUrl(createResourceUri(resource)));
	}

	public Request auth(RequestAuth auth) throws IOException {
		BasicAuthentication basicAuthentication = new BasicAuthentication(auth.email, auth.token);
		basicAuthentication.initialize(this.request);
		return this;
	}

	public Request body(String content) {
		this.request.setContent(new RequestContent(content));
		return this;
	}

	public Request method(String method) {
		this.request.setRequestMethod(method);

		if (method.equals(HttpMethods.GET)) {
			this.request.setContent(null);
		}

		return this;
	}

	public HttpResponse send() throws IOException {
		return this.request.execute();
	}
}
