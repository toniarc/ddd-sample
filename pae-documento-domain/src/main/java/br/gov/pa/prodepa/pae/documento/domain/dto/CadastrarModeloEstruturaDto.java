package br.gov.pa.prodepa.pae.documento.domain.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.gov.pa.prodepa.pae.documento.domain.common.SelfValidating;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloEstruturaValidationMessages.*;
import br.gov.pa.prodepa.pae.documento.domain.model.FormatoPapel;
import br.gov.pa.prodepa.pae.documento.domain.model.OrientacaoPapel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CadastrarModeloEstruturaDto extends SelfValidating<CadastrarModeloEstruturaDto> {

	@NotEmpty(message=CABECALHO_REQUERIDO)
	private String cabecalho;
	
	private String rodape;
	
	@NotEmpty(message=TITULO_REQUERIDO)
	private String titulo;
	
	@NotNull(message=FORMATO_REQUERIDO)
	private FormatoPapel formato;
	
	@NotNull(message=ORIENTACAO_REQUERIDA)
	private OrientacaoPapel orientacao;
	
	@NotEmpty(message=MARGEM_TOPO_REQUERIDA)
	private String margemTopo;
	
	@NotEmpty(message=MARGEM_RODAPE_REQUERIDA)
	private String margemRodape;
	
	@NotEmpty(message=MARGEM_DIREITA_REQUERIDA)
	private String margemDireita;
	
	@NotEmpty(message=MARGEM_ESQUERDA_REQUERIDA)
	private String margemEsquerda;
	
	@NotNull(message=ESPECIE_REQUERIDA)
	@Size(min=1, message=ESPECIE_REQUERIDA)
	private List<Long> especiesId;
	
}
