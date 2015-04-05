package grupo6.modulo.payment.dao.impl;

import java.util.Date;

import grupo6.modulo.payment.dao.enums.TiposPago;
import grupo6.modulo.payment.dao.view.IPaymentDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.FacturaCompra;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.Usuario;

import org.springframework.stereotype.Repository;

@Repository(value="paymentDAO")
public class PaymentDAO extends BaseDAO implements IPaymentDAO {

	private void limpiarCarritoCompras(Usuario userCompra) {
		//Limpiar el carrito de compras
		userCompra.getCarritoCompras().clear();
		getCurrentSession().update(userCompra);
	}
	
	@Override
	public FacturaCompra pagoPSE(Usuario userCompra) {
		
		FacturaCompra factura = new FacturaCompra();
		
		try {
			
			factura.setFechaPago(new Date());
			factura.setProductosComprados(userCompra.getCarritoCompras());
			factura.setTipoPago(TiposPago.PSE);
			factura.setUsuario(userCompra);
			
			double precioTota = 0;
			for (Producto producto : userCompra.getCarritoCompras()) {
				precioTota += producto.getPrecio();
			}
			
			factura.setTotalPagado(precioTota);
			getCurrentSession().save(factura);
			
			limpiarCarritoCompras(userCompra);
			
			return factura;
		} catch (Exception e) {
			
			return null;
		}
	}

	@Override
	public FacturaCompra pagoCreditCard(Usuario userCompra) {
		FacturaCompra factura = new FacturaCompra();
		
		try {
			
			factura.setFechaPago(new Date());
			factura.setProductosComprados(userCompra.getCarritoCompras());
			factura.setTipoPago(TiposPago.CREDIT_CARD);
			factura.setUsuario(userCompra);
			
			double precioTota = 0;
			for (Producto producto : userCompra.getCarritoCompras()) {
				precioTota += producto.getPrecio();
			}
			
			factura.setTotalPagado(precioTota);
			getCurrentSession().save(factura);
			
			limpiarCarritoCompras(userCompra);
			
			return factura;
		} catch (Exception e) {
			
			return null;
		}
	}

	@Override
	public FacturaCompra cashOnDelivery(Usuario userCompra) {

		FacturaCompra factura = new FacturaCompra();

		try {
			
			factura.setFechaPago(new Date());
			factura.setProductosComprados(userCompra.getCarritoCompras());
			factura.setTipoPago(TiposPago.CASH_ON_DELIVERY);
			factura.setUsuario(userCompra);
			
			double precioTota = 0;
			for (Producto producto : userCompra.getCarritoCompras()) {
				precioTota += producto.getPrecio();
			}
			
			factura.setTotalPagado(precioTota);
			getCurrentSession().save(factura);
			
			limpiarCarritoCompras(userCompra);
			
			return factura;
		} catch (Exception e) {
			
			return null;
		}
	}

	
}
