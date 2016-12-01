package com.simplesdental.models;

import java.math.BigDecimal;

import com.simplesdental.models.enums.FormaTarifacao;

public class Transacao {
	public BigDecimal ValorServico;
	public BigDecimal ValorTransacao;
	public Integer Parcelas;
	public Integer Bandeira;
	public String Descricao;
	public ProprietarioCartao ProprietarioCartao;
	public FormaTarifacao FormaTarifacao;
}
