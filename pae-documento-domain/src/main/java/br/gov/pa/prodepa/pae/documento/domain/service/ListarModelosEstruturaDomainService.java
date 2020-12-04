package br.gov.pa.prodepa.pae.documento.domain.service;

import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosEstruturaElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.story.ListarModelosEstruturaStory;
import lombok.Builder;

public class ListarModelosEstruturaDomainService implements ListarModelosEstruturaStory{

	private final ModeloEstruturaRepository repository;

	@Builder
	public ListarModelosEstruturaDomainService(ModeloEstruturaRepository repository) {
		this.repository = repository;
	}

	@Override
	public SearchResponse<ModelosEstruturaElementoListaDto> listar(FiltrosPesquisaModeloEstruturaDto filtros) {
		return repository.listar(filtros);
	}

}
