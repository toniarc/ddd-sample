package br.gov.pa.prodepa.pae.documento.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class Margem {

	private String topo;
	
	private String rodape;
	
	private String direita;
	
	private String esquerda;

	public Margem(String margemTopo, String margemDireita, String margemRodape, String margemEsquerda) {
		super();
		this.topo = margemTopo;
		this.rodape = margemRodape;
		this.direita = margemDireita;
		this.esquerda = margemEsquerda;
	}
	
}
