package grupo6.marketplace.ecoturismo.util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtilidades {

	public static final String CODIGO_COLOMBIA = "CO";
	public static final String LENGUAJE_ESPANOL = "es";
	public static final String LENGUAJE_INGLES = "en";
	
	public static String formatoDinero(double precio,String lenguaje,String pais) {
		Locale locale = new Locale(lenguaje,pais);
 	    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
 	   
 	    return currencyFormatter.format(precio);

	}

}
