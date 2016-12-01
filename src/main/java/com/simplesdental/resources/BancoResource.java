package com.simplesdental.resources;

import java.io.IOException;
import java.util.List;

import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Banco;

public class BancoResource {
	public final static String RESOURCE_V1 = "api/v1/Bancos";

	@SuppressWarnings("unchecked")
	public static List<Banco> list(RequestAuth auth) {
		try {
			Request request = Request.resource(RESOURCE_V1).auth(auth);
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), List.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}
}
