package br.gov.pa.prodepa.pae.documento.domain.port;

public interface GerarPdfPort {

	byte[] gerarPdf(String conteudo, String cabecalho, String rodape, String margemTopo, String margemRodape, String margemEsquerda, String margemDireita, String formato);
}
