package br.gov.pa.prodepa.pae.documento.domain.model;

import java.util.List;

import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloEstruturaValidationMessages.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ModeloEstruturaAggregateRoot {

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
	private List<Especie> especies;
	private Orgao orgao;	

	public void validarCamposObrigatorios() {
		DomainException de = new DomainException();
		
		if(cabecalho == null || cabecalho.trim().length() == 0) de.addError(CABECALHO_REQUERIDO);
		if(titulo == null || titulo.trim().length() == 0) de.addError(TITULO_REQUERIDO);
		if(formato == null) de.addError(FORMATO_REQUERIDO);
		if(orientacao == null) de.addError(ORIENTACAO_REQUERIDA);
		if(especies == null || especies.size() == 0) de.addError(ESPECIE_REQUERIDA);
		if(margens == null) {
			de.addError(MARGENS_REQUERIDAS);
		}else {
			margens.validarCampos(de);
		}
		
		de.throwException();
	}
	
	public void adicionarThumbail(byte[] thumbnail){
		
		if(thumbnail == null) {
			throw new DomainException(THUMBNAIL_GERADO_INCORRETAMENTE);
		}
		
		this.thumbnail = thumbnail;
	}
	
	public void inativar() {
		this.ativo = false;
	}
	
	public void ativar() {
		this.ativo = true;
	}
}
