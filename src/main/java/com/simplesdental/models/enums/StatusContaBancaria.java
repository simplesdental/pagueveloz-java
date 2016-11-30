package com.simplesdental.models.enums;

public enum StatusContaBancaria {
	Verificada(0), NaoVerificada(1), Rejeitada(2), Vencida(3), Inativa(4);

	public Integer value;

	private StatusContaBancaria(Integer value) {
		this.value = value;
	}
}
