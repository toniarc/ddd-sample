package br.gov.pa.prodepa.pae.documento.domain.port;

import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosConteudoElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;

public interface ModeloConteudoRepository {

	Long cadastrar(ModeloConteudo modeloConteudo);
	
	Long contarTodosAtivosPor(Long especieId, TipoAbrangencia abrangencia, Long assuntoId);
	
	Long contarTodosAtivosPor(Long modeloConteudoId, Long especieId, TipoAbrangencia abrangencia, Long assuntoId);
	
	Long contarTodosModeloConteudoDoTipoBasicoAtivos();
	
	Long contarTodosModeloConteudoDoTipoBasicoAtivos(Long modeloConteudoId);

	ModeloConteudo buscarPorId(Long id);

	SearchResponse<ModelosConteudoElementoListaDto> listar(FiltrosPesquisaModeloConteudoDto filtros);
}
