package br.gov.pa.prodepa.pae.documento.domain.port;

import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosEstruturaElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;

public interface ModeloEstruturaRepository {

	Long cadastrarModeloEstrutura(ModeloEstruturaAggregateRoot modeloEstrutura);
	
	ModeloEstruturaAggregateRoot buscarPorId(Long id);

	SearchResponse<ModelosEstruturaElementoListaDto> listar(FiltrosPesquisaModeloEstruturaDto filtros);
}
