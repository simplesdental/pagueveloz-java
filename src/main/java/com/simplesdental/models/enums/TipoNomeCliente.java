package com.simplesdental.models.enums;

public enum TipoNomeCliente {
	RazaoSocial(0), NomeFantasia(1), NomeAbreviado(2);

	public Integer value;

	private TipoNomeCliente(Integer value) {
		this.value = value;
	}
}
