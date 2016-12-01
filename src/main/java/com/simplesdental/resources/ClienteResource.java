package com.simplesdental.resources;

import java.io.IOException;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Cliente;
import com.simplesdental.models.DocumentoParaUpload;

public class ClienteResource {
	private static final String DOCUMENTOS_PENDENTES = "DocumentosPendentes";
	public final static String RESOURCE_V3 = "api/v3/Cliente";

	public static Cliente list(RequestAuth auth) {
		try {
			Request request = Request.resource(RESOURCE_V3).auth(auth);
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), Cliente.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static Cliente retrieveDocumentosPendentes(RequestAuth auth) {
		try {
			String resource = Request.path(RESOURCE_V3, DOCUMENTOS_PENDENTES);
			Request request = Request.resource(resource).auth(auth);
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), Cliente.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static Cliente update(RequestAuth auth, Integer idCliente, Cliente cliente) {
		try {
			String resource = Request.path(RESOURCE_V3, idCliente);
			HttpResponse response = Request.resource(resource).method(HttpMethods.PUT).auth(auth).body(Json.toString(cliente)).send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), Cliente.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static Cliente uploadDocumento(RequestAuth auth, DocumentoParaUpload documentoParaUpload) {
		try {
			String resource = Request.path(RESOURCE_V3, DOCUMENTOS_PENDENTES);
			Request request = Request.resource(resource).method(HttpMethods.POST).auth(auth).body(Json.toString(documentoParaUpload));
			HttpResponse response = request.send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), Cliente.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}
}
