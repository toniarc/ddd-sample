package br.gov.pa.prodepa.pae.documento.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ModelosEstruturaElementoListaDto {

	private Long id;
	private String titulo;
	private Boolean ativo;
	private String thumbnail;

	public ModelosEstruturaElementoListaDto(Long id, String titulo, Boolean ativo, String thumbnail) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.ativo = ativo;
		this.thumbnail = thumbnail;
	}
	
}
