package com.simplesdental.test.resources.vendadigitada;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.google.api.client.http.HttpMethods;
import com.simplesdental.helpers.request.Request;
import com.simplesdental.helpers.request.RequestAuth;
import com.simplesdental.models.Operacao;
import com.simplesdental.models.ProprietarioCartao;
import com.simplesdental.models.Transacao;
import com.simplesdental.resources.vendadigitada.TransacaoResource;
import com.simplesdental.test.utils.BasicAuth;
import com.simplesdental.test.utils.HttpMock;

public class TransacaoTest {
	private static final RequestAuth auth = new RequestAuth("luiz+joaoteste@simplesdental.com", "c78aba7d-483b-4242-b7a6-48b14238dfa3");
	private static final String MOCK_CONSULTAR = "{\"Id\":2642,\"NSU\":null,\"DataHora\":\"2016-12-06T17:03:45.0000000-02:00\",\"Status\":\"Nenhum\",\"ValorTotal\":100,\"Mensagem\":\"\",\"CupomEstabelecimento\":null,\"CupomCliente\":null,\"ConfirmacaoManual\":false}";
	private static final String MOCK_RETORNO = "2642";

	@Test
	public void consultar() {
		Integer idTransacao = 2642;

		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_CONSULTAR, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.GET, request.method);
				Assert.assertEquals("Url should be ", Request.path("https://api.pagueveloz.com.br/api/Cartao/VendaDigitada/v1/Consulta", idTransacao), request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		Operacao operacao = TransacaoResource.consultar(auth, idTransacao);
		Assert.assertTrue("Operacao should be ", operacao.Id.equals(2642));
		Assert.assertTrue("ValorTotal should be ", operacao.ValorTotal.equals(new BigDecimal(100)));
	}

	@Test
	public void create() {
		Request.HTTP_TRANSPORT = HttpMock.create(MOCK_RETORNO, (request) -> {
			try {
				Assert.assertTrue("Authorization header should be present ", BasicAuth.isAuthenticated(request.httpRequest, auth));
				Assert.assertEquals("Method should be ", HttpMethods.POST, request.method);
				Assert.assertEquals("Url should be ", "https://api.pagueveloz.com.br/api/Cartao/VendaDigitada/v1/Transacao", request.url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return request;
		});

		Transacao transacao = new Transacao();
		transacao.ValorServico = new BigDecimal(100);
		transacao.ValorTransacao = new BigDecimal(100);
		transacao.Parcelas = 1;
		transacao.Bandeira = 1;
		transacao.Descricao = "Nova transacao";

		transacao.ProprietarioCartao = new ProprietarioCartao();
		transacao.ProprietarioCartao.Nome = "Luiz Estacio";
		transacao.ProprietarioCartao.CPF = "282.170.415-12";
		transacao.ProprietarioCartao.RG = "36.855.301-2";
		transacao.ProprietarioCartao.TelefoneCelular = "48999218999";
		transacao.ProprietarioCartao.TelefoneFixo = "4834349818";
		transacao.ProprietarioCartao.EnviarEmail = false;
		transacao.ProprietarioCartao.EnviarSMS = false;

		Integer retorno = TransacaoResource.create(auth, transacao);
		Assert.assertTrue("Id should be 2642", retorno.equals(2642));
	}
}
