package br.gov.pa.prodepa.pae.documento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.pa.prodepa.pae.documento.domain.dto.ModelosEstruturaElementoListaDto;
import br.gov.pa.prodepa.pae.documento.entity.ModeloEstruturaEntity;

@Repository
public interface ModeloEstruturaJpaRepository extends JpaRepository<ModeloEstruturaEntity, Long> {

	@Query("select new br.gov.pa.prodepa.pae.documento.domain.dto.ModelosEstruturaElementoListaDto(me.id, me.titulo, me.ativo, me.thumbnail) "
			+ "from ModeloEstruturaEntity me "
			+ "order by me.titulo")
	Page<ModelosEstruturaElementoListaDto> findAllModelosEstrutura(Pageable page);

}
