package grupo6.modulo.payment.strategy;

import grupo6.modulo.payment.dao.enums.TiposPago;
import grupo6.persistencia.entidades.FacturaCompra;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion de la estrategia {@link IPaymentStrategy}. 
 * Pago con tarjeta de credito. 
 * @author caespinosam
 *
 */
@Repository(value = "paymentCreditCardStrategy") 
public class PaymentCreditCardStrategy extends PaymentBaseStrategy implements IPaymentStrategy {


	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.payment.strategy.IPaymentStrategy#pagar(java.lang.String)
	 */
	@Override
	@Transactional
	public FacturaCompra pagar(String userName) {
		FacturaCompra factura = new FacturaCompra();

		try {

			Usuario usuario = buscarUsuarioPorUserName(userName);

			if (usuario != null && usuario.getCarritoCompras() != null && !usuario.getCarritoCompras().isEmpty()) {
				List<Producto> productoComprados = new ArrayList<Producto>();
				productoComprados.addAll(usuario.getCarritoCompras());
				
				factura.setFechaPago(new Date());
				factura.setProductosComprados(productoComprados);
				factura.setTipoPago(TiposPago.CREDIT_CARD);

				double precioTota = 0;
				for (Producto producto : usuario.getCarritoCompras()) {
					precioTota += producto.getPrecio();
				}

				factura.setTotalPagado(precioTota);
				getCurrentSession().save(factura);

				limpiarCarritoCompras(usuario);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return factura;
	}

}
