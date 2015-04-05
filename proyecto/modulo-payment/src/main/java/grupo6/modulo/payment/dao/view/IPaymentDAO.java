package grupo6.modulo.payment.dao.view;


import grupo6.persistencia.entidades.FacturaCompra;

/**
 * Insterface que define los metodos necesarios para
 * realizar pagos en turismos market place
 * @author jhon
 *
 */
public interface IPaymentDAO {


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
