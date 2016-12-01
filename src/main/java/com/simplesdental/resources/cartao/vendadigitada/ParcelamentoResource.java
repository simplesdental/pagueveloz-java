package com.simplesdental.resources.cartao.vendadigitada;

import java.io.IOException;
import java.util.List;

import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Parcela;
import com.simplesdental.resources.cartao.CartaoResource;

public class ParcelamentoResource {
	public final static String RESOURCE = Request.path(CartaoResource.RESOURCE_V1, "Parcelamento");

	@SuppressWarnings("unchecked")
	public static List<Parcela> list(RequestAuth auth) {
		try {
			Request request = Request.resource(RESOURCE).auth(auth);
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
