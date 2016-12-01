package com.simplesdental.helpers.request;

import java.io.IOException;

import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
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
		return path(getApiUri(), resource);
	}

	public static String getApiUri() {
		return PRODUCTION ? API_OFICIAL : API_SANDBOX;
	}

	public static String path(Object origin, Object dest) {
		return origin + "/" + dest;
	}

	public static Request resource() throws IOException {
		return resource("");
	}

	public static Request resource(String resource) throws IOException {
		return new Request(resource);
	}

	private HttpRequest request;
	private GenericUrl url;

	private Request(String resource) throws IOException {
		this.url = new GenericUrl(createResourceUri(resource));
		this.request = HTTP_TRANSPORT.createRequestFactory().buildGetRequest(this.url);
	}

	public Request addParam(String key, Object value) throws IOException {
		this.url.set(key, value);
		return this;
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
