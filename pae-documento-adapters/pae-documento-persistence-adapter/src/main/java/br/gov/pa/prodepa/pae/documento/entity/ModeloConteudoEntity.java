package br.gov.pa.prodepa.pae.documento.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="modelo_conteudo", schema="pae")
public class ModeloConteudoEntity {

	@Id
	private Long id;
	private String nome;
	
	@Lob
	@Column(name="thumbnai")
	private byte[] thumbnail;
	
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
	private Long usuarioManut;
	
	@Column(name="manut_data")
	private Date dataManut;
	
	@Enumerated(EnumType.STRING)
	private TipoAbrangencia abrangencia;
	
	private Boolean basico;

}
