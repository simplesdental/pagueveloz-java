package com.simplesdental.enums;

public enum TipoTitularContaBancaria {
	Propria(0), Socio(1), Terceiro(2);

	public Integer value;

	private TipoTitularContaBancaria(Integer value) {
		this.value = value;
	}
}
