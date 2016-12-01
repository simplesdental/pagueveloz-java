package com.simplesdental.models;

import java.math.BigDecimal;
import java.util.List;

public class Extrato {
	public BigDecimal SaldoAnterior;
	public List<Item> Itens;
	public List<Item> ItensFuturos;
	public String FiltroExtratoOFX;
}
