package grupo6.modulo.product.factory;

import grupo6.persistencia.entidades.Producto;

import java.util.List;

/**
 * Interfaz base de los daos de busquedas de producto. Define los metodos
 * a se fabricados.
 * @author caespinosam
 *
 */
public interface IBusquedaProducto {
		
	
	/**
	* Metodo de busqueda.
	* 
	* @param parametros los parametros a filtrar.
	* @return lista de productos filtrados por el buscador.
	*/
	List<Producto> buscar(Object... parametros);
}
