package br.gov.pa.prodepa.pae.documento.domain.port;

public interface GerarThumbnailPort {

	byte[] gerarThumbnail(String conteudo, String cabecalho, String rodape, String margemTopo, String margemRodape, String margemEsquerda, String margemDireita, String formato);
}
