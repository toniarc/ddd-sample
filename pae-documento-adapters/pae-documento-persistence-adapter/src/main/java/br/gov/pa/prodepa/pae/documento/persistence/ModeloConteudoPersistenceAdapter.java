package br.gov.pa.prodepa.pae.documento.persistence;

import org.springframework.stereotype.Component;

import br.gov.pa.prodepa.pae.documento.domain.model.ModeloConteudo;
import br.gov.pa.prodepa.pae.documento.domain.model.TipoAbrangencia;
import br.gov.pa.prodepa.pae.documento.domain.port.ModeloConteudoRepostiory;

@Component
public class ModeloConteudoPersistenceAdapter implements ModeloConteudoRepostiory {

	@Override
	public void cadastrar(ModeloConteudo modeloConteudo) {
		// TODO Auto-generated method stub
	}

	@Override
	public Long contarTodosAtivosPor(Long especieId, TipoAbrangencia abrangencia, Long assuntoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long contarTodosAtivosPor(Long modeloConteudoId, Long especieId, TipoAbrangencia abrangencia,
			Long assuntoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long contarTodosModeloConteudoDoTipoBasicoAtivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long contarTodosModeloConteudoDoTipoBasicoAtivos(Long modeloConteudoId) {
		// TODO Auto-generated method stub
		return null;
	}

}
