package br.gov.pa.prodepa.pae.documento.domain.model;

import lombok.Getter;

@Getter
public final class Orgao {

	private final Long id;
	
	private final String sigla;

	public Orgao(Long id, String sigla) {
		super();
		this.id = id;
		this.sigla = sigla;
	}

}
