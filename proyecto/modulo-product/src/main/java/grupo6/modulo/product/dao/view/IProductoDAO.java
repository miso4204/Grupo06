package grupo6.modulo.product.dao.view;

import grupo6.persistencia.entidades.Producto;

import java.util.Date;
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
	 * Lista de productos filtrados por ubicacion.
	 * @param ubicacion la ubicacion a filtrar.
	 * @return Lista de productos filtrados por ubicacion.
	 */
	List<Producto> buscarPorUbicacion(String ubicacion);

	/**
	 * Lista de productos filtrados por un rango de precios.
	 * @param precioInicial el precio limite inferior.
	 * @param precioFinal el precio limite superior.
	 * @return Lista de productos filtrados por precio.
	 */
	List<Producto> buscarPorPrecio(double precioInicial, double precioFinal);

	/**
	 * Lista de productos filtrados por un rango de fechas para
	 * la toma del paquete.
	 * @param fechaInicial la fecha limite inferior.
	 * @param fechaFinal la fecha limite superior.
	 * @return Lista de productos filtrados por fecha en que el pauete se hace efectivo.
	 */
	List<Producto> buscarPorFechaInicio(Date fechaInicial, Date fechaFinal);
	
	/**
	 * Retorna todos los productos o paquetes del sistema.
	 * @return todos los productos o paquetes del sistema.
	 */
	List<Producto> listarTodos();


}
