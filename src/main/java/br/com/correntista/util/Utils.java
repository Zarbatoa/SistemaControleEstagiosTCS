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
	
}
