package com.simplesdental.resources;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.Utils;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.RetornoId;
import com.simplesdental.models.Saldo;
import com.simplesdental.models.Saque;
import com.simplesdental.models.SolicitacaoSaque;

public class SaqueResource {
	private static final String INICIO_PARAM = "inicio";
	private static final String FIM_PARAM = "fim";
	public final static String RESOURCE_V2 = "api/v2/Saque";

	public static RetornoId create(RequestAuth auth, SolicitacaoSaque solicitacaoSaque) {
		try {
			Request request = Request.resource(RESOURCE_V2).method(HttpMethods.POST).auth(auth).body(Json.toString(solicitacaoSaque));
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), RetornoId.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static Boolean delete(RequestAuth auth, Integer idSaldo) {
		try {
			String resource = Request.path(RESOURCE_V2, idSaldo);
			Request request = Request.resource(resource).method(HttpMethods.DELETE).auth(auth);
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Boolean.valueOf(response.parseAsString());
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Saque> list(RequestAuth auth, Date inicio, Date fim) {
		try {
			Request request = Request.resource(RESOURCE_V2).auth(auth);
			request.addParam(INICIO_PARAM, Utils.dateToISOFormat(inicio));
			request.addParam(FIM_PARAM, Utils.dateToISOFormat(fim));
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

	public static Saldo retrieve(RequestAuth auth, Integer idSaldo) {
		try {
			String resource = Request.path(RESOURCE_V2, idSaldo);
			Request request = Request.resource(resource).auth(auth);
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), Saldo.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}
}
