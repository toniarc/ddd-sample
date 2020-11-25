package br.gov.pa.prodepa.pae.documento.domain.dto;

import javax.validation.constraints.NotBlank;

import br.gov.pa.prodepa.pae.documento.domain.common.SelfValidating;
import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CadastrarModeloConteudoDto extends SelfValidating<CadastrarModeloConteudoDto>{

	@NotBlank
	private String nome;
	
	@NotBlank
	private String conteudo;
	
	@NonNull
	private Boolean basico;
	
	@NonNull
	private Long especieId;
	
	@NonNull
	private Long assuntoId;
	
	@NonNull
	private Long modeloEstruturaId;
	
	@NonNull
	private TipoAbrangencia abrangencia;
}
