package br.gov.pa.prodepa.pae.documento.domain.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModeloEstrutura {

	private Long id;
	
	private String cabecalho;
	
	private String rodape;
	
	private String titulo;
	
	private byte[] thumbnail;
	
	private Boolean ativo; 
	
	private FormatoPapel formato;
	
	private OrientacaoPapel orientacao;
	
	private Margem margens;
	
	private Auditoria auditoria;
	
	private Set<Especie> especies;
	
	private Orgao orgao;	
	
	public void inativar() {
		this.ativo = false;
	}
	
	public void ativar() {
		this.ativo = true;
	}
}
