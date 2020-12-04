package br.gov.pa.prodepa.pae.documento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosConteudoElementoListaDto;
import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import br.gov.pa.prodepa.pae.documento.entity.ModeloConteudoEntity;

@Repository
public interface ModeloConteudoJpaRepository extends JpaRepository<ModeloConteudoEntity, Long> {

	Long countByEspecieIdAndAbrangenciaAndAssuntoIdAndAtivo(Long especieId, TipoAbrangencia abrangencia, Long assuntoId,
			boolean b);

	Long countByIdNotAndEspecieIdAndAbrangenciaAndAssuntoIdAndAtivo(Long modeloConteudoId, Long especieId,
			TipoAbrangencia abrangencia, Long assuntoId, boolean b);

	Long countByBasicoAndAtivo(boolean b, boolean c);

	Long countByIdNotAndBasicoAndAtivo(Long modeloConteudoId, boolean b, boolean c);

	@Query("select new br.gov.pa.prodepa.pae.documento.domain.dto.ModelosConteudoElementoListaDto(mc.id, mc.nome, mc.ativo, mc.basico, mc.thumbnail) "
			+ "from ModeloConteudoEntity mc "
			+ "order by mc.nome")
	Page<ModelosConteudoElementoListaDto> findAllModelosConteudo(PageRequest of);

}
