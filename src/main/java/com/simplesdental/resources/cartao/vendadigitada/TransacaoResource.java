package com.simplesdental.resources.cartao.vendadigitada;

import java.io.IOException;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.RetornoId;
import com.simplesdental.models.Transacao;
import com.simplesdental.resources.cartao.CartaoResource;

public class TransacaoResource {
	public final static String RESOURCE = Request.path(CartaoResource.RESOURCE_V1, "Transacao");

	public static RetornoId create(RequestAuth auth, Transacao contaBancaria) {
		try {
			HttpResponse response = Request.resource(RESOURCE).method(HttpMethods.POST).auth(auth).body(Json.toString(contaBancaria)).send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), RetornoId.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}
}
