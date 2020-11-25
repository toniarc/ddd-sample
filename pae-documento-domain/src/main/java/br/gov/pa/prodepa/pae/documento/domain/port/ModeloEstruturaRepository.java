package br.gov.pa.prodepa.pae.documento.domain.port;

import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstrutura;

public interface ModeloEstruturaRepository {

	void cadastrarModeloEstrutura(ModeloEstrutura modeloEstrutura);
	
	ModeloEstrutura buscarPorId(Long id);
}
