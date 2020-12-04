package br.gov.pa.prodepa.pae.documento.application.service;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosEstruturaElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;

public interface ModeloEstruturaService {

	ModeloEstruturaAggregateRoot cadastrarModeloEstrutura(CadastrarModeloEstruturaDto command) throws ObjectStorageException;
	
	ModeloEstruturaAggregateRoot buscarPorId(Long id);

	byte[] buscarThumbnailPorId(String objectID) throws ObjectStorageException;

	SearchResponse<ModelosEstruturaElementoListaDto> listar(FiltrosPesquisaModeloEstruturaDto filtros);
}
