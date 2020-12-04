package br.gov.pa.prodepa.pae.documento.domain.service;

import java.util.Date;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.Auditoria;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloConteudoRepository;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.port.ObjectStoragePort;
import br.gov.pa.prodepa.pae.documento.domain.story.CadastrarModeloConteudoStory;
import lombok.Builder;

@Builder
public class CadastrarModeloConteudoDomainService implements CadastrarModeloConteudoStory {

	private final ModeloConteudoRepository modeloConteudoRepostiory;
	
	private final ModeloEstruturaRepository modeloEstruturaRepository;
	
	private final GerarThumbnailPort gerarThumbnailPort;
	
	private final ObjectStoragePort objectStorage;
	
	public ModeloConteudo cadastrar(CadastrarModeloConteudoDto dto) throws ObjectStorageException {
	
		efetuarValidacoes(dto);
		
		//TODO obter orgao de onde?
		Long orgaoId = 35L;
		
		//TODO como obter o usuario logado
		Auditoria auditoria = Auditoria.builder().manutUsuarioId(3199L).manutData(new Date()).build();
		
		byte[] binaryData = gerarThumbnail(dto);
		String objectID = salvarThumbnailNoObjectStorage(binaryData);
		
		ModeloConteudo modeloConteudo = ModeloConteudo.builder()
				.nome(dto.getNome()) 
				.thumbnail(objectID) 
				.ativo(true) 
				.conteudo(dto.getConteudo()) 
				.basico(dto.getBasico()) 
				.orgaoId(orgaoId)
				.especieId(dto.getEspecieId())
				.assuntoId(dto.getAssuntoId())
				.modeloEstruturaId(dto.getModeloEstruturaId())
				.auditoria(auditoria)
				.abrangencia(dto.getAbrangencia())
				.build();
		
		Long id = null;
		try {
			id = modeloConteudoRepostiory.cadastrar(modeloConteudo);
		} catch (Exception e) {
			objectStorage.deleteObject(objectID);
			throw e;
		}
		
		return modeloConteudo.withId(id);
	}

	private void efetuarValidacoes(CadastrarModeloConteudoDto dto) {
		Long quantidadeModelosJaCadastrados = modeloConteudoRepostiory.contarTodosAtivosPor(dto.getEspecieId(), dto.getAbrangencia(), dto.getAssuntoId());
		if(quantidadeModelosJaCadastrados > 0) {
			throw new DomainException(ModeloConteudoValidationMessages.JA_EXISTE_MODELO_COM_ESPECIE_ABRANGENCIA_ASSUNTO);
		}
		
		Long quantidadeDeModelosDeConteudoBasicosJaCadastrados = modeloConteudoRepostiory.contarTodosModeloConteudoDoTipoBasicoAtivos();
		if(quantidadeDeModelosDeConteudoBasicosJaCadastrados > 0) {
			throw new DomainException(ModeloConteudoValidationMessages.JA_EXISTE_MODELO_BASICO_ATIVO);
		}
	}

	private byte[] gerarThumbnail(CadastrarModeloConteudoDto dto) {
		ModeloEstruturaAggregateRoot me = modeloEstruturaRepository.buscarPorId(dto.getModeloEstruturaId());
		byte[] thumbnail = gerarThumbnailPort.gerarThumbnail(dto.getConteudo(), me.getCabecalho(), me.getRodape(), me.getMargens().getTopo(), 
				me.getMargens().getRodape(), me.getMargens().getEsquerda(), me.getMargens().getDireita(), me.getFormato().getDescricao());
		return thumbnail;
	}
	
	public String salvarThumbnailNoObjectStorage(byte[] file) throws ObjectStorageException {
		return objectStorage.putObject(file, "image/png");
	}
}
