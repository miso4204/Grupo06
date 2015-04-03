package grupo6.modulo.product.service.view;

import grupo6.persistencia.entidades.ETipoCalificacionRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;

import java.util.Date;
import java.util.List;

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
	List<Producto> buscarProductosPorUbicacion(String ubicacion);

	
	/**
	 * Lista de productos filtrados por un rango de precios.
	 * @param precioInicial el precio limite inferior.
	 * @param precioFinal el precio limite superior.
	 * @return Lista de productos filtrados por precio.
	 */
	List<Producto> buscarProductosPorPrecio(double precioInicial, double precioFinal);

	/**
	 * Lista de productos filtrados por un rango de fechas para
	 * la toma del paquete.
	 * @param fechaInicial la fecha limite inferior.
	 * @param fechaFinal la fecha limite superior.
	 * @return Lista de productos filtrados por fecha en que el pauete se hace efectivo.
	 */
	List<Producto> buscarProductosPorFechaInicio(Date fechaInicial, Date fechaFinal);
	
	/**
	 * Califica el servicio de un producto.
	 * 
	 * @param clienteId el id del cliente quien califica.
	 * @param servicioId el id del servicio a calificar.
	 * @param calificacion la calificacion del servicio.
	 */
	void calificarProducto(Long clienteId, Long servicioId, ETipoCalificacionRating calificacion);
	
	
	/**
	 * Lista de ratings filtrados por el producto asociado.
	 * @param productoId id del producto a filtrar a filtrar.
	 * @return Lista de ratings del producto.
	 */
	List<RatingProducto> buscarRatingPorProductoId(Long productoId);
	
	/**
	 * Retorna la calificacion promedio de un servicio o rating asociado a un
	 * producto.
	 * @param servicioId el servicio o rating.
	 * @return la calificacion promedio dada por los usuarios a dicho servicio.
	 */
	double obtenerCalificacionDeServicio(Long servicioId);
	
	/**
	 * Retorna la cantidad de usuarios que han calificado un servicio de un
	 * determinado producto.
	 * @param servicioId el servicio o rating.
	 * @return la cantidad de usuarios que han calificado un servicio de un
	 * determinado producto.
	 */
	int obtenerNumeroVotantesDeServicio(Long servicioId);
	

	/**
	 * Retorna todos los productos o paquetes del sistema.
	 * @return todos los productos o paquetes del sistema.
	 */
	List<Producto> listarTodosProductos();


}
