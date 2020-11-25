package br.gov.pa.prodepa.pae.documento.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.prodepa.pae.documento.application.service.CadastrarModeloEstruturaService;
import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloEstruturaDto;

@RestController
public final class CadastrarModeloEstruturaController {

	private final CadastrarModeloEstruturaService cadastrarModeloEstruturaApplicationService;
	
	@Autowired
	public CadastrarModeloEstruturaController(final CadastrarModeloEstruturaService cadastrarModeloEstruturaApplicationService) {
		this.cadastrarModeloEstruturaApplicationService = cadastrarModeloEstruturaApplicationService;
	}

	@PostMapping("/modelos-estrutura")
	public void cadastrarModeloEstrutura(@RequestBody final CadastrarModeloEstruturaDto command) {
		cadastrarModeloEstruturaApplicationService.cadastrarModeloEstrutura(command);
	}
	
}
