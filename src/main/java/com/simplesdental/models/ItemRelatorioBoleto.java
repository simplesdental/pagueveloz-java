package com.simplesdental.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ItemRelatorioBoleto {
	public String SeuNumero;
	public Date Vencimento;
	public String Documento;
	public String Sacado;
	public Date DataPagamento;
	public Date DataEnvioEmail;
	public BigDecimal Valor;
	public BigDecimal ValorPago;
	public BigDecimal ValorLiquido;
	public List<TarifaCobrada> TarifasCobradas;
	public Boolean TemPagamento;
	public String Url;
	public String Linha1;
	public String Linha2;
	public Boolean Cancelado;
	public Integer QuantidadeVisualizacoes;
	public Integer QuantidadeRejeicoes;
	public Integer QuantidadePagamentos;
	public String Email;
	public String NossoNumero;
	public String Parcela;
	public Date Emissao;
	public Integer Id;
}