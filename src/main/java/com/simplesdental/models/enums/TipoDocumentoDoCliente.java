package com.simplesdental.models.enums;

public enum TipoDocumentoDoCliente {
	Outros(0), Fisica_Documento(1), Fisica_ComprovanteEndereco(2), Juridica_ContratoSocial(3), Juridica_ComprovanteEndereco(4), Juridica_DocumentoSocio(5), Juridica_DocumentoAdministrador(
			6), Termo_ContaBancariaTerceiros(20), Termo_ChargebackCartao(21);

	public Integer value;

	private TipoDocumentoDoCliente(Integer value) {
		this.value = value;
	}
}
