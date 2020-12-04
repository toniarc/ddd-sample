package br.gov.pa.prodepa.pae.documento.domain.service;

import java.util.Date;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.Auditoria;
import br.gov.pa.prodepa.pae.documento.domain.model.Margem;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.domain.model.Orgao;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.port.ObjectStoragePort;
import br.gov.pa.prodepa.pae.documento.domain.story.CadastrarModeloEstruturaStory;
import lombok.Builder;

public class CadastrarModeloEstruturaDomainService implements CadastrarModeloEstruturaStory {

	private final ModeloEstruturaRepository modeloEstruturaRepository;

	private final GerarThumbnailPort gerarThumbnailPort;
	
	private final ObjectStoragePort objectStorage;

	@Builder
	private CadastrarModeloEstruturaDomainService(ModeloEstruturaRepository modeloEstruturaRepository,
			GerarThumbnailPort gerarThumbnailPort, ObjectStoragePort objectStorage) {
		super();
		this.modeloEstruturaRepository = modeloEstruturaRepository;
		this.gerarThumbnailPort = gerarThumbnailPort;
		this.objectStorage = objectStorage;
	}

	public ModeloEstruturaAggregateRoot cadastrarModeloEstrutura(CadastrarModeloEstruturaDto dto) throws ObjectStorageException {
		dto.validateSelf();

		//TODO alterar para o usuario do wso2
		Auditoria auditoria = Auditoria.builder().manutUsuarioId(3199L).manutData(new Date()).build();
		
		byte[] thumbnail = gerarThumbnail(dto);
		String objectID = salvarThumbnailNoObjectStorage(thumbnail);
		
		//TODO obter orgao do usuario
		Orgao orgao = new Orgao(1L, null);
		
		Margem margens = new Margem(dto.getMargemTopo(), dto.getMargemDireita(), dto.getMargemRodape(), dto.getMargemEsquerda());

		ModeloEstruturaAggregateRoot modeloEstrutura = ModeloEstruturaAggregateRoot.builder()
				.cabecalho(dto.getCabecalho()).rodape(dto.getRodape()).titulo(dto.getTitulo()).ativo(true)
				.formato(dto.getFormato()).orientacao(dto.getOrientacao()).margens(margens).auditoria(auditoria)
				.especiesId(dto.getEspeciesId()).orgao(orgao).thumbnail(objectID).build();

		Long id = null;
		
		try {
			id = modeloEstruturaRepository.cadastrarModeloEstrutura(modeloEstrutura);
		} catch (Exception e) {
			objectStorage.deleteObject(objectID);
		}
		modeloEstrutura.withId(id);
		return modeloEstrutura;
	}

	public byte[] gerarThumbnail(CadastrarModeloEstruturaDto command) {
		return gerarThumbnailPort.gerarThumbnail("", command.getCabecalho(), command.getRodape(),
				command.getMargemTopo(), command.getMargemDireita(), command.getMargemEsquerda(),
				command.getMargemDireita(), command.getFormato().getDescricao());
	}
	
	public String salvarThumbnailNoObjectStorage(byte[] file) throws ObjectStorageException {
		return objectStorage.putObject(file, "image/png");
	}

}
