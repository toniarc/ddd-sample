package br.gov.pa.prodepa.pae.documento.domain.test.unit;

import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosConteudoElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloConteudoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModeloConteudoRepositoryMock implements ModeloConteudoRepository{

	private Long modeloConteudoDoTipoBasicoAtivos;
	private Long totalModelosEspecieAbrangenciaAssuntiAtivos;

	public ModeloConteudoRepositoryMock() {
		this.modeloConteudoDoTipoBasicoAtivos = 0L;
		this.totalModelosEspecieAbrangenciaAssuntiAtivos = 0L;
	}
	
	@Override
	public Long contarTodosModeloConteudoDoTipoBasicoAtivos(Long modeloConteudoId) {
		return 0L;
	}
	
	@Override
	public Long contarTodosModeloConteudoDoTipoBasicoAtivos() {
		return this.modeloConteudoDoTipoBasicoAtivos;
	}
	
	@Override
	public Long contarTodosAtivosPor(Long modeloConteudoId, Long especieId, TipoAbrangencia abrangencia,
			Long assuntoId) {
		return 0L;
	}
	
	@Override
	public Long contarTodosAtivosPor(Long especieId, TipoAbrangencia abrangencia, Long assuntoId) {
		return this.totalModelosEspecieAbrangenciaAssuntiAtivos;
	}
	
	@Override
	public Long cadastrar(ModeloConteudo modeloConteudo) {
		return 1L;
	}

	@Override
	public ModeloConteudo buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResponse<ModelosConteudoElementoListaDto> listar(FiltrosPesquisaModeloConteudoDto filtros) {
		// TODO Auto-generated method stub
		return null;
	}
}
