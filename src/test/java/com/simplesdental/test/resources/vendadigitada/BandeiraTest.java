package com.simplesdental.test.resources.vendadigitada;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.api.client.http.HttpMethods;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.models.Bandeira;
import com.simplesdental.resources.vendadigitada.BandeiraResource;
import com.simplesdental.test.utils.BasicAuth;
import com.simplesdental.test.utils.HttpMock;

public class BandeiraTest {
	private static final RequestAuth auth = new RequestAuth("luiz+joaoteste@simplesdental.com", "c78aba7d-483b-4242-b7a6-48b14238dfa3");
	private static final String MOCK_RETORNO = "[{\"Id\":1}]";

	@Test
	public void list() {
		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETORNO, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.GET, request.method);
				Assert.assertEquals("Url should be ", "https://api.pagueveloz.com.br/api/Cartao/VendaDigitada/v1/Bandeiras", request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		List<Bandeira> bandeiras = BandeiraResource.list(auth);
		Assert.assertTrue("Size of list bandeiras should be greater than 0", bandeiras.size() > 0);
		Assert.assertTrue("Id should be 1", bandeiras.get(0).Id.equals(1));
	}

	@Test
	public void retrieveUrlLogo() {
		String urlLogo = BandeiraResource.retrieveUrlLogo(1);
		Assert.assertEquals("Logo url should be ", "https://api.pagueveloz.com.br/api/Cartao/VendaDigitada/v1/Bandeiras/Logo/1", urlLogo);
	}

}
