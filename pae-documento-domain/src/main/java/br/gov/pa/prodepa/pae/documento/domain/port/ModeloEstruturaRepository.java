package br.gov.pa.prodepa.pae.documento.domain.port;

import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;

public interface ModeloEstruturaRepository {

	void cadastrarModeloEstrutura(ModeloEstruturaAggregateRoot modeloEstrutura);
	
	ModeloEstruturaAggregateRoot buscarPorId(Long id);
}
