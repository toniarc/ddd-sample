package br.gov.pa.prodepa.pae.documento.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.prodepa.pae.documento.application.service.CadastrarModeloConteudoService;
import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloConteudoDto;

@RestController
public final class CadastrarModeloConteudoController {
	
	private final CadastrarModeloConteudoService cadastrarModeloConteudoService;
	
	@Autowired
	public CadastrarModeloConteudoController(CadastrarModeloConteudoService cadastrarModeloConteudoService) {
		this.cadastrarModeloConteudoService = cadastrarModeloConteudoService;
	}

	@PostMapping("/modelos-conteudo")
	public void cadastrarModeloConteudo(@RequestBody CadastrarModeloConteudoDto dto) {
		cadastrarModeloConteudoService.cadastrar(dto);
	} 

}
