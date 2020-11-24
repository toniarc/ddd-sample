package br.gov.pa.prodepa.pae.documento.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstrutura;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.entity.ModeloEstruturaEntity;
import br.gov.pa.prodepa.pae.documento.mapper.ModeloEstruturaMapper;
import br.gov.pa.prodepa.pae.documento.repository.ModeloEstruturaJpaRepository;

@Component
public class ModeloEstruturaPersistenceAdapter implements ModeloEstruturaRepository {

	@Autowired
	private ModeloEstruturaJpaRepository modeloEstruturaRepository;
	
	public void cadastrarModeloEstrutura(ModeloEstrutura modeloEstrutura) {
		ModeloEstruturaEntity modeloEstruturaEntity = ModeloEstruturaMapper.INSTANCE.mapToEntity(modeloEstrutura);
		modeloEstruturaRepository.save(modeloEstruturaEntity);
	}

}
