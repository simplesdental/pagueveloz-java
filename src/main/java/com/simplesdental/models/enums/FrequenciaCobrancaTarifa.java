package com.simplesdental.models.enums;

public enum FrequenciaCobrancaTarifa {
	Diario(0), Semanal(1), Quinzenal(2), Mensal(3), Bimensal(4), Trimestral(5), Quadrimestral(6), Semestral(7), Anual(8);

	public Integer value;

	private FrequenciaCobrancaTarifa(Integer value) {
		this.value = value;
	}
}
