package grupo6.modulo.product.service.view;

import grupo6.persistencia.entidades.ETipoCalificacionRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;

import java.util.Date;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.summary.Product;

/**
 * Servcios expuestos por el módulo de producto.
 */
public interface IProductoService {
	
	/**
	 * Crear un producto.
	 * @param producto el producto a crear.
	 * @return el id del producto creado.
	 */
	Long crearProducto(Producto producto);

	/**
	 * Buscar un producto por llame primaria.
	 * @param id la llave a buscar.
	 * @return el producto, null en caso que no exista.
	 */
	Producto buscarProductoPorId(Long id);

	/**
	 * Lista de productos filtrados por ubicacion.
	 * @param ubicacion la ubicacion a filtrar.
	 * @return Lista de productos filtrados por ubicacion.
	 */
	List<Product> buscarProductosPorUbicacion(String ubicacion);

	/**
	 * Lista de productos filtrados por un rango de precios.
	 * @param precioInicial el precio limite inferior.
	 * @param precioFinal el precio limite superior.
	 * @return Lista de productos filtrados por precio.
	 */
	List<Product> buscarProductosPorPrecio(double precioInicial, double precioFinal);

	/**
	 * Lista de productos filtrados por un rango de fechas para
	 * la toma del paquete.
	 * @param fechaInicial la fecha limite inferior.
	 * @param fechaFinal la fecha limite superior.
	 * @return Lista de productos filtrados por fecha en que el pauete se hace efectivo.
	 */
	List<Product> buscarProductosPorFechaInicio(Date fechaInicial, Date fechaFinal);
	
	/**
	 * Califica el servicio de un producto.
	 * 
	 * @param servicioId el id del servicio a calificar.
	 * @param calificacion la calificacion del servicio.
	 */
	void calificarProducto(Long servicioId, ETipoCalificacionRating calificacion);
	
	
	/**
	 * Lista de ratings filtrados por el producto asociado.
	 * @param productoId id del producto a filtrar a filtrar.
	 * @return Lista de ratings del producto.
	 */
	List<RatingProducto> buscarRatingPorProductoId(Long productoId);


}
