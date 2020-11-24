package br.gov.pa.prodepa.pae.documento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pa.prodepa.pae.documento.entity.ModeloEstruturaEntity;

@Repository
public interface ModeloEstruturaJpaRepository extends JpaRepository<ModeloEstruturaEntity, Long> {

}
