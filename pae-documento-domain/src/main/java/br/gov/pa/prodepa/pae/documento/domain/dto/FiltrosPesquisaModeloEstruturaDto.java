package br.gov.pa.prodepa.pae.documento.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrosPesquisaModeloEstruturaDto extends FiltroPesquisaPaginada{

	public FiltrosPesquisaModeloEstruturaDto(Integer pageNumber, Integer pageSize) {
		super(pageNumber, pageSize);
	}
	
}
