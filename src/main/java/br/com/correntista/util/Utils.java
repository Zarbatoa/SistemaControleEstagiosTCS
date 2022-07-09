package br.com.correntista.util;

import java.util.HashMap;

import br.com.correntista.entidade.TipoInatividade;

public class Utils {

	static HashMap<TipoInatividade, String> mapaTipoInatividade;
	static HashMap<Integer, String> mapaNomesDosMeses;
	
	static {
		mapaTipoInatividade = new HashMap<>();
		mapaNomesDosMeses = new HashMap<>();
		
		mapaTipoInatividade.put(TipoInatividade.ESTAGIARIO_NAO_EFETIVADO, "Estagiário não foi efetivado");
		mapaTipoInatividade.put(TipoInatividade.ESTAGIARIO_EFETIVADO, "Estagiário foi efetivado");
		mapaTipoInatividade.put(TipoInatividade.DESAPROVACAO_EMPRESA, "Desaprovação por parte da unidade concedente");
		mapaTipoInatividade.put(TipoInatividade.DESAPROVACAO_ESTAGIARIO, "Desaprovação pela parte do estagiário");
		mapaTipoInatividade.put(TipoInatividade.DESAPROVACAO_AMBOS, "Desaprovação entre ambas as partes");
		mapaTipoInatividade.put(TipoInatividade.TRANCAMENTO_MATRICULA, "Trancamento da matrícula do aluno");
		
		mapaNomesDosMeses.put(1, "Janeiro");
		mapaNomesDosMeses.put(2, "Fevereiro");
		mapaNomesDosMeses.put(3, "Março");
		mapaNomesDosMeses.put(4, "Abril");
		
		mapaNomesDosMeses.put(5, "Maio");
		mapaNomesDosMeses.put(6, "Junho");
		mapaNomesDosMeses.put(7, "Julho");
		mapaNomesDosMeses.put(8, "Agosto");
		
		mapaNomesDosMeses.put(9, "Setembro");
		mapaNomesDosMeses.put(10, "Outubro");
		mapaNomesDosMeses.put(11, "Novembro");
		mapaNomesDosMeses.put(12, "Dezembro");
	}
	
	/**
	 * @param cnpj, texto com 14 digitos numéricos para formatar
	 * @return texto com a mascara de cnpj
	 * */
	public static String formatarCnpj(String cnpj) {
		String formattedCnpj = null;
		if (cnpj != null) {
			formattedCnpj = cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
		}
		return formattedCnpj;
	}
	
	/**
	 * @param cnpj, texto com a mascara de cnpj (##.###.###/####-##)
	 * @return texto não formatado com 14 digitos numéricos
	 * */
	public static String desformatarCnpj(String cnpj) {
		String formattedCnpj = null;
		if (cnpj != null) {
			formattedCnpj = cnpj.replaceAll("(\\d{2})\\.(\\d{3})\\.(\\d{3})/(\\d{4})\\-(\\d{2})", "$1$2$3$4$5");
		}
		return formattedCnpj;
	}
	
	/**
	 * @param cpf, texto com 11 digitos numéricos para formatar
	 * @return texto com a mascara de cpf
	 * */
	public static String formatarCpf(String cpf) {
		String formattedCpf = null;
		if (cpf != null) {
			formattedCpf = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
		}
		return formattedCpf;
	}
	
	/**
	 * @param cpf, texto com a mascara de cpf (###.###.###-##)
	 * @return texto não formatado com 11 digitos numéricos
	 * */
	public static String desformatarCpf(String cpf) {
		String formattedCpf = null;
		if (cpf != null) {
			formattedCpf = cpf.replaceAll("(\\d{3})\\.(\\d{3})\\.(\\d{3})\\-(\\d{2})", "$1$2$3$4");
		}
		return formattedCpf;
	}
	
	
	/**
	 * @param um enum tipoInatividade
	 * @return texto com uma descrição referente ao valor do enum
	 * */
	public static String mapearTipoInatividade(TipoInatividade tipo) {
		return mapaTipoInatividade.get(tipo);
	}
	
	
	/**
	 * @param um int referenciando um mês
	 * @return o nome do mês em extenso textualmente
	 * */
	public static String mapearMesExtenso(int mes) {
		return mapaNomesDosMeses.get(mes);
	}
	
	
}
