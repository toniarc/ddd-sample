package br.gov.pa.prodepa.pae.documento.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloConteudoRepostiory;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.service.CadastrarModeloConteudoDomainService;
import br.gov.pa.prodepa.pae.documento.domain.story.CadastrarModeloConteudoStory;

@Component
@Transactional
public class CadastrarModeloConteudoApplicationService implements CadastrarModeloConteudoService {

	private final ModeloConteudoRepostiory modeloConteudoRepository;
	private final ModeloEstruturaRepository modeloEstruturaRepository;
	private final GerarThumbnailPort thumnailPort;

	@Autowired
	public CadastrarModeloConteudoApplicationService(ModeloConteudoRepostiory modeloConteudoRepository, ModeloEstruturaRepository modeloEstruturaRepository, GerarThumbnailPort thumnailPort) {
		this.modeloConteudoRepository = modeloConteudoRepository;
		this.modeloEstruturaRepository = modeloEstruturaRepository;
		this.thumnailPort = thumnailPort;
		
	}

	public void cadastrar(CadastrarModeloConteudoDto dto) {
		CadastrarModeloConteudoStory cadastrarModeloConteudoStory = new CadastrarModeloConteudoDomainService(modeloConteudoRepository, modeloEstruturaRepository, thumnailPort);
		cadastrarModeloConteudoStory.cadastrar(dto);
	}
}
