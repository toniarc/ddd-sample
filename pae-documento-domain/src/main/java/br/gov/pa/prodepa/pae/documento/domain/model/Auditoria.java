package br.gov.pa.prodepa.pae.documento.domain.model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder()
public final class Auditoria {

	private final Long manutUsuarioId;
	
	private final Date manutData;
	
}
