package br.gov.pa.prodepa.pae.documento.domain.service;

import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import br.gov.pa.prodepa.pae.documento.domain.exception.ModeloEstruturaValidationMessages;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.story.BuscarModeloEstruturaPorIdStory;

public class BuscarModeloEstruturaPorIdDomainService implements BuscarModeloEstruturaPorIdStory {

	private ModeloEstruturaRepository repository;
	
	public BuscarModeloEstruturaPorIdDomainService(ModeloEstruturaRepository repository) {
		this.repository = repository;
	}

	@Override
	public ModeloEstruturaAggregateRoot buscarPorId(Long id) {
		
		if(id == null) {
			throw new DomainException(ModeloEstruturaValidationMessages.ID_OBRIGATORIO);
		}
		
		return repository.buscarPorId(id);
	}

}
