package com.simplesdental.resources;

import java.io.IOException;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Assinatura;
import com.simplesdental.models.AssinaturaResultado;

public class AssinarResource {
	public final static String RESOURCE = "api/v4/Assinar";

	public static AssinaturaResultado create(Assinatura assinatura) throws RequestError {
		try {
			HttpResponse response = Request.resource(RESOURCE).method(HttpMethods.POST).body(Json.toString(assinatura)).send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), AssinaturaResultado.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			throw new RequestError(e.getMessage());
		}
	}
}
