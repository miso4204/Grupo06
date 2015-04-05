package grupo6.modulo.payment.service.view;

import grupo6.persistencia.entidades.FacturaCompra;

/**
 * Interface que define que metodos se mostraran al usuario como servicios
 * @author jhon
 *
 */
public interface IPaymentService {



	/**
	 * Metodo que permite hacer le pago por PSE
	 * @return factura de la factura generada
	 */
	public FacturaCompra pagoPSE(String userName);
	
	
	/**
	 * Metodo que permite hacer le pago por PSE
	 * @return factura de la factura generada
	 */
	public FacturaCompra pagoCreditCard(String userName);
	
	/**
	 * Metodo que permite realizar el pago 
	 * mediante efectivo en un punto de pago
	 * @return
	 */
	public FacturaCompra cashOnDelivery(String userName);
	
}
