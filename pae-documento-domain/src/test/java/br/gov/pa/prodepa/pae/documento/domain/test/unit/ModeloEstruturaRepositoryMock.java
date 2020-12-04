package br.gov.pa.prodepa.pae.documento.domain.test.unit;

import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosEstruturaElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.model.FormatoPapel;
import br.gov.pa.prodepa.pae.documento.domain.model.Margem;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;

public class ModeloEstruturaRepositoryMock implements ModeloEstruturaRepository{

	public ModeloEstruturaRepositoryMock() {
	}
	
	@Override
	public Long cadastrarModeloEstrutura(ModeloEstruturaAggregateRoot modeloEstrutura) {
		return 1L;
	}
	
	@Override
	public ModeloEstruturaAggregateRoot buscarPorId(Long id) {
		return ModeloEstruturaAggregateRoot.builder().id(1L).margens(new Margem("10mm", "10mm", "10mm", "10mm")).formato(FormatoPapel.A4).build();
	}

	@Override
	public SearchResponse<ModelosEstruturaElementoListaDto> listar(FiltrosPesquisaModeloEstruturaDto filtros) {
		return null;
	}
}
