package br.gov.pa.prodepa.pae.documento.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosEstruturaElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.entity.ModeloEstruturaEntity;
import br.gov.pa.prodepa.pae.documento.mapper.ModeloEstruturaMapper;
import br.gov.pa.prodepa.pae.documento.repository.ModeloEstruturaJpaRepository;

@Component
public final class ModeloEstruturaPersistenceAdapter implements ModeloEstruturaRepository {
	
	private final ModeloEstruturaJpaRepository modeloEstruturaRepository;

	@Autowired
	public ModeloEstruturaPersistenceAdapter(ModeloEstruturaJpaRepository modeloEstruturaRepository) {
		this.modeloEstruturaRepository = modeloEstruturaRepository;
	}
	
	public Long cadastrarModeloEstrutura(ModeloEstruturaAggregateRoot modeloEstrutura) {
		ModeloEstruturaEntity modeloEstruturaEntity = ModeloEstruturaMapper.INSTANCE.map(modeloEstrutura);
		ModeloEstruturaEntity estruturaEntity = modeloEstruturaRepository.save(modeloEstruturaEntity);
		return estruturaEntity.getId();
	}

	@Override
	public ModeloEstruturaAggregateRoot buscarPorId(Long id) {
		Optional<ModeloEstruturaEntity> modeloEstrutura = modeloEstruturaRepository.findById(id);
		ModeloEstruturaEntity entity = modeloEstrutura.orElseThrow(()->new RuntimeException("Nenhum modelo de estrutura com o id " + id + " foi encontrado"));
		return ModeloEstruturaMapper.INSTANCE.map(entity);
	}

	@Override
	public SearchResponse<ModelosEstruturaElementoListaDto> listar(FiltrosPesquisaModeloEstruturaDto filtros) {
		Page<ModelosEstruturaElementoListaDto> result = modeloEstruturaRepository.findAllModelosEstrutura(PageRequest.of(filtros.getPageNumber(), filtros.getPageSize()));
		return new SearchResponse<ModelosEstruturaElementoListaDto>(result.getTotalPages(), new Long(result.getTotalElements()).intValue(), result.getNumber(), result.getContent());
	}

}
