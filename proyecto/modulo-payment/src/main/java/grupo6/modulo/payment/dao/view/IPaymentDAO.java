package grupo6.modulo.payment.dao.view;


import grupo6.persistencia.entidades.FacturaCompra;
import grupo6.persistencia.entidades.Usuario;

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
	public FacturaCompra pagoPSE(Usuario userCompra);
	
	
	/**
	 * Metodo que permite hacer le pago por PSE
	 * @return factura de la factura generada
	 */
	public FacturaCompra pagoCreditCard(Usuario userCompra);
	
	/**
	 * Metodo que permite realizar el pago 
	 * mediante efectivo en un punto de pago
	 * @return
	 */
	public FacturaCompra cashOnDelivery(Usuario userCompra);
}
