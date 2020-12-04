package br.gov.pa.prodepa.pae.documento.domain.test.unit;

import static org.junit.Assert.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import br.gov.pa.prodepa.pae.documento.domain.dto.CadastrarModeloConteudoDto;
import br.gov.pa.prodepa.pae.documento.domain.exception.DomainException;
import br.gov.pa.prodepa.pae.documento.domain.exception.ModeloConteudoValidationMessages;
import br.gov.pa.prodepa.pae.documento.domain.exception.ObjectStorageException;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import br.gov.pa.prodepa.pae.documento.domain.service.CadastrarModeloConteudoDomainService;

public class CadastrarModeloConteudoTest {

	private ModeloConteudoRepositoryMock modeloConteudoRepostiory = new ModeloConteudoRepositoryMock();
	
	private ModeloEstruturaRepositoryMock modeloEstruturaRepository = new ModeloEstruturaRepositoryMock();
	
	private GerarThumbnailPortMock gerarThumbnailPort = new GerarThumbnailPortMock();
	
	private ObjectStoragePortMock objectStorage = new ObjectStoragePortMock();
	
	CadastrarModeloConteudoDomainService service;
	
	@Before
	public void preTests() {
		this.service = CadastrarModeloConteudoDomainService.builder()
				.modeloConteudoRepostiory(modeloConteudoRepostiory) 
				.modeloEstruturaRepository(modeloEstruturaRepository)
				.gerarThumbnailPort(gerarThumbnailPort)
				.objectStorage(objectStorage)
				.build();
	}
	
	@Test
	public void deveCadastrarModeloConteudoBasicoComSucesso() throws ObjectStorageException {
		
		CadastrarModeloConteudoDto dto = new CadastrarModeloConteudoDto();
		dto.setAbrangencia(TipoAbrangencia.ORGAO);
		dto.setAssuntoId(1L);
		dto.setBasico(false);
		dto.setConteudo("conteudo");
		dto.setEspecieId(1L);
		dto.setModeloEstruturaId(1L);
		dto.setNome("nome");
		
		ModeloConteudo mc = service.cadastrar(dto);
		assertTrue(mc.getId().equals(1L));
		assertTrue(mc.isAtivo());
		assertTrue(mc.getAuditoria().getManutUsuarioId().equals(3199L));
		assertTrue(mc.getAuditoria().getManutData() != null);
		assertTrue(mc.getThumbnail() != null);
		assertTrue(mc.getOrgaoId() == 35L);
		
	}
	
	@Test
	public void deveCadastrarModeloConteudoNaoBasicoComSucesso() throws ObjectStorageException {
		
		CadastrarModeloConteudoDto dto = new CadastrarModeloConteudoDto();
		dto.setAbrangencia(TipoAbrangencia.ORGAO);
		dto.setBasico(false);
		dto.setConteudo("conteudo");
		dto.setAssuntoId(1L);
		dto.setEspecieId(1L);
		dto.setModeloEstruturaId(1L);
		dto.setNome("nome");
		
		ModeloConteudo mc = service.cadastrar(dto);
		assertTrue(mc.getId().equals(1L));
	}
	
	@Test
	public void deveFalharQuandoCadastrarModeloBasicoInformandoEspecieEAssunto() throws ObjectStorageException {
		
		CadastrarModeloConteudoDto dto = new CadastrarModeloConteudoDto();
		dto.setAbrangencia(TipoAbrangencia.ORGAO);
		dto.setBasico(true);
		dto.setConteudo("conteudo");
		dto.setAssuntoId(1L);
		dto.setEspecieId(1L);
		dto.setModeloEstruturaId(1L);
		dto.setNome("nome");
		
		Assertions.assertThatThrownBy( () -> service.cadastrar(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message")
			.hasMessageContaining(ModeloConteudoValidationMessages.MODELO_BASICO_NAO_PODE_TER_ASSUNTO)
			.hasMessageContaining(ModeloConteudoValidationMessages.MODELO_BASICO_NAO_PODE_TER_ESPECIE);
	}
	
	@Test
	public void deveFalharQuandoJaExistirUmModeloBasicoCadastrado() throws ObjectStorageException {
		
		modeloConteudoRepostiory.setModeloConteudoDoTipoBasicoAtivos(1L);
		
		CadastrarModeloConteudoDto dto = new CadastrarModeloConteudoDto();
		dto.setAbrangencia(TipoAbrangencia.ESTADO);
		dto.setBasico(true);
		dto.setConteudo("conteudo");
		dto.setModeloEstruturaId(1L);
		dto.setNome("nome");
		
		Assertions.assertThatThrownBy( () -> service.cadastrar(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message")
			.hasMessageContaining(ModeloConteudoValidationMessages.JA_EXISTE_MODELO_BASICO_ATIVO);
	}
	
	@Test
	public void deveFalharQuandoJaExistirUmModeloComAMesmaEspecieAssuntEAbrangenciaCadastrado() throws ObjectStorageException {
		
		modeloConteudoRepostiory.setTotalModelosEspecieAbrangenciaAssuntiAtivos(1L);
		
		CadastrarModeloConteudoDto dto = new CadastrarModeloConteudoDto();
		dto.setAbrangencia(TipoAbrangencia.ESTADO);
		dto.setBasico(true);
		dto.setConteudo("conteudo");
		dto.setModeloEstruturaId(1L);
		dto.setNome("nome");
		
		Assertions.assertThatThrownBy( () -> service.cadastrar(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message")
			.hasMessageContaining(ModeloConteudoValidationMessages.JA_EXISTE_MODELO_COM_ESPECIE_ABRANGENCIA_ASSUNTO);
	}
	
	@Test
	public void deveFalharQuandoNaoInformarCampoObrigatorios() throws ObjectStorageException {
		
		CadastrarModeloConteudoDto dto = new CadastrarModeloConteudoDto();
		dto.setBasico(true);
		
		Assertions.assertThatThrownBy( () -> service.cadastrar(dto))
		.isInstanceOf(DomainException.class)
		.hasFieldOrProperty("message")
			.hasMessageContaining(ModeloConteudoValidationMessages.NOME_OBRIGATORIO)
			.hasMessageContaining(ModeloConteudoValidationMessages.CONTEUDO_OBRIGATORIO)
			.hasMessageContaining(ModeloConteudoValidationMessages.ABRANGENCIA_OBRIGATORIO)
			.hasMessageContaining(ModeloConteudoValidationMessages.MODELO_ESTRUTURA_OBRIGATORIO);
	}
}
