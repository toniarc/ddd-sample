package br.gov.pa.prodepa.pae.documento.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Usuario {

	private Long id;
	private String nome;
	private List<ApplicationRoles> roles;
	private List<String> scopes;
}
