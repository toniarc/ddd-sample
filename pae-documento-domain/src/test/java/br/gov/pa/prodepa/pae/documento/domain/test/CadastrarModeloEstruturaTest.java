package br.gov.pa.prodepa.pae.documento.domain.test;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloEstruturaDto;
import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import static br.gov.pa.prodepa.pae.documento.domain.exception.ModeloEstruturaValidationMessages.*;
import br.gov.pa.prodepa.pae.documento.domain.model.FormatoPapel;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.domain.model.OrientacaoPapel;
import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloEstruturaRepository;
import br.gov.pa.prodepa.pae.documento.domain.service.CadastrarModeloEstruturaDomainService;
import br.gov.pa.prodepa.pae.documento.domain.story.CadastrarModeloEstruturaStory;

public class CadastrarModeloEstruturaTest {

	ModeloEstruturaRepository repository = new ModeloEstruturaRepository() {
		
		@Override
		public void cadastrarModeloEstrutura(ModeloEstruturaAggregateRoot modeloEstrutura) {
		}
		
		@Override
		public ModeloEstruturaAggregateRoot buscarPorId(Long id) {
			return null;
		}
	};
	
	GerarThumbnailPort gerarThumbnailPort = new GerarThumbnailPort() {
		
		@Override
		public byte[] gerarThumbnail(String conteudo, String cabecalho, String rodape, String margemTopo,
				String margemRodape, String margemEsquerda, String margemDireita, String formato) {
			return "teste".getBytes();
		}
	};
	
	CadastrarModeloEstruturaStory domainService = new CadastrarModeloEstruturaDomainService(repository, gerarThumbnailPort);
	
	CadastrarModeloEstruturaDto dto = criarModeloEstruturaDto();
	
	private CadastrarModeloEstruturaDto criarModeloEstruturaDto() {
		CadastrarModeloEstruturaDto dto = new CadastrarModeloEstruturaDto();
		dto.setCabecalho("cabecalho");
		dto.setRodape("rodape");
		dto.setEspeciesId(Arrays.asList(2L));
		dto.setFormato(FormatoPapel.A4);
		dto.setMargemDireita("10mm");
		dto.setMargemEsquerda("10mm");
		dto.setMargemTopo("20mm");
		dto.setMargemRodape("20mm");
		dto.setTitulo("titulo");
		dto.setOrientacao(OrientacaoPapel.PORTRAIT);
		return dto;
	}
	
	@Test
	public void deveCadastrarComSucesso() {
		domainService.cadastrarModeloEstrutura(dto);
	}
	
	@Test
	public void deveFalharQuandoNaoInformarCabecalho() {
		dto.setCabecalho(null);
		Assertions.assertThatThrownBy( () -> domainService.cadastrarModeloEstrutura(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message").hasMessageContaining(CABECALHO_REQUERIDO);
	}
	
	@Test
	public void deveFalharQuandoNaoInformarTitulo() {
		dto.setTitulo(null);
		Assertions.assertThatThrownBy( () -> domainService.cadastrarModeloEstrutura(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message").hasMessageContaining(TITULO_REQUERIDO);
	}
	
	@Test
	public void deveFalharQuandoNaoInformarFormato() {
		dto.setFormato(null);
		Assertions.assertThatThrownBy( () -> domainService.cadastrarModeloEstrutura(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message").hasMessageContaining(FORMATO_REQUERIDO);
	}
	
	@Test
	public void deveFalharQuandoNaoInformarOrientacao() {
		dto.setOrientacao(null);
		Assertions.assertThatThrownBy( () -> domainService.cadastrarModeloEstrutura(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message").hasMessageContaining(ORIENTACAO_REQUERIDA);
	}
	
	@Test
	public void deveFalharQuandoNaoInformarEspecie() {
		dto.setEspeciesId(null);
		Assertions.assertThatThrownBy( () -> domainService.cadastrarModeloEstrutura(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message").hasMessageContaining(ESPECIE_REQUERIDA);
	}
	
	@Test
	public void deveFalharQuandoNaoInformarMargemTopo() {
		dto.setMargemTopo(null);
		Assertions.assertThatThrownBy( () -> domainService.cadastrarModeloEstrutura(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message").hasMessageContaining(MARGEM_TOPO_REQUERIDA);
	}
	
	@Test
	public void deveFalharQuandoNaoInformarMargemRodape() {
		dto.setMargemRodape(null);
		Assertions.assertThatThrownBy( () -> domainService.cadastrarModeloEstrutura(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message").hasMessageContaining(MARGEM_RODAPE_REQUERIDA);
	}
	
	@Test
	public void deveFalharQuandoNaoInformarMargemEsquerda() {
		dto.setMargemEsquerda(null);
		Assertions.assertThatThrownBy( () -> domainService.cadastrarModeloEstrutura(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message").hasMessageContaining(MARGEM_ESQUERDA_REQUERIDA);
	}
	
	@Test
	public void deveFalharQuandoNaoInformarMargemDireita() {
		dto.setMargemDireita(null);
		Assertions.assertThatThrownBy( () -> domainService.cadastrarModeloEstrutura(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message").hasMessageContaining(MARGEM_DIREITA_REQUERIDA);
	}
}
