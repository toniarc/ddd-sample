package br.gov.pa.prodepa.pae.documento.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class ModeloEstruturaEspecieID implements Serializable{

	private static final long serialVersionUID = -7087928728937463601L;

	@Column(name="especie_id")
	private Long especieId;
	
	@Column(name="modelo_estrutura_id")
	private Long modeloEstruturaId;
	
}
