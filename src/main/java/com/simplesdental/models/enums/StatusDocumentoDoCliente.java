package com.simplesdental.models.enums;

public enum StatusDocumentoDoCliente {
	NaoEnviado(0), AguardandoVerificacao(1), EmAnalise(2), Aceito(3), Rejeitado(4);

	public Integer value;

	private StatusDocumentoDoCliente(Integer value) {
		this.value = value;
	}
}
