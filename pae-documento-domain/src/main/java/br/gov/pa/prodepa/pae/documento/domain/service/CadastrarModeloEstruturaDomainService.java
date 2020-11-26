package br.gov.pa.prodepa.pae.documento.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.model.Auditoria;
import br.gov.pa.prodepa.pae.documento.domain.model.Especie;
import br.gov.pa.prodepa.pae.documento.domain.model.Margem;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.domain.model.Orgao;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.story.CadastrarModeloEstruturaStory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarModeloEstruturaDomainService implements CadastrarModeloEstruturaStory {

	private final ModeloEstruturaRepository modeloConteudoRepository;
	
	private final GerarThumbnailPort gerarThumbnailPort;
			
	public void cadastrarModeloEstrutura(CadastrarModeloEstruturaDto dto) {
				
		Auditoria auditoria = null;
		
		Margem margens = new Margem(dto.getMargemTopo(), dto.getMargemDireita(), dto.getMargemRodape(), dto.getMargemEsquerda());
		Orgao orgao = new Orgao(1L, null);
		List<Especie> especies = dto.getEspeciesId().stream().map( id -> new Especie(id, null)).collect(Collectors.toList());
		
		ModeloEstruturaAggregateRoot modeloEstrutura = ModeloEstruturaAggregateRoot.builder()
				.cabecalho(dto.getCabecalho())
				.rodape(dto.getRodape()) 
				.titulo(dto.getTitulo()) 
				.ativo(true) 
				.formato(dto.getFormato())
				.orientacao(dto.getOrientacao()) 
				.margens(margens) 
				.auditoria(auditoria) 
				.especies(especies)
				.orgao(orgao)
				.build();
		
		modeloEstrutura.validarCamposObrigatorios();
		
		modeloConteudoRepository.cadastrarModeloEstrutura(modeloEstrutura);
	}

	public byte[] gerarThumbnail(CadastrarModeloEstruturaDto command) {
		return gerarThumbnailPort.gerarThumbnail("", command.getCabecalho(), command.getRodape(), command.getMargemTopo(), command.getMargemDireita(), command.getMargemEsquerda(), command.getMargemDireita(), command.getFormato().getDescricao());
	}
}
