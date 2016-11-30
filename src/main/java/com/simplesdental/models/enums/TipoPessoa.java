package com.simplesdental.models.enums;

public enum TipoPessoa {
	NaoDefinido(0), Fisica(1), Juridica(2), Outros(20);

	public Integer value;

	private TipoPessoa(Integer value) {
		this.value = value;
	}
}
