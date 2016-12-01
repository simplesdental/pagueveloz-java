package com.simplesdental.models.enums;

public enum StatusDocumentoFinanceiro {
	Pendente(0), Processado(1), Cancelado(2), EmProcessamento(3), AguardandoAtivacaoCliente(4);

	public Integer value;

	private StatusDocumentoFinanceiro(Integer value) {
		this.value = value;
	}
}
