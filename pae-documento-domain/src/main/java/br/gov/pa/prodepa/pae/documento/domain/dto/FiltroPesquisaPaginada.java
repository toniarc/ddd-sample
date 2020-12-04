package br.gov.pa.prodepa.pae.documento.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroPesquisaPaginada {

	private Integer pageNumber;
	private Integer pageSize;

	public FiltroPesquisaPaginada(Integer pageNumber, Integer pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
}
