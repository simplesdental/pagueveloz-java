package com.simplesdental.models.enums;

public enum StatusBoletoRelatorio {
	Todos(0), Pagos(1), Vencidos(2), AVencer(3), Emitidos(4);

	public Integer value;

	private StatusBoletoRelatorio(Integer value) {
		this.value = value;
	}
}
