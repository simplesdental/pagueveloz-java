package com.simplesdental.models.enums;

public enum TipoTarifa {
	NaoDefinido(0), BoletoEmissao(1), BoletoLiquidacao(2), Saque(3), NaoTarifar(4), TransferenciaEfetuada(5), TransferenciaRecebida(6), DepositoRecebido(7), PagamentoDeConta(8), BoletoBaixaManual(
			9), DispositivoPINPAD(10), DispositivoPOS(11), DispositivoModerninha(12), OperacaoCartao(13);

	public Integer value;

	private TipoTarifa(Integer value) {
		this.value = value;
	}
}
