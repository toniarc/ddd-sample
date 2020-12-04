package br.gov.pa.prodepa.pae.documento.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.prodepa.pae.documento.application.service.ModeloEstruturaService;
import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosEstruturaElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;

@RestController
@RequestMapping("/modelos-estrutura")
public final class ModeloEstruturaController {

	private final ModeloEstruturaService modeloEstruturaApplicationService;
	
	@Autowired
	public ModeloEstruturaController(final ModeloEstruturaService cadastrarModeloEstruturaApplicationService) {
		this.modeloEstruturaApplicationService = cadastrarModeloEstruturaApplicationService;
	}

	@PostMapping
	public ModeloEstruturaAggregateRoot cadastrarModeloEstrutura(@RequestBody final CadastrarModeloEstruturaDto command) throws ObjectStorageException {
		return modeloEstruturaApplicationService.cadastrarModeloEstrutura(command);
	}
	
	@GetMapping("/{id}")
	public ModeloEstruturaAggregateRoot cadastrarModeloEstrutura(@PathVariable("id") Long id) {
		return modeloEstruturaApplicationService.buscarPorId(id);
	}
	
	@GetMapping("/thumbnail/{id}")
	public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable("id") String id) throws ObjectStorageException  {
	    HttpHeaders headers = new HttpHeaders();
	    byte[] media = modeloEstruturaApplicationService.buscarThumbnailPorId(id);
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	    headers.set("Content-Type", "image/png");
	     
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}
	
	@GetMapping
	public SearchResponse<ModelosEstruturaElementoListaDto> listar(@RequestParam(required=true) int pageSize, @RequestParam(required=true) int pageNumber) {
		return modeloEstruturaApplicationService.listar(new FiltrosPesquisaModeloEstruturaDto(pageNumber, pageSize));
	}
}
