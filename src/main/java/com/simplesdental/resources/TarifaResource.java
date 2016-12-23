package com.simplesdental.resources;

import java.io.IOException;
import java.util.List;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Tarifa;

public class TarifaResource {
	public final static String RESOURCE_V1 = "api/v1/Tarifa";
	public final static String RESOURCE_ATIVAR_CUPOM = Request.path(RESOURCE_V1, "AtivarCupom");

	public static List<Tarifa> ativarCupom(RequestAuth auth, String cupom) {
		try {
			Request request = Request.resource(RESOURCE_ATIVAR_CUPOM).method(HttpMethods.POST).auth(auth).body(Json.toString(cupom));
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJsonList(response.parseAsString(), Tarifa.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}
}
