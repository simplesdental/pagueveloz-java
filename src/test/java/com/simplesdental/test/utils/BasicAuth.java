package com.simplesdental.test.utils;

import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.util.Base64;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import com.simplesdental.helpers.request.RequestAuth;

public class BasicAuth {
	public static String create(String username, String password) {
		String userPass = Preconditions.checkNotNull(username) + ":" + Preconditions.checkNotNull(password);
		String encoded = Base64.encodeBase64String(StringUtils.getBytesUtf8(userPass));

		return encoded;
	}

	public static Boolean isAuthenticated(MockLowLevelHttpRequest httpRequest, RequestAuth auth) {
		String basicHeader = httpRequest.getHeaders().get("authorization").get(0).toString();
		return basicHeader.equals("Basic " + BasicAuth.create(auth.email, auth.token));
	}
}
