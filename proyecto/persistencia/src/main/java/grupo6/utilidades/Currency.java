package grupo6.utilidades;

import grupo6.modulo.payment.dao.enums.TipoMoneda;

public class Currency {

	private static final double USD_TO_COP = 2518.89169;
	private static final double USD_TO_EUR = 0.944643869;
	
	private static final double EUR_TO_COP = 2666.49874;
	private static final double EUR_TO_USD = 1.0586;
	
	private static final double COP_TO_EUR = 0.000375023616;
	private static final double COP_TO_USD = 0.000397;
	
	 public static double getConversion(TipoMoneda tipoMonedaProducto,TipoMoneda tipoMonedaUsuario,double valor) {
	    	double valorFinal = valor;
	    	if(tipoMonedaUsuario != tipoMonedaProducto){
				if(tipoMonedaProducto == TipoMoneda.DOLAR && tipoMonedaUsuario == TipoMoneda.EURO ){
					valorFinal = valorFinal * USD_TO_EUR;
				}
				if(tipoMonedaProducto == TipoMoneda.DOLAR && tipoMonedaUsuario == TipoMoneda.COLOMBIAN_PESOS ){
					valorFinal = valorFinal * USD_TO_COP;
				}
				if(tipoMonedaProducto == TipoMoneda.EURO && tipoMonedaUsuario == TipoMoneda.DOLAR ){
					valorFinal = valorFinal * EUR_TO_USD;
				}
				if(tipoMonedaProducto == TipoMoneda.EURO && tipoMonedaUsuario == TipoMoneda.COLOMBIAN_PESOS ){
					valorFinal = valorFinal * EUR_TO_COP;
				}
				if(tipoMonedaProducto == TipoMoneda.COLOMBIAN_PESOS && tipoMonedaUsuario == TipoMoneda.DOLAR ){
					valorFinal = valorFinal * COP_TO_USD;
				}
				if(tipoMonedaProducto == TipoMoneda.COLOMBIAN_PESOS && tipoMonedaUsuario == TipoMoneda.EURO ){
					valorFinal = valorFinal * COP_TO_EUR;
				}
			}
	    	return valorFinal;
		}
}
