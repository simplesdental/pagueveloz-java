package com.simplesdental.models.enums;

public enum FormaTarifacao {
	NA_ANTECIPACAO(1), NO_REPASSEL(2), NA_TRANSACAO(3);

	public Integer value;

	private FormaTarifacao(Integer value) {
		this.value = value;
	}
}
