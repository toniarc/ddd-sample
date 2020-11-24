package br.gov.pa.prodepa.pae.documento.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.prodepa.pae.documento.application.service.CadastrarModeloEstruturaApplication;
import br.gov.pa.prodepa.pae.documento.domain.command.CadastrarModeloEstruturaCommand;

@RestController
public class CadastrarModeloEstruturaController {

	@Autowired
	private CadastrarModeloEstruturaApplication cadastrarModeloEstruturaApplicationService;

	@PostMapping("/modelos-estrutura")
	public void cadastrarModeloEstrutura(@RequestBody CadastrarModeloEstruturaCommand command) {
		
		cadastrarModeloEstruturaApplicationService.cadastrarModeloEstrutura(command);
	}
	
}
