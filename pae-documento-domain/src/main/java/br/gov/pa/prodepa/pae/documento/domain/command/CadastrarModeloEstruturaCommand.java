package br.gov.pa.prodepa.pae.documento.domain.command;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.gov.pa.prodepa.pae.documento.domain.common.SelfValidating;
import br.gov.pa.prodepa.pae.documento.domain.model.FormatoPapel;
import br.gov.pa.prodepa.pae.documento.domain.model.OrientacaoPapel;
import lombok.Getter;

@Getter
public class CadastrarModeloEstruturaCommand extends SelfValidating<CadastrarModeloEstruturaCommand>{

	@NotNull
	private String cabecalho;
	
	private String rodape;
	
	@NotNull
	private String titulo;
	
	@NotNull
	private FormatoPapel formato;
	
	@NotNull
	private OrientacaoPapel orientacao;
	
	@NotNull
	private String margemTopo;
	
	@NotNull
	private String margemRodape;
	
	@NotNull
	private String margemDireita;
	
	@NotNull
	private String margemEsquerda;
	
	@NotNull
	private List<Long> especiesId;
	
	@NotNull
	private Long orgaoId;
	
	public CadastrarModeloEstruturaCommand() {
	}

	public CadastrarModeloEstruturaCommand(String cabecalho, String rodape, String titulo, FormatoPapel formato,
			OrientacaoPapel orientacao, String margemTopo, String margemRodape, String margemDireita,
			String margemEsquerda, List<Long> especiesId, Long orgaoId) {
		super();
		this.cabecalho = cabecalho;
		this.rodape = rodape;
		this.titulo = titulo;
		this.formato = formato;
		this.orientacao = orientacao;
		this.margemTopo = margemTopo;
		this.margemRodape = margemRodape;
		this.margemDireita = margemDireita;
		this.margemEsquerda = margemEsquerda;
		this.especiesId = especiesId;
		this.orgaoId = orgaoId;
		
		this.validateSelf();
	}
	
}
