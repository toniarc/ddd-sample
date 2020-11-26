package br.gov.pa.prodepa.pae.documento.domain.dto;

import java.util.List;

import br.gov.pa.prodepa.pae.documento.domain.model.FormatoPapel;
import br.gov.pa.prodepa.pae.documento.domain.model.OrientacaoPapel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CadastrarModeloEstruturaDto {

	private String cabecalho;
	private String rodape;
	private String titulo;
	private FormatoPapel formato;
	private OrientacaoPapel orientacao;
	private String margemTopo;
	private String margemRodape;
	private String margemDireita;
	private String margemEsquerda;
	private List<Long> especiesId;
	
}
