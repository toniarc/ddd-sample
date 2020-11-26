package br.gov.pa.prodepa.pae.documento.domain.service;

import java.util.Date;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import br.gov.pa.prodepa.pae.documento.domain.model.Auditoria;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
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
	
		efetuarValidacoes(dto);
		
		//TODO obter orgao de onde?
		Long orgaoId = null; 
		
		//TODO como obter o usuario logado
		Auditoria auditoria = Auditoria.builder().manutUsuarioId(3199L).manutData(new Date()).build();
		
		Boolean basico = dto.getBasico();
		if(dto.getAbrangencia() != null && dto.getAbrangencia().equals(TipoAbrangencia.ESTADO)) {
			basico = false;
		}
		
		ModeloConteudo modeloConteudo = ModeloConteudo.builder()
				.nome(dto.getNome()) 
				.thumbnail(() -> gerarThumbnail(dto)) 
				.ativo(true) 
				.conteudo(dto.getConteudo()) 
				.basico(basico) 
				.orgaoId(orgaoId)
				.especieId(dto.getEspecieId())
				.assuntoId(dto.getAssuntoId())
				.modeloEstruturaId(dto.getModeloEstruturaId())
				.auditoria(auditoria)
				.abrangencia(dto.getAbrangencia())
				.build();
		
		modeloConteudoRepostiory.cadastrar(modeloConteudo);
	}

	private void efetuarValidacoes(CadastrarModeloConteudoDto dto) {
		Long quantidadeModelosJaCadastrados = modeloConteudoRepostiory.contarTodosAtivosPor(dto.getEspecieId(), dto.getAbrangencia(), dto.getAssuntoId());
		if(quantidadeModelosJaCadastrados > 0) {
			throw new DomainException("Já existe Modelo de Conteúdo, ativo, com os campos informados");
		}
		
		Long quantidadeDeModelosDeConteudoBasicosJaCadastrados = modeloConteudoRepostiory.contarTodosModeloConteudoDoTipoBasicoAtivos();
		if(quantidadeDeModelosDeConteudoBasicosJaCadastrados > 0) {
			throw new DomainException("Já existe Modelo de Conteúdo Básico e ativo");
		}
	}

	private byte[] gerarThumbnail(CadastrarModeloConteudoDto dto) {
		ModeloEstruturaAggregateRoot me = modeloEstruturaRepository.buscarPorId(dto.getModeloEstruturaId());
		byte[] thumbnail = gerarThumbnailPort.gerarThumbnail(dto.getConteudo(), me.getCabecalho(), me.getRodape(), me.getMargens().getTopo(), 
				me.getMargens().getRodape(), me.getMargens().getEsquerda(), me.getMargens().getDireita(), me.getFormato().getDescricao());
		return thumbnail;
	}
	
}
