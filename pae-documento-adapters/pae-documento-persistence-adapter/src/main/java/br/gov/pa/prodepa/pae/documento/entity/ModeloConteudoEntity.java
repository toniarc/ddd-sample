package br.gov.pa.prodepa.pae.documento.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="modelo_conteudo", schema="pae")
public class ModeloConteudoEntity {

	@Id
	@SequenceGenerator(name="MODELO_CONTEUDO_ID_GENERATOR", sequenceName="pae.sq_modelo_conteudo", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MODELO_CONTEUDO_ID_GENERATOR")
	private Long id;
	
	private String nome;
	
	@Lob
	@Column(name="thumbnai")
	private String thumbnail;
	
	private Boolean ativo;
	
	@Lob
	private String conteudo;
	
	@Column(name="orgao_id")
	private Long orgaoId;
	
	@Column(name="especie_id")
	private Long especieId;
	
	@Column(name="assunto_id")
	private Long assuntoId;
	
	@ManyToOne
	@JoinColumn(name="modelo_estrutura_id")
	private ModeloEstruturaEntity modeloEstrutura;
	
	@Column(name="manut_usuario_id")
	private Long usuarioManutId;
	
	@Column(name="manut_data")
	private Date dataManut;
	
	@Enumerated(EnumType.STRING)
	private TipoAbrangencia abrangencia;
	
	private Boolean basico;
	
	public ModeloConteudoEntity() {
	}

	@Builder
	public ModeloConteudoEntity(Long id, String nome, String thumbnail, Boolean ativo, String conteudo, Long orgaoId,
			Long especieId, Long assuntoId, ModeloEstruturaEntity modeloEstrutura, Long usuarioManutId, Date dataManut,
			TipoAbrangencia abrangencia, Boolean basico) {
		super();
		this.id = id;
		this.nome = nome;
		this.thumbnail = thumbnail;
		this.ativo = ativo;
		this.conteudo = conteudo;
		this.orgaoId = orgaoId;
		this.especieId = especieId;
		this.assuntoId = assuntoId;
		this.modeloEstrutura = modeloEstrutura;
		this.usuarioManutId = usuarioManutId;
		this.dataManut = dataManut;
		this.abrangencia = abrangencia;
		this.basico = basico;
	}

	
}
