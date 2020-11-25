package br.gov.pa.prodepa.pae.documento.domain.service;

import java.util.Set;
import java.util.stream.Collectors;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.model.Auditoria;
import br.gov.pa.prodepa.pae.documento.domain.model.Especie;
import br.gov.pa.prodepa.pae.documento.domain.model.Margem;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstrutura;
import br.gov.pa.prodepa.pae.documento.domain.model.Orgao;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.story.CadastrarModeloEstruturaStory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarModeloEstruturaDomainService implements CadastrarModeloEstruturaStory {

	private final ModeloEstruturaRepository modeloConteudoRepository;
	
	private final GerarThumbnailPort gerarThumbnailPort;
			
	public void cadastrarModeloEstrutura(CadastrarModeloEstruturaDto command) {
				
		command.validateSelf();
		
		byte[] thumbnail = gerarThumbnailPort.gerarThumbnail("", command.getCabecalho(), command.getRodape(), command.getMargemTopo(), command.getMargemDireita(), command.getMargemEsquerda(), command.getMargemDireita(), command.getFormato().getDescricao());
		Auditoria auditoria = null;
		
		Margem margens = new Margem(command.getMargemTopo(), command.getMargemDireita(), command.getMargemRodape(), command.getMargemEsquerda());
		Orgao orgao = new Orgao(command.getOrgaoId(), null);
		Set<Especie> especies = command.getEspeciesId().stream().map( id -> new Especie(id, null)).collect(Collectors.toSet());
		
		ModeloEstrutura modeloEstrutura = new ModeloEstrutura(null, command.getCabecalho(), command.getRodape(), 
				command.getTitulo(), thumbnail, true, command.getFormato(), command.getOrientacao(), margens, 
				auditoria, especies, orgao);
		
		modeloConteudoRepository.cadastrarModeloEstrutura(modeloEstrutura);
	}

}
