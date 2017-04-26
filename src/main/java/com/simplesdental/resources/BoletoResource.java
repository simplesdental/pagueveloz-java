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
import com.simplesdental.models.EmissaoBoleto;
import com.simplesdental.models.ItemRelatorioBoleto;
import com.simplesdental.models.RetornoEmissaoBoleto;
import com.simplesdental.models.enums.StatusBoletoRelatorio;

public class BoletoResource {
	public final static String RESOURCE_V4 = "api/v4/Boleto";
	private static final String DATA_INICIO_PARAM = "DataInicio";
	private static final String DATA_FIM_PARAM = "DataFim";
	private static final String STATUS_PARAM = "Status";
	private static final String DOCUMENTO_PARAM = "Documento";
	private static final String SEU_NUMERO_PARAM = "SeuNumero";
	private static final String INCLUIR_CANCELADOS_PARAM = "IncluirCancelados";
	private static final String APENAS_AGENDADOS_PARAM = "ApenasAgendados";
	private static final String NAO_VISUALIZADOS_PARAM = "NaoVisualizados";

	public static RetornoEmissaoBoleto create(RequestAuth auth, EmissaoBoleto emissaoBoleto) {
		try {
			Request request = Request.resource(RESOURCE_V4).method(HttpMethods.POST).auth(auth).body(Json.toString(emissaoBoleto));
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), RetornoEmissaoBoleto.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static Boolean delete(RequestAuth auth, Integer idBoleto) {
		try {
			String resource = Request.path(RESOURCE_V4, idBoleto);
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

	public static List<ItemRelatorioBoleto> list(RequestAuth auth, Date DataInicio, Date DataFim, StatusBoletoRelatorio Status, String Documento, String SeuNumero, Boolean IncluirCancelados,
			Boolean ApenasAgendados, Boolean NaoVisualizados) {
		try {
			Request request = Request.resource(RESOURCE_V4).auth(auth);
			if (DataInicio != null) {
				request.addParam(DATA_INICIO_PARAM, Utils.dateToISOFormat(DataInicio));
			}
			if (DataFim != null) {
				request.addParam(DATA_FIM_PARAM, Utils.dateToISOFormat(DataFim));
			}
			request.addParam(STATUS_PARAM, Status);
			request.addParam(DOCUMENTO_PARAM, Documento);
			request.addParam(SEU_NUMERO_PARAM, SeuNumero);
			request.addParam(INCLUIR_CANCELADOS_PARAM, IncluirCancelados);
			request.addParam(APENAS_AGENDADOS_PARAM, ApenasAgendados);
			request.addParam(NAO_VISUALIZADOS_PARAM, NaoVisualizados);
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJsonList(response.parseAsString(), ItemRelatorioBoleto.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static ItemRelatorioBoleto retrieve(RequestAuth auth, Integer idBoleto) {
		try {
			String resource = Request.path(RESOURCE_V4, idBoleto);
			Request request = Request.resource(resource).auth(auth);
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), ItemRelatorioBoleto.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

}
