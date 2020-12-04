package br.gov.pa.prodepa.pae.documento.domain.service;

import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import br.gov.pa.prodepa.pae.documento.domain.exception.ModeloEstruturaValidationMessages;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.port.ObjectStoragePort;
import br.gov.pa.prodepa.pae.documento.domain.story.BuscarThumbnailModeloConteudoPorIdStory;
import lombok.Builder;

@Builder
public class BuscarThumbnailModeloConteudoPorIdDomainService implements BuscarThumbnailModeloConteudoPorIdStory {

	private ObjectStoragePort objectStorage;

	public BuscarThumbnailModeloConteudoPorIdDomainService(ObjectStoragePort objectStorage) {
		this.objectStorage = objectStorage;
	}
	
	@Override
	public byte[] buscarThumbnailPorId(String objectId) throws ObjectStorageException {
		
		if(objectId == null) {
			throw new DomainException(ModeloEstruturaValidationMessages.OBJECT_ID_OBRIGATORIO);
		}
		
		return objectStorage.getObject(objectId);
	}

}
