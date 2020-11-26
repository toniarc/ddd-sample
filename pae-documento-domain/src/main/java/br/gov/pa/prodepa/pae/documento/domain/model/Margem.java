package br.gov.pa.prodepa.pae.documento.domain.model;

import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloEstruturaValidationMessages.*;
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

	public void validarCampos(DomainException de) {
		if(topo == null || topo.trim().length() == 0) de.addError(MARGEM_TOPO_REQUERIDA);
		if(rodape == null || rodape.trim().length() == 0) de.addError(MARGEM_RODAPE_REQUERIDA);
		if(direita == null || direita.trim().length() == 0) de.addError(MARGEM_DIREITA_REQUERIDA);
		if(esquerda == null || esquerda.trim().length() == 0) de.addError(MARGEM_ESQUERDA_REQUERIDA);
	}
	
}
