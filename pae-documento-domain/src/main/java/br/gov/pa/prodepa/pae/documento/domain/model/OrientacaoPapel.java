package br.gov.pa.prodepa.pae.documento.domain.model;

public enum OrientacaoPapel {

	LANDSCAPE("Landscape"), PORTRAIT("Portrait");
	
	private OrientacaoPapel(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}
}
