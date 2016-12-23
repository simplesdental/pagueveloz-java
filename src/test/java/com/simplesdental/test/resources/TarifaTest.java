package com.simplesdental.test.resources;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.api.client.http.HttpMethods;
import com.simplesdental.helpers.Json;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.models.Tarifa;
import com.simplesdental.resources.TarifaResource;
import com.simplesdental.test.utils.BasicAuth;
import com.simplesdental.test.utils.HttpMock;

public class TarifaTest {
	private static final RequestAuth auth = new RequestAuth("luiz+joaoteste@simplesdental.com", "c78aba7d-483b-4242-b7a6-48b14238dfa3");
	private static final String MOCK_CUPOM = "SIMPLES.DENTAL.HOMOLOG.2016";
	private static final String MOCK_RETORNO = "[{\"Tipo\":\"BoletoEmissao\",\"ValorFixo\":0,\"ValorPercentual\":0,\"QuantidadeGratis\":0,\"Periodo\":\"Nenhum\",\"FrequenciaCobranca\":null,\"Descricao\":\"Emissão de Boleto\",\"Id\":4967},{\"Tipo\":\"BoletoLiquidacao\",\"ValorFixo\":4,\"ValorPercentual\":0,\"QuantidadeGratis\":0,\"Periodo\":\"Nenhum\",\"FrequenciaCobranca\":null,\"Descricao\":\"Recebimento de Boleto Pago\",\"Id\":4968},{\"Tipo\":\"Saque\",\"ValorFixo\":10,\"ValorPercentual\":0,\"QuantidadeGratis\":1,\"Periodo\":\"Semana\",\"FrequenciaCobranca\":null,\"Descricao\":\"Saque\",\"Id\":4969}]";

	@Test
	public void ativarCupom() {
		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETORNO, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.POST, request.method);
				Assert.assertEquals("Url should be ", "https://api.pagueveloz.com.br/api/v1/Tarifa/AtivarCupom", request.url);
				Assert.assertEquals("Body should be ", Json.toString(MOCK_CUPOM), request.httpRequest.getContentAsString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		List<Tarifa> tarifas = TarifaResource.ativarCupom(auth, MOCK_CUPOM);
		Assert.assertTrue("Id first tarifa should be", tarifas.get(0).Id.equals(4967));
	}
}
