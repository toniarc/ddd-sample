package br.gov.pa.prodepa.pae.documento.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosEstruturaElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.port.ObjectStoragePort;
import br.gov.pa.prodepa.pae.documento.domain.service.BuscarModeloEstruturaPorIdDomainService;
import br.gov.pa.prodepa.pae.documento.domain.service.BuscarThumbnailModeloEstruturaPorIdDomainService;
import br.gov.pa.prodepa.pae.documento.domain.service.CadastrarModeloEstruturaDomainService;
import br.gov.pa.prodepa.pae.documento.domain.service.ListarModelosEstruturaDomainService;
import br.gov.pa.prodepa.pae.documento.domain.story.BuscarModeloEstruturaPorIdStory;
import br.gov.pa.prodepa.pae.documento.domain.story.BuscarThumbnailModeloEstruturaPorIdStory;
import br.gov.pa.prodepa.pae.documento.domain.story.CadastrarModeloEstruturaStory;
import br.gov.pa.prodepa.pae.documento.domain.story.ListarModelosEstruturaStory;

@Component
@Transactional
public class ModeloEstruturaApplicationService implements ModeloEstruturaService {

	private final ModeloEstruturaRepository modeloEstruturaRepository;
	private final GerarThumbnailPort gerarThumbnailPort;
	private final ObjectStoragePort objectStorage;
	
	@Autowired
	public ModeloEstruturaApplicationService(ModeloEstruturaRepository modeloEstruturaRepository,
			GerarThumbnailPort gerarThumbnailPort, ObjectStoragePort objectStorage) {
		super();
		this.modeloEstruturaRepository = modeloEstruturaRepository;
		this.gerarThumbnailPort = gerarThumbnailPort;
		this.objectStorage = objectStorage;
	}

	public ModeloEstruturaAggregateRoot cadastrarModeloEstrutura(CadastrarModeloEstruturaDto command) throws ObjectStorageException {
		CadastrarModeloEstruturaStory domainService = CadastrarModeloEstruturaDomainService.builder()
				.modeloEstruturaRepository(modeloEstruturaRepository)
				.gerarThumbnailPort(gerarThumbnailPort)
				.objectStorage(objectStorage)
				.build();
		return domainService.cadastrarModeloEstrutura(command);
	}

	public ModeloEstruturaAggregateRoot buscarPorId(Long id) {
		BuscarModeloEstruturaPorIdStory domainService = new BuscarModeloEstruturaPorIdDomainService(modeloEstruturaRepository);
		return domainService.buscarPorId(id);
	}
	
	public byte[] buscarThumbnailPorId(String objectID) throws ObjectStorageException {
		BuscarThumbnailModeloEstruturaPorIdStory domainService = new BuscarThumbnailModeloEstruturaPorIdDomainService(objectStorage);
		return domainService.buscarThumbnailPorId(objectID);
	}

	public SearchResponse<ModelosEstruturaElementoListaDto> listar(FiltrosPesquisaModeloEstruturaDto filtros) {
		ListarModelosEstruturaStory story = ListarModelosEstruturaDomainService.builder().repository(modeloEstruturaRepository).build();
		return story.listar(filtros);
	}
}
