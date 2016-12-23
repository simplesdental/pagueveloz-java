package com.simplesdental.resources;

import java.io.IOException;
import java.util.List;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.ContaBancaria;
import com.simplesdental.models.RetornoId;

public class ContaBancariaResource {
	public final static String RESOURCE_V4 = "api/v4/ContaBancaria";

	public static RetornoId create(RequestAuth auth, ContaBancaria contaBancaria) {
		try {
			HttpResponse response = Request.resource(RESOURCE_V4).method(HttpMethods.POST).auth(auth).body(Json.toString(contaBancaria)).send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), RetornoId.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static Boolean delete(RequestAuth auth, Integer idContaBancaria) {
		try {
			String resource = Request.path(RESOURCE_V4, idContaBancaria);
			HttpResponse response = Request.resource(resource).method(HttpMethods.DELETE).auth(auth).send();

			if (response.isSuccessStatusCode()) {
				return Boolean.valueOf(response.parseAsString());
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static List<ContaBancaria> list(RequestAuth auth) {
		try {
			HttpResponse response = Request.resource(RESOURCE_V4).auth(auth).send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJsonList(response.parseAsString(), ContaBancaria.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static ContaBancaria retrieve(RequestAuth auth, Integer idContaBancaria) {
		try {
			String resource = Request.path(RESOURCE_V4, idContaBancaria);
			HttpResponse response = Request.resource(resource).auth(auth).send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), ContaBancaria.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static ContaBancaria update(RequestAuth auth, Integer idContaBancaria, ContaBancaria contaBancaria) {
		try {
			String resource = Request.path(RESOURCE_V4, idContaBancaria);
			HttpResponse response = Request.resource(resource).method(HttpMethods.PUT).auth(auth).body(Json.toString(contaBancaria)).send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), ContaBancaria.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}
}
