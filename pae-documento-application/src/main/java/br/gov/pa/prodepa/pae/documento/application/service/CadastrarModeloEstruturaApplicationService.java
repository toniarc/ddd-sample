package br.gov.pa.prodepa.pae.documento.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.service.CadastrarModeloEstruturaDomainService;

@Component
@Transactional
public class CadastrarModeloEstruturaApplicationService implements CadastrarModeloEstruturaService {

	private final ModeloEstruturaRepository modeloConteudoRepository;
	private final GerarThumbnailPort gerarThumbnailPort;
	
	@Autowired
	public CadastrarModeloEstruturaApplicationService(ModeloEstruturaRepository modeloConteudoRepository,
			GerarThumbnailPort gerarThumbnailPort) {
		super();
		this.modeloConteudoRepository = modeloConteudoRepository;
		this.gerarThumbnailPort = gerarThumbnailPort;
	}

	public void cadastrarModeloEstrutura(CadastrarModeloEstruturaDto command) {
		CadastrarModeloEstruturaDomainService domainService = new CadastrarModeloEstruturaDomainService(modeloConteudoRepository, gerarThumbnailPort);
		domainService.cadastrarModeloEstrutura(command);
	}

}
