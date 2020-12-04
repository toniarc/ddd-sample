package br.gov.pa.prodepa.pae.documento.domain.model;

import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages.ABRANGENCIA_OBRIGATORIO;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages.ABRANGENCIA_ORGAO_NAO_PODE_SER_MODELO_BASICO;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages.BASICO_OBRIGATORIO;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages.CONTEUDO_OBRIGATORIO;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages.ESPECIE_OBRIGATORIA;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages.MODELO_BASICO_NAO_PODE_TER_ASSUNTO;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages.MODELO_BASICO_NAO_PODE_TER_ESPECIE;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages.MODELO_ESTRUTURA_OBRIGATORIO;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages.NOME_OBRIGATORIO;

import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ModeloConteudo {

	private Long id;
	private String nome;
	private String thumbnail;
	private boolean ativo;
	private String conteudo;
	private Boolean basico;
	private Long orgaoId;
	private Long especieId;
	private Long assuntoId;
	private Long modeloEstruturaId;
	private Auditoria auditoria;
	private TipoAbrangencia abrangencia;

	public ModeloConteudo() {
	}
	
	@Builder
	public ModeloConteudo(Long id, String nome, String thumbnail, boolean ativo, String conteudo, Boolean basico,
			Long orgaoId, Long especieId, Long assuntoId, Long modeloEstruturaId, Auditoria auditoria,
			TipoAbrangencia abrangencia) {
		super();
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
		this.conteudo = conteudo;
		this.basico = basico;
		this.especieId = especieId;
		this.assuntoId = assuntoId;
		this.modeloEstruturaId = modeloEstruturaId;
		this.auditoria = auditoria;
		this.abrangencia = abrangencia;
		this.thumbnail = thumbnail;
		
		if(this.abrangencia != null && this.abrangencia.equals(TipoAbrangencia.ORGAO)) {
			this.orgaoId = orgaoId;
		} else {
			this.orgaoId = null;
		}
		
		validarCampos();
	}
	
	private void validarCampos() {
		
		DomainException de = new DomainException();

		//Critério de aceitação 01 e 02 sao referentes ao frontend
		
		//campos obrigatorios
		if(basico == null)  {
			de.addError(BASICO_OBRIGATORIO);
		} else {
			//Critério de aceitação 03
			if(basico) {
				if(especieId != null) de.addError(MODELO_BASICO_NAO_PODE_TER_ESPECIE);
				if(assuntoId != null) de.addError(MODELO_BASICO_NAO_PODE_TER_ASSUNTO);
			} else {
				if(especieId == null) de.addError(ESPECIE_OBRIGATORIA);
			}
		}
		
		if(nome == null || nome.trim().length() == 0 ) de.addError(NOME_OBRIGATORIO);
		if(conteudo == null || conteudo.trim().length() == 0 ) de.addError(CONTEUDO_OBRIGATORIO);
		if(abrangencia == null) de.addError(ABRANGENCIA_OBRIGATORIO);
		if(modeloEstruturaId == null) de.addError(MODELO_ESTRUTURA_OBRIGATORIO);
		
		//Critério de aceitação 04 - regras para o frontend, exibir o orgao do usuario chamando o servico de usuario
		
		//Critério de aceitação 05
		if(abrangencia != null) { 
			if(abrangencia.equals(TipoAbrangencia.ORGAO) && basico != null && basico) {
				de.addError(ABRANGENCIA_ORGAO_NAO_PODE_SER_MODELO_BASICO);
			}
		}
		
		//Critério de aceitação 06 - funcionalidade reference ao servico de modelo de estrurtura
		
		de.throwException();
	}
	
	public ModeloConteudo withId(Long id) {
		this.id = id;
		return this;
	}
}
