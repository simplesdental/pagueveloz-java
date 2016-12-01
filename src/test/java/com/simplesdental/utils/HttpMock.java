package com.simplesdental.utils;

import java.io.IOException;
import java.util.function.Function;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;

public class HttpMock {

	public static HttpTransport create(String mock_retorno, Function<RequestMock, RequestMock> fn) {
		return new MockHttpTransport() {
			@Override
			public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
				MockLowLevelHttpRequest mockLowLevelHttpRequest = new MockLowLevelHttpRequest() {
					@Override
					public LowLevelHttpResponse execute() throws IOException {
						MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
						response.setStatusCode(200);
						response.setContent(mock_retorno);

						fn.apply(new RequestMock(method, url, this));

						return response;
					}
				};

				mockLowLevelHttpRequest.setUrl(url);

				return mockLowLevelHttpRequest;
			}
		};
	}
}
