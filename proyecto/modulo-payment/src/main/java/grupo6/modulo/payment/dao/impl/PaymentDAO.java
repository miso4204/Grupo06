package grupo6.modulo.payment.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import grupo6.modulo.payment.dao.enums.TiposPago;
import grupo6.modulo.payment.dao.view.IPaymentDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.FacturaCompra;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.Usuario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "paymentDAO")
public class PaymentDAO extends BaseDAO implements IPaymentDAO {

	private static final String USER_NAME = "usuario";

	/**
	 * Busca un Usuario por userName
	 * 
	 * @param userName
	 * @return el usuario encontrado o null si no se encontro
	 */
	private Usuario buscarUsuarioPorUserName(String userName) {
		Criteria criteria = getCurrentSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq(USER_NAME, userName));
		return (Usuario) criteria.uniqueResult();
	}

	/**
	 * Limpia el carrito de compras al pagar exitosamente
	 * 
	 * @param userCompra
	 */
	private void limpiarCarritoCompras(Usuario userCompra) {
		// Limpiar el carrito de compras
		userCompra.getCarritoCompras().clear();
		getCurrentSession().update(userCompra);
	}

	/**
	 * Pagar por PSE
	 */
	@Transactional
	public FacturaCompra pagoPSE(String userName) {

		FacturaCompra factura = new FacturaCompra();

		try {

			Usuario usuario = buscarUsuarioPorUserName(userName);

			if (usuario != null && usuario.getCarritoCompras() != null && !usuario.getCarritoCompras().isEmpty()) {
				
				List<Producto> productoComprados = new ArrayList<Producto>();
				productoComprados.addAll(usuario.getCarritoCompras());
				
				factura.setFechaPago(new Date());
				factura.setProductosComprados(productoComprados);
				factura.setTipoPago(TiposPago.PSE);

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

	/**
	 * Pagar con tarjeta de credito
	 */
	@Transactional
	public FacturaCompra pagoCreditCard(String userName) {
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

	/**
	 * Pagar con efectivo
	 */
	@Transactional
	public FacturaCompra cashOnDelivery(String userName) {

		FacturaCompra factura = new FacturaCompra();

		try {

			Usuario usuario = buscarUsuarioPorUserName(userName);

			if (usuario != null && usuario.getCarritoCompras() != null && !usuario.getCarritoCompras().isEmpty()) {
				List<Producto> productoComprados = new ArrayList<Producto>();
				productoComprados.addAll(usuario.getCarritoCompras());
				
				factura.setFechaPago(new Date());
				factura.setProductosComprados(productoComprados);
				factura.setTipoPago(TiposPago.CASH_ON_DELIVERY);

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
