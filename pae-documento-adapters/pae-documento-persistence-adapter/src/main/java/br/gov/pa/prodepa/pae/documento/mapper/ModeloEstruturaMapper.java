package br.gov.pa.prodepa.pae.documento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.entity.ModeloEstruturaEntity;

@Mapper()
public interface ModeloEstruturaMapper {

	ModeloEstruturaMapper INSTANCE = Mappers.getMapper( ModeloEstruturaMapper.class );
	
	@Mapping(source="margens.direita", target="margemDireita")
	@Mapping(source="margens.esquerda", target="margemEsquerda")
	@Mapping(source="margens.topo", target="margemTopo")
	@Mapping(source="margens.rodape", target="margemRodape")
	@Mapping(source="auditoria.manutUsuarioId", target="manutUsuarioId")
	@Mapping(source="auditoria.manutData", target="manutData")
	@Mapping(source="orgao.id", target="orgaoId")
	ModeloEstruturaEntity map(ModeloEstruturaAggregateRoot modeloEstrutura);

	@Mapping(source="margemDireita", target="margens.direita")
	@Mapping(source="margemEsquerda", target="margens.esquerda")
	@Mapping(source="margemTopo", target="margens.topo")
	@Mapping(source="margemRodape", target="margens.rodape")
	@Mapping(source="manutUsuarioId", target="auditoria.manutUsuarioId")
	@Mapping(source="manutData", target="auditoria.manutData")
	@Mapping(source="orgaoId", target="orgao.id")
	ModeloEstruturaAggregateRoot map(ModeloEstruturaEntity entity);
	
}
