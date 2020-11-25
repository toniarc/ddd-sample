package br.gov.pa.prodepa.pae.documento.domain.service;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.model.Auditoria;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstrutura;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloConteudoRepostiory;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.story.CadastrarModeloConteudoStory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarModeloConteudoDomainService implements CadastrarModeloConteudoStory {

	private final ModeloConteudoRepostiory modeloConteudoRepostiory;
	
	private final ModeloEstruturaRepository modeloEstruturaRepository;
	
	private final GerarThumbnailPort gerarThumbnailPort;
	
	public void cadastrar(CadastrarModeloConteudoDto dto) {
	
		dto.validateSelf();
		
		ModeloEstrutura me = modeloEstruturaRepository.buscarPorId(dto.getModeloEstruturaId());
		byte[] thumbnail = gerarThumbnailPort.gerarThumbnail(dto.getConteudo(), me.getCabecalho(), me.getRodape(), me.getMargens().getTopo(), 
				me.getMargens().getRodape(), me.getMargens().getEsquerda(), me.getMargens().getDireita(), me.getFormato().getDescricao());
		
		boolean ativo = true;
		Long orgaoId = null; //TODO obter orgao de onde?
		Long localizacaoId = null;
		Auditoria auditoria = null;
		Long tipoId = null;
		
		ModeloConteudo modeloConteudo = new ModeloConteudo(null, dto.getNome(), thumbnail, ativo, dto.getConteudo(), dto.getBasico(), 
				orgaoId, localizacaoId, dto.getEspecieId(), dto.getAssuntoId(), dto.getModeloEstruturaId(), auditoria, dto.getAbrangencia(), tipoId);
		
		modeloConteudoRepostiory.cadastrar(modeloConteudo);
	}
}
