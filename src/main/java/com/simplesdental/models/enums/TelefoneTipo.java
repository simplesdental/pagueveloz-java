package com.simplesdental.models.enums;

public enum TelefoneTipo {
	Residencial(1), Comercial(2), Celular(4);

	public Integer value;

	private TelefoneTipo(Integer value) {
		this.value = value;
	}
}
