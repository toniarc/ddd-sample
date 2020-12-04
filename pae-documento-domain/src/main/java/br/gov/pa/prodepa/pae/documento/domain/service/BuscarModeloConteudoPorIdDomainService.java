package br.gov.pa.prodepa.pae.documento.domain.service;

import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import br.gov.pa.prodepa.pae.documento.domain.exception.ModeloEstruturaValidationMessages;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloConteudoRepository;
import br.gov.pa.prodepa.pae.documento.domain.story.BuscarModeloConteudoPorIdStory;
import lombok.Builder;

@Builder
public class BuscarModeloConteudoPorIdDomainService implements BuscarModeloConteudoPorIdStory {

	private final ModeloConteudoRepository repository;

	@Override
	public ModeloConteudo buscarPorId(Long id) {
		
		if(id == null) {
			throw new DomainException(ModeloEstruturaValidationMessages.ID_OBRIGATORIO);
		}
		
		return repository.buscarPorId(id);
	}

}
