package grupo6.modulo.shoppingcart.dao.view;

import java.util.List;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.shoppingcart.dao.impl.CarritoProductoResponseDTO;

/**
 * Metodo de acceso a los datos del carrito de compras.
 * 
 * @author Alejo
 *
 */
public interface ICarritoComprasDAO {

	/**
	 * Agrega un producto al carrito de compras.
	 * 
	 * @param id
	 *            del producto a agregar.
	 * @return true si se pudo agregar, false en caso contrario
	 */
	Boolean agregar(String userName, Long idProducto);

	/**
	 * Remueve un producto del carrito de compras.
	 * 
	 * @param id
	 *            del producto a agregar.
	 * @return true si se pudo agregar, false en caso contrario
	 */
	Boolean remover(String userName, Long idProducto);

	/**
	 * Retorna la lista de productos que hacen parte del carrito de compras.
	 * 
	 * @return la lista de productos que hacen parte del carrito de compras.
	 */
	List<CarritoProductoResponseDTO> consultarCarritoCompras(String userName,TipoMoneda tipoMoneda);

	/**
	 * Retorna el valor total del carrito de compras.
	 * 
	 * @return el valor total del carrito de compras.
	 */
	Double getTotalCarritoCompras(String userName,TipoMoneda tipoMoneda);

}