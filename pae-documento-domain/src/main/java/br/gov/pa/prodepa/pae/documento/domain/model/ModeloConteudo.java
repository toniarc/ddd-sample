package br.gov.pa.prodepa.pae.documento.domain.model;

import java.util.function.Supplier;

import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class ModeloConteudo {

	private final Long id;
	private final String nome;
	private final byte[] thumbnail;
	private final boolean ativo;
	private final String conteudo;
	private final Boolean basico;
	private final Long orgaoId;
	private final Long especieId;
	private final Long assuntoId;
	private final Long modeloEstruturaId;
	private final Auditoria auditoria;
	private final TipoAbrangencia abrangencia;

	@Builder
	public ModeloConteudo(Long id, String nome, Supplier<byte[]> thumbnail, boolean ativo, String conteudo, Boolean basico,
			Long orgaoId, Long especieId, Long assuntoId, Long modeloEstruturaId, Auditoria auditoria,
			TipoAbrangencia abrangencia) {
		super();
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
		this.conteudo = conteudo;
		this.basico = basico;
		this.orgaoId = orgaoId;
		this.especieId = especieId;
		this.assuntoId = assuntoId;
		this.modeloEstruturaId = modeloEstruturaId;
		this.auditoria = auditoria;
		this.abrangencia = abrangencia;
		
		validarCampos();
		
		this.thumbnail = thumbnail.get();
	}
	
	private void validarCampos() {
		
		DomainException de = new DomainException();

		//Critério de aceitação 01 e 02 sao referentes ao frontend
		
		//campos obrigatorios
		if(basico == null)  {
			de.addError("O campo básico é obrigatório");
		} else {
			//Critério de aceitação 03
			if(basico) {
				if(especieId != null) de.addError("O campo espécie não deve ser informado quando o modelo for do tipo básico");
				if(assuntoId != null) de.addError("O campo assunto não deve ser informado quando o modelo for do tipo básico");
				if(abrangencia != null) de.addError("O campo abrangência não deve ser informado quando o modelo for do tipo básico");
			} else {
				if(especieId == null) de.addError("O campo espécie é obrigatório");
				if(assuntoId == null) de.addError("O campo assunto é obrigatório");
				if(abrangencia == null) de.addError("O campo abrangência é obrigatório");
			}
		}
		
		if(nome == null || nome.trim().length() == 0 ) de.addError("O campo nome é obrigatório");
		if(conteudo == null || conteudo.trim().length() == 0 ) de.addError("O campo conteúdo é obrigatório");
		if(abrangencia == null) de.addError("O campo abrangência é obrigatório");
		
		//Critério de aceitação 04 - regras para o frontend, exibir o orgao do usuario chamando o servico de usuario
		
		//Critério de aceitação 05
		if(abrangencia != null && abrangencia.equals(TipoAbrangencia.ESTADO) && basico != null && basico) { 
			de.addError("Não é permitido definir o modelo como básico quando a abrangência é estadual");
		}
		
		//Critério de aceitação 06 - funcionalidade reference ao servico de modelo de estrurtura
		
		de.throwException();
	}
	
}
