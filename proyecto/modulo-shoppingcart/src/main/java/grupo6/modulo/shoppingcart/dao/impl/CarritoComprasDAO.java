package grupo6.modulo.shoppingcart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.shoppingcart.dao.view.ICarritoComprasDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.Usuario;
import grupo6.utilidades.Currency;

/**
 * Implementación de {@link ICarritoComprasDAO}
 * 
 * @author Alejo
 *
 */
@Repository(value = "carritoComprasDAO")
public class CarritoComprasDAO extends BaseDAO implements ICarritoComprasDAO {

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
	 * Busca un producto por id
	 * 
	 * @param idProducto
	 * @return el producto encontrado o null si no se encuentra el producto
	 */
	private Producto buscarProductoPorId(Long idProducto) {
		return (Producto) getCurrentSession().get(Producto.class, idProducto);
	}

	/**
	 * @see grupo6.modulo.shoppingcart.dao.view.ICarritoComprasDAO#agregar(Long)
	 */
	@Override
	@Transactional
	public Boolean agregar(String userName, Long idProducto) {
		Usuario usuario = buscarUsuarioPorUserName(userName);
		Producto producto = buscarProductoPorId(idProducto);
		
		if (usuario != null 
		 && producto != null 
		 && usuario.getCarritoCompras() != null 
		 && !usuario.getCarritoCompras().contains(producto)) {
			
			usuario.getCarritoCompras().add(producto);
			getCurrentSession().update(usuario);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @see grupo6.modulo.shoppingcart.dao.view.ICarritoComprasDAO#remover(Long)
	 */
	@Override
	@Transactional
	public Boolean remover(String userName, Long idProducto) {
		Usuario usuario = buscarUsuarioPorUserName(userName);
		Producto producto = buscarProductoPorId(idProducto);

		if (usuario != null && producto != null) {
			usuario.getCarritoCompras().remove(producto);
			getCurrentSession().update(usuario);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @see grupo6.modulo.shoppingcart.dao.view.ICarritoComprasDAO#consultarCarritoCompras()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CarritoProductoResponseDTO> consultarCarritoCompras(String userName,TipoMoneda tipoMonedaUsuario) {
		List<CarritoProductoResponseDTO> carrito = new ArrayList<CarritoProductoResponseDTO>();
		Usuario usuario = buscarUsuarioPorUserName(userName);
		if (usuario != null && usuario.getCarritoCompras() != null) {
			for(Producto p : usuario.getCarritoCompras()){
				CarritoProductoResponseDTO c = CarritoProductoResponseDTO.productoToCarritoProductoDTO(p);
				double valor = Currency.getConversion(p.getTipoMoneda(), tipoMonedaUsuario, p.getPrecio());
				c.setPrecio(valor);
				carrito.add(c);
			}
			return carrito;
			
		} else {
			return new ArrayList<CarritoProductoResponseDTO>();
		}
	}

	/**
	 * @see grupo6.modulo.shoppingcart.dao.view.ICarritoComprasDAO#getTotalCarritoCompras()
	 */
	@Override
	@Transactional(readOnly = true)
	public Double getTotalCarritoCompras(String userName,TipoMoneda tipoMonedaUsuario) {
		Usuario usuario = buscarUsuarioPorUserName(userName);

		if (usuario != null) {
			double total = 0;
			for (Producto p : usuario.getCarritoCompras()) {
				double valor = Currency.getConversion(p.getTipoMoneda(), tipoMonedaUsuario, p.getPrecio());
				total += valor;
			}
			return total;
		} else {
			return 0d;
		}
	}

}
