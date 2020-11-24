package br.gov.pa.prodepa.pae.documento.domain.model;

import lombok.Getter;

@Getter
public final class Margem {

	private final String topo;
	
	private final String rodape;
	
	private final String direita;
	
	private final String esquerda;

	public Margem(String margemTopo, String margemDireita, String margemRodape, String margemEsquerda) {
		super();
		this.topo = margemTopo;
		this.rodape = margemRodape;
		this.direita = margemDireita;
		this.esquerda = margemEsquerda;
	}
	
}
