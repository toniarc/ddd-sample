package br.gov.pa.prodepa.pae.documento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.entity.ModeloConteudoEntity;

@Mapper
public interface ModeloConteudoMapper {
	
	ModeloConteudoMapper INSTANCE = Mappers.getMapper( ModeloConteudoMapper.class );

	@Mapping(source="auditoria.manutUsuarioId", target="usuarioManutId")
	@Mapping(source="auditoria.manutData", target="dataManut")
	@Mapping(source="modeloEstruturaId", target="modeloEstrutura.id")
	ModeloConteudoEntity map(ModeloConteudo modeloConteudo);
	
	@Mapping(source="usuarioManutId", target="auditoria.manutUsuarioId")
	@Mapping(source="dataManut", target="auditoria.manutData")
	@Mapping(source="modeloEstrutura.id", target="modeloEstruturaId")
	ModeloConteudo map(ModeloConteudoEntity entity);
}
