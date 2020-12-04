package br.gov.pa.prodepa.pae.documento.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosConteudoElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloConteudoRepository;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.port.ObjectStoragePort;
import br.gov.pa.prodepa.pae.documento.domain.service.BuscarModeloConteudoPorIdDomainService;
import br.gov.pa.prodepa.pae.documento.domain.service.BuscarThumbnailModeloConteudoPorIdDomainService;
import br.gov.pa.prodepa.pae.documento.domain.service.CadastrarModeloConteudoDomainService;
import br.gov.pa.prodepa.pae.documento.domain.service.ListarModelosConteudoDomainService;
import br.gov.pa.prodepa.pae.documento.domain.story.BuscarModeloConteudoPorIdStory;
import br.gov.pa.prodepa.pae.documento.domain.story.BuscarThumbnailModeloConteudoPorIdStory;
import br.gov.pa.prodepa.pae.documento.domain.story.CadastrarModeloConteudoStory;
import br.gov.pa.prodepa.pae.documento.domain.story.ListarModelosConteudoStory;

@Component
@Transactional
public class ModeloConteudoApplicationService implements ModeloConteudoService {

	private final ModeloConteudoRepository modeloConteudoRepository;
	private final ModeloEstruturaRepository modeloEstruturaRepository;
	private final GerarThumbnailPort thumnailPort;
	private ObjectStoragePort objectStoragePort;

	@Autowired
	public ModeloConteudoApplicationService(ModeloConteudoRepository modeloConteudoRepository, ModeloEstruturaRepository modeloEstruturaRepository, 
			GerarThumbnailPort thumnailPort, ObjectStoragePort objectStoragePort) {
		this.modeloConteudoRepository = modeloConteudoRepository;
		this.modeloEstruturaRepository = modeloEstruturaRepository;
		this.thumnailPort = thumnailPort;
		this.objectStoragePort = objectStoragePort;
		
	}

	public ModeloConteudo cadastrar(CadastrarModeloConteudoDto dto) throws ObjectStorageException {
		CadastrarModeloConteudoStory cadastrarModeloConteudoStory = CadastrarModeloConteudoDomainService.builder()
				.modeloConteudoRepostiory(modeloConteudoRepository)
				.modeloEstruturaRepository(modeloEstruturaRepository) 
				.gerarThumbnailPort(thumnailPort) 
				.objectStorage(objectStoragePort)
				.build();
		return cadastrarModeloConteudoStory.cadastrar(dto);
	}
	
	public ModeloConteudo buscarPorId(Long id) {
		BuscarModeloConteudoPorIdStory story = BuscarModeloConteudoPorIdDomainService.builder().repository(modeloConteudoRepository).build();
		return story.buscarPorId(id);
	}
	
	public byte[] buscarThumbnailPorId(String objectId) throws ObjectStorageException {
		BuscarThumbnailModeloConteudoPorIdStory story = BuscarThumbnailModeloConteudoPorIdDomainService.builder()
				.objectStorage(objectStoragePort)
				.build();
		
		return story.buscarThumbnailPorId(objectId);		
	}
	
	public SearchResponse<ModelosConteudoElementoListaDto> listar(FiltrosPesquisaModeloConteudoDto filtros) {
		ListarModelosConteudoStory story = ListarModelosConteudoDomainService.builder().repository(modeloConteudoRepository).build();
		return story.listar(filtros);
	}
}
