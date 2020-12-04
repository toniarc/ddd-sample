package br.gov.pa.prodepa.pae.documento.domain.story;

import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;

public interface BuscarThumbnailModeloEstruturaPorIdStory {

	byte[] buscarThumbnailPorId(String objectId) throws ObjectStorageException;
}
