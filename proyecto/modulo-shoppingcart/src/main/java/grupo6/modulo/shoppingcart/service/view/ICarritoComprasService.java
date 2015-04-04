package grupo6.modulo.shoppingcart.service.view;

import java.util.List;

import grupo6.modulo.shoppingcart.dao.impl.CarritoProductoResponseDTO;

/**
 * Servcios expuestos por el módulo de shoppingcart.
 * 
 * @author Alejo
 */
public interface ICarritoComprasService {

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
	List<CarritoProductoResponseDTO> consultarCarritoCompras(String userName);

	/**
	 * Retorna el valor total del carrito de compras.
	 * 
	 * @return el valor total del carrito de compras.
	 */
	Double getTotalCarritoCompras(String userName);
}
