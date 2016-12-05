package com.simplesdental.resources.vendadigitada;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpResponse;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Bandeira;
import com.simplesdental.models.Operacao;
import com.simplesdental.models.Parcela;
import com.simplesdental.models.RetornoId;
import com.simplesdental.models.Transacao;

public class TransacaoResource {
	public final static String RESOURCE_CARTAO = "api/Cartao/VendaDigitada/v1";
	public final static String RESOURCE_TRANSACAO = Request.path(RESOURCE_CARTAO, "Transacao");
	public final static String RESOURCE_CONFIRMACAO = Request.path(RESOURCE_CARTAO, "Confirmar");
	public final static String RESOURCE_CANCELAR = Request.path(RESOURCE_CARTAO, "Cancelar");
	public final static String RESOURCE_PARCELAMENTO = Request.path(RESOURCE_CARTAO, "Parcelamento");

	public static RetornoId cancelar(RequestAuth auth, Transacao contaBancaria) {
		try {
			HttpResponse response = Request.resource(RESOURCE_TRANSACAO).method(HttpMethods.POST).auth(auth).body(Json.toString(contaBancaria)).send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), RetornoId.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static Boolean confirmar(RequestAuth auth, Integer idTransacao) {
		try {
			HttpResponse response = Request.resource(RESOURCE_CONFIRMACAO).method(HttpMethods.POST).auth(auth).body(String.valueOf(idTransacao)).send();

			if (response.isSuccessStatusCode()) {
				return Boolean.valueOf(response.parseAsString());
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static Operacao consultar(RequestAuth auth, Integer idTransacao) {
		try {
			String resource = Request.path(RESOURCE_TRANSACAO, idTransacao);
			HttpResponse response = Request.resource(resource).send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), Operacao.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	public static RetornoId create(RequestAuth auth, Transacao contaBancaria) {
		try {
			HttpResponse response = Request.resource(RESOURCE_TRANSACAO).method(HttpMethods.POST).auth(auth).body(Json.toString(contaBancaria)).send();

			if (response.isSuccessStatusCode()) {
				return Json.fromJson(response.parseAsString(), RetornoId.class);
			}

			throw new RequestError(response.parseAsString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RequestError(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Parcela> parcelamento(RequestAuth auth, Bandeira bandeira, BigDecimal valor) {
		try {
			Request request = Request.resource(RESOURCE_PARCELAMENTO).auth(auth);
			request.addParam("Bandeira", bandeira.Id);
			request.addParam("ValorServico", valor);
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
