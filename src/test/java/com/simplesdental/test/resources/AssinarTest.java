package com.simplesdental.test.resources;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.api.client.http.HttpMethods;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestError;
import com.simplesdental.models.Assinatura;
import com.simplesdental.models.AssinaturaResultado;
import com.simplesdental.resources.AssinarResource;
import com.simplesdental.test.utils.HttpMock;

public class AssinarTest {
	public static String MOCK_BODY = "{\"Nome\":\"Joaodoidao\",\"Documento\":\"287.516.026-52\",\"Email\":\"luiz+joaoteste@simplesdental.com\",\"Endereco\":{\"Bairro\":\"Centro\",\"Cidade\":{\"Nome\":\"Criciuma\",\"Estado\":\"SantaCatarina\"},\"Logradouro\":\"Ruajosedepatta\",\"Numero\":\"499\",\"CEP\":\"88802240\"},\"Telefones\":[{\"Tipo\":1,\"Numero\":\"48999991999\"}],\"DataNascimento\":\"1990-11-17T00:00:00Z\",\"TipoPessoa\":1,\"Usuario\":{\"Nome\":\"Joaodoidao\",\"Email\":\"luiz+joaoteste@simplesdental.com\",\"Senha\":\"qwe123@A\",\"ConfirmacaoSenha\":\"qwe123@A\",\"Ativo\":true}}";
	public static String MOCK_RETORNO = "{\"Id\":2735,\"Token\":\"1cba9ae5-7f64-40a7-a17f-9647c97eb7b0\"}";

	@Test
	public void create() {
		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETORNO, (request) -> {
			try {
				assertEquals("Method should be ", HttpMethods.POST, request.method);
				assertEquals("Url should be ", "https://api.pagueveloz.com.br/api/v4/Assinar", request.url);
				assertEquals("Body should be equal a mock", Json.parse(MOCK_BODY), Json.parse(request.httpRequest.getContentAsString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		try {
			Assinatura assinatura = Json.fromJson(Json.parse(MOCK_BODY), Assinatura.class);
			AssinaturaResultado reponse = AssinarResource.create(assinatura);

			assertEquals("Reponse should be equal mock", Json.toJson(reponse), Json.parse(MOCK_RETORNO));
		} catch (RequestError e) {
			System.out.println(e);
		}
	}
}
