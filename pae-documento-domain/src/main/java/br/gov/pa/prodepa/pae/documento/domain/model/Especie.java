package br.gov.pa.prodepa.pae.documento.domain.model;

import lombok.Getter;

@Getter
public final class Especie {

	private final Long id;
	
	private final String nome;

	public Especie(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
}
