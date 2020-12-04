package br.gov.pa.prodepa.pae.documento.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ModelosConteudoElementoListaDto {

	private Long id;
	private String nome;
	private Boolean ativo;
	private Boolean basico;
	private String thumbnail;
	
	public ModelosConteudoElementoListaDto(Long id, String nome, Boolean ativo, Boolean basico, String thumbnail) {
		super();
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
		this.basico = basico;
		this.thumbnail = thumbnail;
	}
	
	
}
