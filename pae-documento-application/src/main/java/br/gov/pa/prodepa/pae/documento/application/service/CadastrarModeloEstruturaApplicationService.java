package br.gov.pa.prodepa.pae.documento.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.command.CadastrarModeloEstruturaCommand;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.service.CadastrarModeloEstruturaDomainService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Transactional
public class CadastrarModeloEstruturaApplicationService implements CadastrarModeloEstruturaApplication {

	@Autowired
	private ModeloEstruturaRepository modeloConteudoRepository;
	
	@Autowired
	private GerarThumbnailPort gerarThumbnailPort;
	
	public void cadastrarModeloEstrutura(CadastrarModeloEstruturaCommand command) {
		CadastrarModeloEstruturaDomainService domainService = new CadastrarModeloEstruturaDomainService(modeloConteudoRepository, gerarThumbnailPort);
		domainService.cadastrarModeloEstrutura(command);
	}

}
