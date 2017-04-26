package com.simplesdental.test.resources;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.api.client.http.HttpMethods;
import com.simplesdental.helpers.Utils;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.models.EmissaoBoleto;
import com.simplesdental.models.ItemRelatorioBoleto;
import com.simplesdental.models.RetornoEmissaoBoleto;
import com.simplesdental.resources.BoletoResource;
import com.simplesdental.test.utils.BasicAuth;
import com.simplesdental.test.utils.HttpMock;

public class BoletoTest {
	private static final RequestAuth auth = new RequestAuth("luiz+joaoteste@simplesdental.com", "c78aba7d-483b-4242-b7a6-48b14238dfa3");
	private static final String MOCK_DELETE = "true";
	private static final String MOCK_RETRIEVE = "{\"SeuNumero\": \"123456\",\"Vencimento\": \"2017-04-25T00:00:00Z\",\"Documento\": \"Documento\",\"Sacado\": \"abcd1234\",\"DataPagamento\": \"2017-04-25T00:00:00Z\",\"DataEnvioEmail\": \"2017-04-25T00:00:00Z\",\"Valor\": 10.0,\"ValorPago\": 10.0,\"ValorLiquido\": 8.0,\"TarifasCobradas\": [{\"Tipo\": \"BoletoLiquidacao\",\"Valor\": 2.0}],\"TemPagamento\": true,\"Url\": \"simplesdental.com/boleto\",\"Linha1\": \"Linha 1\",\"Linha2\": \"Linha 2\",\"Cancelado\": false,\"QuantidadeVisualizacoes\": 2,\"QuantidadeRejeicoes\": 0,\"QuantidadePagamentos\": 1,\"Email\": \"email@simplesdental.com\",\"NossoNumero\": \"12345678123\",\"Parcela\": \"1\",\"Emissao\": \"2017-04-25T00:00:00Z\",\"Id\": 3503}";
	private static final String MOCK_RETORNO_EMISSAO = "{\"Id\":3503, \"Url\": \"simplesdental.com/boleto\"}";
	private static final String MOCK_RETORNO_LIST = "[{\"Id\":3503}]";

	@Test
	public void create() {
		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETORNO_EMISSAO, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.POST, request.method);
				Assert.assertEquals("Url should be ", "https://api.pagueveloz.com.br/api/v4/Boleto", request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		EmissaoBoleto emissaoBoleto = new EmissaoBoleto();
		emissaoBoleto.Sacado = "abcd1234";
		emissaoBoleto.CPFCNPJSacado = "07046328960";
		emissaoBoleto.Vencimento = new Date();
		emissaoBoleto.Valor = BigDecimal.TEN;
		emissaoBoleto.SeuNumero = "123456";
		emissaoBoleto.Parcela = "1";
		emissaoBoleto.Linha1 = "Linha 1";
		emissaoBoleto.Linha2 = "Linha 2";
		emissaoBoleto.Email = "email@simplesdental.com";
		emissaoBoleto.DataEnvioEmail = new Date();
		emissaoBoleto.Pdf = Boolean.TRUE;

		RetornoEmissaoBoleto retorno = BoletoResource.create(auth, emissaoBoleto);
		Assert.assertTrue("Id should be 3503", retorno.Id.equals(3503));
		Assert.assertTrue("Url should be simplesdental.com/boleto", retorno.Url.equals("simplesdental.com/boleto"));
	}

	@Test
	public void delete() {
		Integer idBoleto = 3503;

		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_DELETE, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.DELETE, request.method);
				Assert.assertEquals("Url should be ", Request.path("https://api.pagueveloz.com.br/api/v4/Boleto", idBoleto), request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		Boolean wasDeleted = BoletoResource.delete(auth, idBoleto);

		Assert.assertTrue("Delete should be success", wasDeleted);
	}

	@Test
	public void list() {

		Date dataInicio = new Date();
		Date dataFim = new Date();

		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETORNO_LIST, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.GET, request.method);
				Assert.assertEquals("Url should be ", "https://api.pagueveloz.com.br/api/v4/Boleto?DataInicio=" + Utils.dateToISOFormat(dataInicio) + "&DataFim=" + Utils.dateToISOFormat(dataFim),
						request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		List<ItemRelatorioBoleto> boletos = BoletoResource.list(auth, new Date(), new Date(), null, null, null, null, null, null);
		Assert.assertTrue(boletos.get(0).Id.equals(3503));
	}

	@Test
	public void retrieve() {
		Integer idBoleto = 3503;

		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETRIEVE, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.GET, request.method);
				Assert.assertEquals("Url should be ", Request.path("https://api.pagueveloz.com.br/api/v4/Boleto", idBoleto), request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		ItemRelatorioBoleto boleto = BoletoResource.retrieve(auth, idBoleto);

		Assert.assertEquals("Id should be ", boleto.Id, new Integer(3503));
		Assert.assertEquals("Sacado should be ", boleto.Sacado, "abcd1234");
		Assert.assertEquals("SeuNumero should be ", boleto.SeuNumero, "123456");
	}
}
