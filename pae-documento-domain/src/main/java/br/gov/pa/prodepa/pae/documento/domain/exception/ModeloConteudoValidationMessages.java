package br.gov.pa.prodepa.pae.documento.domain.exception;

public class ModeloConteudoValidationMessages {

	public static final String BASICO_OBRIGATORIO = "O campo básico é obrigatório";
	public static final String MODELO_BASICO_NAO_PODE_TER_ESPECIE = "O campo espécie não deve ser informado quando o modelo for do tipo básico";
	public static final String MODELO_BASICO_NAO_PODE_TER_ASSUNTO = "O campo assunto não deve ser informado quando o modelo for do tipo básico";
	public static final String ESPECIE_OBRIGATORIA = "O campo espécie é obrigatório";
	public static final String ASSUNTO_OBRIGATORIO = "O campo assunto é obrigatório";
	
	public static final String NOME_OBRIGATORIO = "O campo nome é obrigatório";
	public static final String CONTEUDO_OBRIGATORIO = "O campo conteúdo é obrigatório";
	public static final String ABRANGENCIA_OBRIGATORIO = "O campo abrangência é obrigatório";
	
	public static final String ABRANGENCIA_ORGAO_NAO_PODE_SER_MODELO_BASICO = "Não é permitido definir o modelo como básico quando a abrangência é de órgão";
	
	public static final String JA_EXISTE_MODELO_COM_ESPECIE_ABRANGENCIA_ASSUNTO = "Já existe Modelo de Conteúdo, ativo, com os campos informados";
	public static final String JA_EXISTE_MODELO_BASICO_ATIVO = "Já existe Modelo de Conteúdo Básico e ativo";
	public static final String MODELO_ESTRUTURA_OBRIGATORIO = "Informe o modelo de estrutura";
}
