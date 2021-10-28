package app.backend.utils;

public class NumberUtils {
	
	public static Double formatDecimals(Double numero, Integer numeroDecimales) {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
	}

}
