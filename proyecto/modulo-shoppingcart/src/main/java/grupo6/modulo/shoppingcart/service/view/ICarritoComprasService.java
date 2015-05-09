package grupo6.modulo.shoppingcart.service.view;

import java.util.List;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.shoppingcart.dao.impl.CarritoProductoResponseDTO;
import grupo6.persistencia.entidades.dto.TotalCarritoDTO;

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
	List<CarritoProductoResponseDTO> consultarCarritoCompras(String userName,TipoMoneda tipoMoneda);

	/**
	 * Retorna el valor total del carrito de compras.
	 * 
	 * @return el valor total del carrito de compras.
	 */
	 List<TotalCarritoDTO> getTotalCarritoCompras(String userName,TipoMoneda tipoMoneda);
}
