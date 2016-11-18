package com.simplesdental.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.kevinsawicki.http.HttpRequest;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Assinatura;
import com.simplesdental.models.AssinaturaResultado;

public class Assinar {
	public final static String RESOURCE_V4 = "api/v4/Assinar";

	public static AssinaturaResultado create(Assinatura assinatura) throws RequestError {
		String uri = Request.createResourceUri(RESOURCE_V4);

		HttpRequest request = Request.createRequest(HttpRequest.METHOD_POST, uri).send(Json.toString(assinatura));
		if (request.code() == 200) {
			JsonNode json = Json.parse(request.body());
			return Json.fromJson(json, AssinaturaResultado.class);
		}

		throw new RequestError(request.body());
	}
}
