package br.gov.pa.prodepa.pae.documento.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstrutura;
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
	
	public void cadastrarModeloEstrutura(ModeloEstrutura modeloEstrutura) {
		ModeloEstruturaEntity modeloEstruturaEntity = ModeloEstruturaMapper.INSTANCE.mapToEntity(modeloEstrutura);
		modeloEstruturaRepository.save(modeloEstruturaEntity);
	}

	@Override
	public ModeloEstrutura buscarPorId(Long id) {
		Optional<ModeloEstruturaEntity> modeloEstrutura = modeloEstruturaRepository.findById(id);
		ModeloEstruturaEntity entity = modeloEstrutura.orElseThrow(()->new RuntimeException("Nenhum modelo de estrutura com o id " + id + " foi encontrado"));
		return ModeloEstruturaMapper.INSTANCE.mapToDomain(entity);
	}

}
