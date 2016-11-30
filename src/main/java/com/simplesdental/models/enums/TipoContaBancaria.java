package com.simplesdental.models.enums;

public enum TipoContaBancaria {
	NaoDefinido(0), ContaCorrente(1), ContaPoupanca(2);

	public Integer value;

	private TipoContaBancaria(Integer value) {
		this.value = value;
	}
}
