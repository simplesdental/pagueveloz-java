package com.simplesdental.models;

import java.util.Date;
import java.util.List;

import com.simplesdental.models.enums.TipoNomeCliente;
import com.simplesdental.models.enums.TipoPessoa;

public class Cliente {
	public Integer Id;
	public String Nome;
	public String Email;
	public TipoPessoa TipoPessoa;
	public String Documento;
	public Date DataNascimento;
	public Boolean EnviarEamilBoletosPagos;
	public CEP Endereco;
	public List<Telefone> Telefones;
	public Boolean OptanteSimples;
	public Integer InscricaoEstadual;
	public TipoNomeCliente TipoNomeBoleto;
	public TipoNomeCliente TipoNomeCartao;
	public AprovacaoNome AprovacaoTipoNome;
}
