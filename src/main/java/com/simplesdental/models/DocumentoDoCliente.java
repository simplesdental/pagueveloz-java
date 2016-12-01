package com.simplesdental.models;

import com.simplesdental.models.enums.StatusDocumentoDoCliente;
import com.simplesdental.models.enums.TipoDocumentoDoCliente;

public class DocumentoDoCliente {
	public Integer Id;
	public String NomeArquivo;
	public TipoDocumentoDoCliente Tipo;
	public String TipoDescricao;
	public StatusDocumentoDoCliente Status;
	public String StatusDescricao;
	public String Descricao;
	public String Observacao;
	public Boolean PermiteExclusao;
	public Boolean TemArquivo;
}
