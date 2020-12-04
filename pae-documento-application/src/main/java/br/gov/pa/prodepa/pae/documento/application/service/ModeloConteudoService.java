package br.gov.pa.prodepa.pae.documento.application.service;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosConteudoElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;

public interface ModeloConteudoService {

	ModeloConteudo cadastrar(CadastrarModeloConteudoDto dto) throws ObjectStorageException;

	ModeloConteudo buscarPorId(Long id);

	byte[] buscarThumbnailPorId(String id) throws ObjectStorageException;

	SearchResponse<ModelosConteudoElementoListaDto> listar(FiltrosPesquisaModeloConteudoDto filtros);

}