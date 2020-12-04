package br.gov.pa.prodepa.pae.documento.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosConteudoElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloConteudoRepository;
import br.gov.pa.prodepa.pae.documento.entity.ModeloConteudoEntity;
import br.gov.pa.prodepa.pae.documento.mapper.ModeloConteudoMapper;
import br.gov.pa.prodepa.pae.documento.repository.ModeloConteudoJpaRepository;

@Component
public class ModeloConteudoPersistenceAdapter implements ModeloConteudoRepository {

	private final ModeloConteudoJpaRepository modeloConteudoRepository;
	
	@Autowired
	public ModeloConteudoPersistenceAdapter(ModeloConteudoJpaRepository modeloConteudoRepository) {
		this.modeloConteudoRepository = modeloConteudoRepository;
	}
	
	@Override
	public Long cadastrar(ModeloConteudo modeloConteudo) {
		ModeloConteudoEntity entity = ModeloConteudoMapper.INSTANCE.map(modeloConteudo);
		ModeloConteudoEntity savedEntity = modeloConteudoRepository.save(entity);
		return savedEntity.getId();
	}

	@Override
	public Long contarTodosAtivosPor(Long especieId, TipoAbrangencia abrangencia, Long assuntoId) {
		return modeloConteudoRepository.countByEspecieIdAndAbrangenciaAndAssuntoIdAndAtivo(especieId,abrangencia,assuntoId,true);
	}

	@Override
	public Long contarTodosAtivosPor(Long modeloConteudoId, Long especieId, TipoAbrangencia abrangencia, Long assuntoId) {
		return modeloConteudoRepository.countByIdNotAndEspecieIdAndAbrangenciaAndAssuntoIdAndAtivo(modeloConteudoId, especieId,abrangencia,assuntoId,true);
	}

	@Override
	public Long contarTodosModeloConteudoDoTipoBasicoAtivos() {
		return modeloConteudoRepository.countByBasicoAndAtivo(true,true);
	}

	@Override
	public Long contarTodosModeloConteudoDoTipoBasicoAtivos(Long modeloConteudoId) {
		return modeloConteudoRepository.countByIdNotAndBasicoAndAtivo(modeloConteudoId,true,true);
	}

	@Override
	public ModeloConteudo buscarPorId(Long id) {
		Optional<ModeloConteudoEntity> optional = modeloConteudoRepository.findById(id);
		ModeloConteudoEntity entity = optional.orElseThrow( () -> new RuntimeException("Modelo de conteúdo não encontrado"));
		return ModeloConteudoMapper.INSTANCE.map(entity);
	}

	@Override
	public SearchResponse<ModelosConteudoElementoListaDto> listar(FiltrosPesquisaModeloConteudoDto filtros) {
		Page<ModelosConteudoElementoListaDto> result = modeloConteudoRepository.findAllModelosConteudo(PageRequest.of(filtros.getPageNumber(), filtros.getPageSize()));
		return new SearchResponse<ModelosConteudoElementoListaDto>(result.getTotalPages(), new Long(result.getTotalElements()).intValue(), result.getNumber(), result.getContent());
	}

}
