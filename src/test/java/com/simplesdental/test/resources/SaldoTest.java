package com.simplesdental.test.resources;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.google.api.client.http.HttpMethods;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.models.Saldo;
import com.simplesdental.resources.SaldoResource;
import com.simplesdental.test.utils.BasicAuth;
import com.simplesdental.test.utils.HttpMock;

public class SaldoTest {
	private static final RequestAuth auth = new RequestAuth("luiz+joaoteste@simplesdental.com", "c78aba7d-483b-4242-b7a6-48b14238dfa3");
	private static final String MOCK_RETRIEVE = "{\"Data\":\"06/12/2016\",\"Disponivel\":0,\"Bloqueado\":0,\"BloqueadoAteAtivacao\":0,\"LiberadoParaUso\":0}";

	@Test
	public void retrieve() {
		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETRIEVE, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.GET, request.method);
				Assert.assertEquals("Url should be ", "https://api.pagueveloz.com.br/api/v1/Saldo", request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		Saldo saldo = SaldoResource.retrieve(auth);
		Assert.assertEquals("Description should be ", saldo.Bloqueado, BigDecimal.ZERO);
	}
}
