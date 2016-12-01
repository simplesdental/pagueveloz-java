package com.simplesdental.resources;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.Utils;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Extrato;

public class ExtratoResource {
	private static final String INICIO_PARAM = "inicio";
	private static final String FIM_PARAM = "fim";
	public final static String RESOURCE_V2 = "api/v2/Extrato";

	@SuppressWarnings("unchecked")
	public static List<Extrato> listBetween(RequestAuth auth, Date inicio, Date fim) {
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
}
