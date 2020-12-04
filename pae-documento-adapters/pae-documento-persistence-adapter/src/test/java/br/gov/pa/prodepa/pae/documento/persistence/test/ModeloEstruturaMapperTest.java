package br.gov.pa.prodepa.pae.documento.persistence.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.pa.prodepa.pae.documento.domain.model.Auditoria;
import br.gov.pa.prodepa.pae.documento.domain.model.FormatoPapel;
import br.gov.pa.prodepa.pae.documento.domain.model.Margem;
import br.gov.pa.prodepa.pae.documento.domain.model.ModeloEstruturaAggregateRoot;
import br.gov.pa.prodepa.pae.documento.domain.model.Orgao;
import br.gov.pa.prodepa.pae.documento.domain.model.OrientacaoPapel;
import br.gov.pa.prodepa.pae.documento.entity.ModeloEstruturaEntity;
import br.gov.pa.prodepa.pae.documento.mapper.ModeloEstruturaMapper;

@RunWith(SpringRunner.class)
public class ModeloEstruturaMapperTest {

	@Test
	public void converterAggregateRootToEntity() {
		
		Date now = new Date();
		ModeloEstruturaAggregateRoot modeloEstrutura = ModeloEstruturaAggregateRoot.builder()
				.ativo(true)
				.auditoria(Auditoria.builder().manutUsuarioId(3199L).manutData(now).build())
				.cabecalho("cabecalho")
				.especiesId(Arrays.asList(2L))
				.formato(FormatoPapel.A4)
				.id(1L)
				.margens(new Margem("10mm", "10mm", "10mm", "10mm"))
				.orgao(new Orgao(1L, "orgao 1"))
				.orientacao(OrientacaoPapel.PORTRAIT)
				.rodape("rodape")
				.thumbnail("key01")
				.titulo("titulo")
				.build();
		
		ModeloEstruturaEntity entity = ModeloEstruturaMapper.INSTANCE.map(modeloEstrutura);
		assertTrue(entity.isAtivo());
		assertTrue(entity.getManutUsuarioId().equals(3199L));
		assertTrue("validacao manutData", entity.getManutData().equals(now));
		assertTrue(entity.getCabecalho().equals("cabecalho"));
		assertTrue(entity.getEspeciesId() != null);
		assertTrue(entity.getEspeciesId().size() == 1);
		assertTrue(entity.getEspeciesId().get(0) == 2L);
		assertTrue(entity.getFormato().equals(FormatoPapel.A4));
		assertTrue(entity.getId() == 1L);
		assertTrue(entity.getMargemDireita().equals("10mm"));
		assertTrue(entity.getMargemEsquerda().equals("10mm"));
		assertTrue(entity.getMargemTopo().equals("10mm"));
		assertTrue(entity.getMargemRodape().equals("10mm"));
		assertTrue(entity.getOrgaoId() == 1L);
		assertTrue(entity.getOrientacao().equals(OrientacaoPapel.PORTRAIT));
		assertTrue(entity.getRodape().equals("rodape"));
		assertTrue(entity.getThumbnail().equals("key01"));
		assertTrue(entity.getTitulo().equals("titulo"));
		
	}
	
	@Test
	public void converterEntityToAggregateRoot() {
		
		Date now = new Date();
		ModeloEstruturaEntity modeloEstrutura = ModeloEstruturaEntity.builder()
				.ativo(true)
				.manutUsuarioId(3199L)
				.manutData(now)
				.cabecalho("cabecalho")
				.especiesId(Arrays.asList(2L))
				.formato(FormatoPapel.A4)
				.id(1L)
				.margemDireita("10mm")
				.margemEsquerda("10mm")
				.margemTopo("10mm")
				.margemRodape("10mm")
				.orgaoId(1L)
				.orientacao(OrientacaoPapel.PORTRAIT)
				.rodape("rodape")
				.thumbnail("key01")
				.titulo("titulo")
				.build();
		
		ModeloEstruturaAggregateRoot entity = ModeloEstruturaMapper.INSTANCE.map(modeloEstrutura);
		assertTrue(entity.getAtivo());
		assertTrue(entity.getAuditoria().getManutUsuarioId().equals(3199L));
		assertTrue(entity.getAuditoria().getManutData().equals(now));
		assertTrue(entity.getCabecalho().equals("cabecalho"));
		assertTrue(entity.getEspeciesId() != null);
		assertTrue(entity.getEspeciesId().size() == 1);
		assertTrue(entity.getEspeciesId().get(0) == 2L);
		assertTrue(entity.getFormato().equals(FormatoPapel.A4));
		assertTrue(entity.getId() == 1L);
		assertTrue(entity.getMargens().getDireita().equals("10mm"));
		assertTrue(entity.getMargens().getEsquerda().equals("10mm"));
		assertTrue(entity.getMargens().getTopo().equals("10mm"));
		assertTrue(entity.getMargens().getRodape().equals("10mm"));
		assertTrue(entity.getOrgao().getId() == 1L);
		assertTrue(entity.getOrientacao().equals(OrientacaoPapel.PORTRAIT));
		assertTrue(entity.getRodape().equals("rodape"));
		assertTrue(entity.getThumbnail().equals("key01"));
		assertTrue(entity.getTitulo().equals("titulo"));
		
	}
}
