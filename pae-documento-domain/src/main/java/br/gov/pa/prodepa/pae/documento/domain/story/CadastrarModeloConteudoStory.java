package br.gov.pa.prodepa.pae.documento.domain.story;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;

public interface CadastrarModeloConteudoStory {

	ModeloConteudo cadastrar(CadastrarModeloConteudoDto dto) throws ObjectStorageException;

}