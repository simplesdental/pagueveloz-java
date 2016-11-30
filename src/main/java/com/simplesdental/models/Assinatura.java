package com.simplesdental.models;

import java.util.Date;
import java.util.List;

import com.simplesdental.models.enums.TipoPessoa;

public class Assinatura {
	public String Nome = null;
	public String Documento = null;
	public TipoPessoa TipoPessoa;
	public String Email = null;
	public String Urlnotifcacao = null;
	public CEP Endereco = null;
	public String Cupom = null;
	public List<Telefone> Telefones;
	public Integer InscricaoEstadual;
	public Integer InscricaoMunicipal;
	public Date DataNascimento;
	public UsuarioNaGravacao Usuario;
	public Integer Id;
}
