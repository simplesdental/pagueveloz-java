package com.simplesdental.test.resources;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.api.client.http.HttpMethods;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.models.ContaBancaria;
import com.simplesdental.models.RetornoId;
import com.simplesdental.models.enums.TipoContaBancaria;
import com.simplesdental.models.enums.TipoTitularContaBancaria;
import com.simplesdental.resources.ContaBancariaResource;
import com.simplesdental.test.utils.BasicAuth;
import com.simplesdental.test.utils.HttpMock;

public class ContaBancariaTest {
	private static final String MOCK_DELETE = "true";
	private static final RequestAuth auth = new RequestAuth("luiz+joaoteste@simplesdental.com", "c78aba7d-483b-4242-b7a6-48b14238dfa3");
	private static final String MOCK_BODY = "{\"DigitoConta\":\"1\",\"CodigoBanco\":4,\"CodigoAgencia\":\"1234\",\"NumeroConta\":\"12345\",\"TipoConta\":1,\"TipoTitular\":0,\"DigitoAgencia\":0,\"Descricao\":\"\"}";
	private static final String MOCK_UPDATE = "{\"Id\":3503,\"CodigoBanco\":4,\"Operacao\":\"\",\"CodigoAgencia\":\"1234\",\"DigitoAgencia\":\"0\",\"NumeroConta\":\"12345\",\"DigitoConta\":\"1\",\"Descricao\":\"ALTERADO\",\"TipoConta\":\"ContaCorrente\",\"TipoTitular\":\"Propria\",\"Titular\":null,\"DataValidadeAprovada\":null,\"DataValidadeSolicitada\":null,\"Status\":\"NaoVerificada\",\"DescricaoStatus\":\"Não Verificada\"}";
	private static final String MOCK_RETRIEVE = "{\"Id\":3503,\"CodigoBanco\":4,\"Operacao\":\"\",\"CodigoAgencia\":\"1234\",\"DigitoAgencia\":\"0\",\"NumeroConta\":\"12345\",\"DigitoConta\":\"1\",\"Descricao\":\"ALTERADO\",\"TipoConta\":\"ContaCorrente\",\"TipoTitular\":\"Propria\",\"Titular\":null,\"DataValidadeAprovada\":null,\"DataValidadeSolicitada\":null,\"Status\":\"NaoVerificada\",\"DescricaoStatus\":\"Não Verificada\"}";
	private static final String MOCK_RETORNO = "{\"Id\":3503}";
	private static final String MOCK_RETORNO_LIST = "[{\"Id\":3503}]";

	@Test
	public void create() {
		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETORNO, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.POST, request.method);
				Assert.assertEquals("Url should be ", "https://api.pagueveloz.com.br/api/v4/ContaBancaria", request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		ContaBancaria contaBancaria = new ContaBancaria();

		contaBancaria.CodigoAgencia = "1234";
		contaBancaria.DigitoConta = "1";
		contaBancaria.CodigoBanco = 4;
		contaBancaria.TipoConta = TipoContaBancaria.ContaCorrente;
		contaBancaria.TipoTitular = TipoTitularContaBancaria.Propria;
		contaBancaria.NumeroConta = "12345";
		contaBancaria.DigitoAgencia = "0";

		RetornoId retorno = ContaBancariaResource.create(auth, contaBancaria);
		Assert.assertTrue("Id should be 3503", retorno.Id.equals(3503));
	}

	@Test
	public void delete() {
		Integer idContaBancaria = 3503;

		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_DELETE, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.DELETE, request.method);
				Assert.assertEquals("Url should be ", Request.path("https://api.pagueveloz.com.br/api/v4/ContaBancaria", idContaBancaria), request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		Boolean wasDeleted = ContaBancariaResource.delete(auth, idContaBancaria);

		Assert.assertTrue("Delete should be success", wasDeleted);
	}

	@Test
	public void list() {
		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETORNO_LIST, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.GET, request.method);
				Assert.assertEquals("Url should be ", "https://api.pagueveloz.com.br/api/v4/ContaBancaria", request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		List<ContaBancaria> contas = ContaBancariaResource.list(auth);
		Assert.assertTrue(contas.get(0).Id.equals(3503));
	}

	@Test
	public void retrieve() {
		Integer idContaBancaria = 3503;

		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETRIEVE, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.GET, request.method);
				Assert.assertEquals("Url should be ", Request.path("https://api.pagueveloz.com.br/api/v4/ContaBancaria", idContaBancaria), request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		ContaBancaria contaBancaria = ContaBancariaResource.retrieve(auth, idContaBancaria);

		Assert.assertEquals("Description should be ", contaBancaria.Descricao, "ALTERADO");
	}

	@Test
	public void update() {
		Integer idContaBancaria = 3503;

		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_UPDATE, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.PUT, request.method);
				Assert.assertEquals("Url should be ", Request.path("https://api.pagueveloz.com.br/api/v4/ContaBancaria", idContaBancaria), request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		ContaBancaria contaBancaria = Json.fromJson(Json.parse(MOCK_BODY), ContaBancaria.class);
		contaBancaria.Descricao = "ALTERADO";
		ContaBancaria contaBancariaUpdated = ContaBancariaResource.update(auth, idContaBancaria, contaBancaria);

		Assert.assertEquals("Description should be ", contaBancariaUpdated.Descricao, contaBancaria.Descricao);
	}
}
