package br.gov.pa.prodepa.pae.documento.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModeloEstruturaAggregateRoot {

	private Long id;
	private String cabecalho;
	private String rodape;
	private String titulo;
	private String thumbnail;
	private Boolean ativo; 
	private FormatoPapel formato;
	private OrientacaoPapel orientacao;
	private Margem margens;
	private Auditoria auditoria;
	private List<Long> especiesId;
	private Orgao orgao;	

	public void validarCamposObrigatorios() {
	}
	
	public void withId(Long id){
		this.id = id;
	}
	
	public void inativar() {
		this.ativo = false;
	}
	
	public void ativar() {
		this.ativo = true;
	}
}
