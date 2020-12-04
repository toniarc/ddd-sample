package br.gov.pa.prodepa.pae.documento.domain.story;

import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosEstruturaElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;

public interface ListarModelosEstruturaStory {

	SearchResponse<ModelosEstruturaElementoListaDto> listar(FiltrosPesquisaModeloEstruturaDto filtros);
}
