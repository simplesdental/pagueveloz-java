package com.simplesdental.models.enums;

public enum TipoNomeBoleto {
	RazaoSocial(0), NomeFantasia(1), NomeAbreviado(2);

	public Integer value;

	private TipoNomeBoleto(Integer value) {
		this.value = value;
	}
}
