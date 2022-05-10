package br.com.correntista.util;

public class Utils {

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
	
}
