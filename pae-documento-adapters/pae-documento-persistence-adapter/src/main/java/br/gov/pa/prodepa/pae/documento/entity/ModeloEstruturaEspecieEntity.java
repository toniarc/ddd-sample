package br.gov.pa.prodepa.pae.documento.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="modelo_estrutura_especie", schema="pae")
public class ModeloEstruturaEspecieEntity {

	@EmbeddedId
	private ModeloEstruturaEspecieID id;
}
