package br.gov.pa.prodepa.pae.documento.domain.story;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;

public interface CadastrarModeloEstruturaStory {

	ModeloEstruturaAggregateRoot cadastrarModeloEstrutura(CadastrarModeloEstruturaDto command) throws ObjectStorageException;
	
}
