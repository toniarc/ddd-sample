package br.gov.pa.prodepa.pae.documento.domain.story;

import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;

public interface BuscarModeloEstruturaPorIdStory {

	ModeloEstruturaAggregateRoot buscarPorId(Long id);
}
