package com.simplesdental.models;

import com.simplesdental.models.enums.TipoNomeCliente;

public class AprovacaoNome {
	public Integer Id;
	public String Nome;
	public TipoNomeCliente TipoNome;
	public String TipoNomeDescricao;
	public Boolean Aprovado;
	public Boolean FoiRevisado;
}
