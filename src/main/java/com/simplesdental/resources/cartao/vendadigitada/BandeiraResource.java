package com.simplesdental.resources.cartao.vendadigitada;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Bandeira;
import com.simplesdental.resources.cartao.CartaoResource;

public class BandeiraResource {
	public final static String RESOURCE = Request.path(CartaoResource.RESOURCE_V1, "Bandeiras");

	@SuppressWarnings("unchecked")
	public static List<Bandeira> list(RequestAuth auth, Bandeira bandeira, BigDecimal valorServico) {
		try {
			Request request = Request.resource(RESOURCE).auth(auth);
			request.addParam("Bandeira", bandeira.Id);
			request.addParam("ValorServico", valorServico);
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

	public static String retrieveUrlLogo(Integer idDandeira, BigDecimal valorServico) {
		return Request.path(RESOURCE, Request.path("Logo", idDandeira));
	}
}
