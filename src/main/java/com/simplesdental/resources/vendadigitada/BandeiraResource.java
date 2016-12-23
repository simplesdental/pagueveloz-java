package com.simplesdental.resources.vendadigitada;

import java.io.IOException;
import java.util.List;

import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Bandeira;

public class BandeiraResource {
	public final static String RESOURCE = "api/Cartao/VendaDigitada/v1/Bandeiras";

	public static List<Bandeira> list(RequestAuth auth) {
		try {
			Request request = Request.resource(RESOURCE).auth(auth);
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJsonList(response.parseAsString(), Bandeira.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static String retrieveUrlLogo(Integer idDandeira) {
		return Request.path(RESOURCE, Request.path("Logo", idDandeira));
	}
}
