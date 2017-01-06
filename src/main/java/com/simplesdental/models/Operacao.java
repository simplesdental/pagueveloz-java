package com.simplesdental.models;

import java.math.BigDecimal;

import com.google.api.client.util.DateTime;

public class Operacao {
	public Integer Id;
	public String NSU;
	public DateTime DataHora;
	public BigDecimal ValorTotal;
	public String Mensagem;
	public String CupomEstabelecimento;
	public String CupomCliente;
	public Boolean Sucesso;
	public String Status;
	public Boolean ConfirmacaoManual;
}