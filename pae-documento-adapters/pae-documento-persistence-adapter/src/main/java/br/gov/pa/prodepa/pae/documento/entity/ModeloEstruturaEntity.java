package br.gov.pa.prodepa.pae.documento.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.pa.prodepa.pae.documento.domain.model.FormatoPapel;
import br.gov.pa.prodepa.pae.documento.domain.model.OrientacaoPapel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="modelo_estrutura", schema="pae")
public class ModeloEstruturaEntity {

	@Id
	@SequenceGenerator(name="MODELO_ESTRUTURA_ID_GENERATOR", sequenceName="pae.sq_modelo_estrutura", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MODELO_ESTRUTURA_ID_GENERATOR")
	private Long id;
	
	private String cabecalho;
	
	private String rodape;
	
	private String titulo;
	
	private String thumbnail;
	
	private boolean ativo;
	
	@Enumerated(EnumType.STRING)
	private FormatoPapel formato;
	
	@Enumerated(EnumType.STRING)
	private OrientacaoPapel orientacao;
	
	@Column(name="margem_topo")
	private String margemTopo;
	
	@Column(name="margem_rodape")
	private String margemRodape;
	
	@Column(name="margem_direita")
	private String margemDireita;
	
	@Column(name="margem_esquerda")
	private String margemEsquerda;
	
	private Long manutUsuarioId;
	
	private Date manutData;
	
	@ElementCollection
    @CollectionTable(name = "modelo_estrutura_especie", schema="pae", joinColumns = @JoinColumn(name = "modelo_estrutura_id"))
    @Column(name = "especie_id")
	private List<Long> especiesId;
	
	@Column(name="orgao_id")
	private Long orgaoId;
	
	public ModeloEstruturaEntity() {
	}
	
	@Builder
	public ModeloEstruturaEntity(Long id, String cabecalho, String rodape, String titulo, String thumbnail,
			boolean ativo, FormatoPapel formato, OrientacaoPapel orientacao, String margemTopo, String margemRodape,
			String margemDireita, String margemEsquerda, Long manutUsuarioId, Date manutData, List<Long> especiesId,
			Long orgaoId) {
		super();
		this.id = id;
		this.cabecalho = cabecalho;
		this.rodape = rodape;
		this.titulo = titulo;
		this.thumbnail = thumbnail;
		this.ativo = ativo;
		this.formato = formato;
		this.orientacao = orientacao;
		this.margemTopo = margemTopo;
		this.margemRodape = margemRodape;
		this.margemDireita = margemDireita;
		this.margemEsquerda = margemEsquerda;
		this.manutUsuarioId = manutUsuarioId;
		this.manutData = manutData;
		this.especiesId = especiesId;
		this.orgaoId = orgaoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloEstruturaEntity other = (ModeloEstruturaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
