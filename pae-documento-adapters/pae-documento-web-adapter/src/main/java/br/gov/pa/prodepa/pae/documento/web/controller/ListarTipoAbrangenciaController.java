package br.gov.pa.prodepa.pae.documento.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.prodepa.pae.documento.domain.model.ApplicationRoles;
import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import br.gov.pa.prodepa.pae.documento.domain.model.Usuario;

@RestController
public class ListarTipoAbrangenciaController {

	@GetMapping("/tipos-abrangencia/por-perfil-usuario")
	public List<TipoAbrangencia> listarTiposAbrangenciaPorPerfilDoUSuario(){
		
		//TODO substituir com o usuario vindo do wso2
		Usuario user = new Usuario(3199L, "Antonio Junior", Arrays.asList(ApplicationRoles.GESTOR_DO_SISTEMA, ApplicationRoles.ADMINISTRADOR_DO_SISTEMA_NO_ORGAO), null);
		return TipoAbrangencia.buscarTiposAbrangenciaBaseadoNoPerfilDoUsuario(user);
	}
}
