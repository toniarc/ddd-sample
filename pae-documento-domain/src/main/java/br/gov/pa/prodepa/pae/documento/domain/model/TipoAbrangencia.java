package br.gov.pa.prodepa.pae.documento.domain.model;

import java.util.ArrayList;
import java.util.List;

public enum TipoAbrangencia {
	ESTADO, ORGAO;
	
	public static List<TipoAbrangencia> buscarTiposAbrangenciaBaseadoNoPerfilDoUsuario(Usuario usuario) {
	
		List<TipoAbrangencia> list = new ArrayList<TipoAbrangencia>(2);
		if(usuario.getRoles().contains(ApplicationRoles.GESTOR_DO_SISTEMA)) {
			list.add(TipoAbrangencia.ESTADO);
		}
		
		if(usuario.getRoles().contains(ApplicationRoles.ADMINISTRADOR_DO_SISTEMA_NO_ORGAO)) {
			list.add(TipoAbrangencia.ORGAO);
		}
		
		return list;
	}
}
