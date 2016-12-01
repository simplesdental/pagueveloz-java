package com.simplesdental.resources;

import java.io.IOException;

import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.CEP;

public class CEPResource {
	public final static String RESOURCE_V1 = "api/v1/CEP";

	public static CEP retrieve(RequestAuth auth, String cep) {
		try {
			String resource = Request.path(RESOURCE_V1, cep);
			Request request = Request.resource(resource).auth(auth);
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), CEP.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}
}
