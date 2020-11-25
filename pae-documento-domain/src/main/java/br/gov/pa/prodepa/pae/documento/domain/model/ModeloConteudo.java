package br.gov.pa.prodepa.pae.documento.domain.model;

import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModeloConteudo {

	private Long id;
	private String nome;
	private byte[] thumbnail;
	private boolean ativo;
	private String conteudo;
	private Boolean basico;
	private Long orgaoId;
	private Long localizacaoId;
	private Long especieId;
	private Long assuntoId;
	private Long modeloEstruturaId;
	private Auditoria auditoria;
	private TipoAbrangencia abrangencia;
	private Long tipoId;

	public void validarCampos(Usuario usuario) {
		
		DomainException de = new DomainException();
		
		//TODO os perfis estao confusos 
		//TODO campo basico na tabela modelo_conteudo deve ser boolean
		
		//TODO as validacoes baseadas no perfil do usuario nao sao necessarias uma vez que todos os campos sao obrigatorios 
		//TODO pra que serve o campo tipoId?
		//TODO pra que serve o campo localizacaoId?
		
		//Critério de aceitação 01
		if(usuario.getRoles().contains(ApplicationRoles.GESTOR_DO_SISTEMA) || usuario.getRoles().contains(ApplicationRoles.ADMINISTRADOR_DO_ORGAO)) {
			if(nome == null) de.addError("Campo nome obrigatório") ;
			if(basico == null) de.addError("Campo básico obrigatório") ;
			if(especieId == null) de.addError("Campo espécie obrigatório") ;
			if(assuntoId == null) de.addError("Campo assunto obrigatório") ;
			//TODO abrangencia nao existe na tabela modelo_conteudo
			if(abrangencia == null) de.addError("Campo abrangência obrigatório") ;
			if(conteudo == null) de.addError("Campo conteúdo obrigatório") ;
			if(modeloEstruturaId == null) de.addError("Campo modelo de estrutura obrigatório") ;
		}
		
		//Critério de aceitação 02
		if(usuario.getRoles().contains(ApplicationRoles.ADMINISTRADOR_DO_SISTEMA_NO_ORGAO)) {
			if(nome == null) de.addError("Campo nome obrigatório");
			if(especieId == null) de.addError("Campo espécie obrigatório");
			if(assuntoId == null) de.addError("Campo assunto obrigatório");
			if(abrangencia == null) de.addError("Campo abrangência obrigatório") ;
			if(conteudo == null) de.addError("Campo conteúdo obrigatório") ;
			if(modeloEstruturaId == null) de.addError("Campo modelo de estrutura obrigatório") ;
		}
		
		//Critério de aceitação 03
		if(basico != null && basico) {
			if(especieId == null) de.addError("Campo espécie obrigatório");
			if(assuntoId == null) de.addError("Campo assunto obrigatório");
			if(abrangencia == null) de.addError("Campo abrangência obrigatório");
		}

		//Critério de aceitação 04 - regras para o frontend, exibir o orgao do usuario chamando o servico de usuario
		
		//Critério de aceitação 05
		if(abrangencia != null && abrangencia.equals(TipoAbrangencia.ESTADO) && basico != null && basico) { 
			de.addError("Não é permitido definir o modelo como básico quando a abrangência é estadual");
		}
		
		//Critério de aceitação 06 - funcionalidade reference ao servico de modelo de estrurtura
		
		
		de.throwException();
	}
}
