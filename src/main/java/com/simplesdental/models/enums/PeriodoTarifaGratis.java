package com.simplesdental.models.enums;

public enum PeriodoTarifaGratis {
	Nenhum(0), Semana(1), Mes(2), Ano(3), Dia(4);

	public Integer value;

	private PeriodoTarifaGratis(Integer value) {
		this.value = value;
	}
}
