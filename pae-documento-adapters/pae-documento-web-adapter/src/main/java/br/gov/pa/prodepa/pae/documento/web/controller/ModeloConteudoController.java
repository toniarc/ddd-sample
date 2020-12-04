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

import br.gov.pa.prodepa.pae.documento.application.service.ModeloConteudoService;
import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.FiltrosPesquisaModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosConteudoElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.dto.SearchResponse;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;

@RestController
@RequestMapping("/modelos-conteudo")
public final class ModeloConteudoController {
	
	private final ModeloConteudoService modeloConteudoService;
	
	@Autowired
	public ModeloConteudoController(ModeloConteudoService cadastrarModeloConteudoService) {
		this.modeloConteudoService = cadastrarModeloConteudoService;
	}

	@PostMapping
	public ModeloConteudo cadastrarModeloConteudo(@RequestBody CadastrarModeloConteudoDto dto) throws ObjectStorageException {
		return modeloConteudoService.cadastrar(dto);
	} 

	@GetMapping("/{id}")
	public ModeloConteudo cadastrarModeloConteudo(@PathVariable("id") Long id) {
		return modeloConteudoService.buscarPorId(id);
	}
	
	@GetMapping("/thumbnail/{id}")
	public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable("id") String id) throws ObjectStorageException  {
	    HttpHeaders headers = new HttpHeaders();
	    byte[] media = modeloConteudoService.buscarThumbnailPorId(id);
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	    headers.set("Content-Type", "image/png");
	     
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}
	
	@GetMapping
	public SearchResponse<ModelosConteudoElementoListaDto> listar(@RequestParam(required=true) int pageSize, @RequestParam(required=true) int pageNumber) {
		return modeloConteudoService.listar(new FiltrosPesquisaModeloConteudoDto(pageNumber, pageSize));
	}
}
