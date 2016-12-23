package com.simplesdental.models;

import java.math.BigDecimal;

import com.simplesdental.models.enums.FrequenciaCobrancaTarifa;
import com.simplesdental.models.enums.PeriodoTarifaGratis;
import com.simplesdental.models.enums.TipoTarifa;

public class Tarifa {
	public Integer Id;
	public TipoTarifa Tipo;
	public BigDecimal ValorFixo;
	public BigDecimal ValorPercentual;
	public Integer QuantidadeGratis;
	public PeriodoTarifaGratis Periodo;
	public FrequenciaCobrancaTarifa FrequenciaCobranca;
	public String Descricao;
}
