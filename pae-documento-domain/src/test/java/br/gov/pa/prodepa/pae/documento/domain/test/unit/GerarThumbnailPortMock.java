package br.gov.pa.prodepa.pae.documento.domain.test.unit;

import br.gov.pa.prodepa.pae.documento.domain.port.GerarThumbnailPort;

public class GerarThumbnailPortMock implements GerarThumbnailPort{

	public GerarThumbnailPortMock() {
	}
	
	@Override
	public byte[] gerarThumbnail(String conteudo, String cabecalho, String rodape, String margemTopo,
			String margemRodape, String margemEsquerda, String margemDireita, String formato) {
		return "teste".getBytes();
	}
}
