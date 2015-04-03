package grupo6.modulo.payment.dao.impl;

import java.util.Date;
import java.util.List;

import grupo6.modulo.payment.dao.enums.TiposPago;
import grupo6.modulo.payment.dao.view.IPaymentDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.FacturaCompra;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.Usuario;

import org.springframework.stereotype.Repository;

@Repository(value="paymentDAO")
public class PaymentDAO extends BaseDAO implements IPaymentDAO {

	@Override
	public FacturaCompra pagoPSE(Usuario userCompra, List<Producto> productos) {
		
		FacturaCompra factura = new FacturaCompra();
		
		try {
			
			factura.setFechaPago(new Date());
			factura.setProductosComprados(productos);
			factura.setTipoPago(TiposPago.PSE);
			factura.setUsuario(userCompra);
			
			double precioTota = 0;
			for (Producto producto : productos) {
				precioTota += producto.getPrecio();
			}
			
			factura.setTotalPagado(precioTota);
			getCurrentSession().save(factura);
			
			return factura;
		} catch (Exception e) {
			
			return null;
		}
	}

	@Override
	public FacturaCompra pagoCreditCard(Usuario userCompra, List<Producto> productos) {
		FacturaCompra factura = new FacturaCompra();
		
		try {
			
			factura.setFechaPago(new Date());
			factura.setProductosComprados(productos);
			factura.setTipoPago(TiposPago.CREDIT_CARD);
			factura.setUsuario(userCompra);
			
			double precioTota = 0;
			for (Producto producto : productos) {
				precioTota += producto.getPrecio();
			}
			
			factura.setTotalPagado(precioTota);
			getCurrentSession().save(factura);
			
			return factura;
		} catch (Exception e) {
			
			return null;
		}
	}

	@Override
	public FacturaCompra cashOnDelivery(Usuario userCompra, List<Producto> productos) {

		FacturaCompra factura = new FacturaCompra();

		try {
			
			factura.setFechaPago(new Date());
			factura.setProductosComprados(productos);
			factura.setTipoPago(TiposPago.CASH_ON_DELIVERY);
			factura.setUsuario(userCompra);
			
			double precioTota = 0;
			for (Producto producto : productos) {
				precioTota += producto.getPrecio();
			}
			
			factura.setTotalPagado(precioTota);
			getCurrentSession().save(factura);
			
			return factura;
		} catch (Exception e) {
			
			return null;
		}
	}

	
}
