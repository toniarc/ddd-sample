package br.gov.pa.prodepa.pae.documento.persistence.test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.pa.prodepa.pae.documento.domain.model.Auditoria;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import br.gov.pa.prodepa.pae.documento.entity.ModeloConteudoEntity;
import br.gov.pa.prodepa.pae.documento.entity.ModeloEstruturaEntity;
import br.gov.pa.prodepa.pae.documento.mapper.ModeloConteudoMapper;

@RunWith(SpringRunner.class)
public class ModeloConteudoMapperTest {

	@Test
	public void converterAggregateRootToEntity() {
		
		Date now = new Date();
		ModeloConteudo modeloConteudo = ModeloConteudo.builder()
				.id(1L)
				.nome("nome01")
				.thumbnail("key01")
				.ativo(true)
				.conteudo("conteudo")
				.orgaoId(1L)
				.especieId(2L)
				.assuntoId(3L)
				.modeloEstruturaId(2L)
				.auditoria(Auditoria.builder().manutUsuarioId(3199L).manutData(now).build())
				.abrangencia(TipoAbrangencia.ORGAO)
				.basico(false)
				.build();
		
		ModeloConteudoEntity entity = ModeloConteudoMapper.INSTANCE.map(modeloConteudo);
		assertTrue(entity.getId().equals(1L));
		assertTrue(entity.getNome().equals("nome01"));
		assertTrue(entity.getThumbnail().equals("key01"));
		assertTrue(entity.getAtivo());
		assertTrue(entity.getConteudo().equals("conteudo"));
		assertTrue(entity.getOrgaoId().equals(1L));
		assertTrue(entity.getEspecieId().equals(2L));
		assertTrue(entity.getAssuntoId().equals(3L));
		assertTrue(entity.getModeloEstrutura().getId().equals(2L));
		assertTrue(entity.getUsuarioManutId().equals(3199L));
		assertTrue(entity.getDataManut().equals(now));
		assertTrue(entity.getAbrangencia().equals(TipoAbrangencia.ORGAO));
		assertTrue(entity.getBasico().equals(false));
	}
	
	@Test
	public void converterEntityToAggregateRoot() {
		
		Date now = new Date();
		ModeloConteudoEntity modeloConteudo = ModeloConteudoEntity.builder()
				.id(1L)
				.nome("nome01")
				.thumbnail("key01")
				.ativo(true)
				.conteudo("conteudo")
				.orgaoId(1L)
				.especieId(2L)
				.assuntoId(3L)
				.modeloEstrutura(ModeloEstruturaEntity.builder().id(2L).build())
				.usuarioManutId(3199L)
				.dataManut(now)
				.abrangencia(TipoAbrangencia.ORGAO)
				.basico(false)
				.build();
		
		ModeloConteudo domain = ModeloConteudoMapper.INSTANCE.map(modeloConteudo);
		assertTrue(domain.getId().equals(1L));
		assertTrue(domain.getNome().equals("nome01"));
		assertTrue(domain.getThumbnail().equals("key01"));
		assertTrue(domain.isAtivo());
		assertTrue(domain.getConteudo().equals("conteudo"));
		assertTrue(domain.getOrgaoId().equals(1L));
		assertTrue(domain.getEspecieId().equals(2L));
		assertTrue(domain.getAssuntoId().equals(3L));
		assertTrue(domain.getModeloEstruturaId().equals(2L));
		assertTrue(domain.getAuditoria().getManutUsuarioId().equals(3199L));
		assertTrue(domain.getAuditoria().getManutData().equals(now));
		assertTrue(domain.getAbrangencia().equals(TipoAbrangencia.ORGAO));
		assertTrue(domain.getBasico().equals(false));
		
	}
}
