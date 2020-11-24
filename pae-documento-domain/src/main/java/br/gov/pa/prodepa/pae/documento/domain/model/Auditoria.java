package br.gov.pa.prodepa.pae.documento.domain.model;

import java.util.Date;

import lombok.Getter;

@Getter
public final class Auditoria {

	private final Long manutUsuarioId;
	
	private final Date manutData;

	public Auditoria(Long manutUsuarioId, Date manutData) {
		super();
		this.manutUsuarioId = manutUsuarioId;
		this.manutData = manutData;
	}

}
