package br.gov.pa.prodepa.pae.documento.domain.story;

import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosConteudoElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;

public interface ListarModelosConteudoStory {

	SearchResponse<ModelosConteudoElementoListaDto> listar(FiltrosPesquisaModeloConteudoDto filtros);

}
