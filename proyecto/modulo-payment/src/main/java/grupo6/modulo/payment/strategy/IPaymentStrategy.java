package grupo6.modulo.payment.strategy;

import grupo6.persistencia.entidades.FacturaCompra;

/**
 * Interfaz comun para los medios de pago y así pagar
 * los elementos del carrito.
 * 
 * 
 * @author caespinosam
 *
 */
public interface IPaymentStrategy {
	
	/**
	 * Pagar el total de los elementos del carrito.
	 * @param userName el username del usuario
	 * @return la factura de la compra. 
	 */
	public FacturaCompra pagar(String userName);

}
