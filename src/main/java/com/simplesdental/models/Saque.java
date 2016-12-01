package com.simplesdental.models;

import java.math.BigDecimal;
import java.util.Date;

import com.simplesdental.models.enums.StatusDocumentoFinanceiro;

public class Saque {
	public Integer Id;
	public ContaBancaria ContaBancaria;
	public StatusDocumentoFinanceiro Status;
	public BigDecimal Valor;
	public Date DataSolicitacao;
	public Date DataProcessamento;
	public Boolean EmitirComprovante;
	public Boolean TemComprovante;
}
