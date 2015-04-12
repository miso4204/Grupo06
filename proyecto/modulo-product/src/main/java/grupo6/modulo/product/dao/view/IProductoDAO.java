package grupo6.modulo.product.dao.view;

import grupo6.persistencia.entidades.Producto;

import java.util.List;

/**
 * Metodos de acceso a datos.
 * 
 * @author caespinosam
 * 
 */
public interface IProductoDAO {

	/**
	 * Crear un producto.
	 * @param producto el producto a crear.
	 * @return el id del producto creado.
	 */
	Long crear(Producto producto);

	/**
	 * Buscar un producto por llame primaria.
	 * @param id la llave a buscar.
	 * @return el producto, null en caso que no exista.
	 */
	Producto buscarPorId(Long id);

	
	/**
	 * Retorna todos los productos o paquetes del sistema.
	 * @return todos los productos o paquetes del sistema.
	 */
	List<Producto> listarTodos();


}
