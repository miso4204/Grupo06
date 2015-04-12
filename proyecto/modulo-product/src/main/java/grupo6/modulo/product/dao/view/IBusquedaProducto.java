package grupo6.modulo.product.dao.view;

import grupo6.persistencia.entidades.Producto;

import java.util.List;

/**
 * Interfaz base de los daos de busquedas de producto.
 * @author caespinosam
 *
 */
public interface IBusquedaProducto {

	 List<Producto> buscar(Object... parametros);
}
