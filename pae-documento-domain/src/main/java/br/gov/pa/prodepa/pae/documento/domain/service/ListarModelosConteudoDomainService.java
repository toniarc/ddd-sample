package br.gov.pa.prodepa.pae.documento.domain.service;

import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosConteudoElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloConteudoRepository;
import br.gov.pa.prodepa.pae.documento.domain.story.ListarModelosConteudoStory;
import lombok.Builder;

public class ListarModelosConteudoDomainService implements ListarModelosConteudoStory {

	private final ModeloConteudoRepository repository;

	@Builder
	private ListarModelosConteudoDomainService(ModeloConteudoRepository repository) {
		this.repository = repository;
	}

	public SearchResponse<ModelosConteudoElementoListaDto> listar(FiltrosPesquisaModeloConteudoDto filtros) {
		return repository.listar(filtros);
	}

}
