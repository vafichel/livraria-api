package br.com.livraria.util;

public class LivrariaUtil {
	
	public static String montarParametro(String key, String value) {
		String parametro = "";
		if (key != null) {
			parametro = key.concat("=").concat(value);
		}
		
		return parametro;
	}
}
