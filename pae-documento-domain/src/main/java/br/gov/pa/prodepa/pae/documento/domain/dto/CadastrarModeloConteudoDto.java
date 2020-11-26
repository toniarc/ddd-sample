package br.gov.pa.prodepa.pae.documento.domain.dto;

import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarModeloConteudoDto {
	private String nome;
	private String conteudo;
	private Boolean basico;
	private Long especieId;
	private Long assuntoId;
	private Long modeloEstruturaId;
	private TipoAbrangencia abrangencia;
}
