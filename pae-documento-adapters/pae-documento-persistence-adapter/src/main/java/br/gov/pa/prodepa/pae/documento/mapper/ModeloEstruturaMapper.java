package br.gov.pa.prodepa.pae.documento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstrutura;
import br.gov.pa.prodepa.pae.documento.entity.ModeloEstruturaEntity;

@Mapper
public interface ModeloEstruturaMapper {

	ModeloEstruturaMapper INSTANCE = Mappers.getMapper( ModeloEstruturaMapper.class );
	
	@Mapping(source="margens.direita", target="margemDireita")
	@Mapping(source="margens.esquerda", target="margemEsquerda")
	@Mapping(source="margens.topo", target="margemTopo")
	@Mapping(source="margens.rodape", target="margemRodape")
	@Mapping(source="auditoria.manutUsuarioId", target="manutUsuarioId")
	ModeloEstruturaEntity mapToEntity(ModeloEstrutura modeloEstrutura);

	@Mapping(source="margemDireita", target="margens.direita")
	@Mapping(source="margemEsquerda", target="margens.esquerda")
	@Mapping(source="margemTopo", target="margens.topo")
	@Mapping(source="margemRodape", target="margens.rodape")
	@Mapping(source="manutUsuarioId", target="auditoria.manutUsuarioId")
	ModeloEstrutura mapToDomain(ModeloEstruturaEntity entity);
	
}
