package grupo6.modulo.product.service.view;

import grupo6.modulo.product.factory.ETipoBusqueda;
import grupo6.persistencia.entidades.ETipoCalificacionRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;
import grupo6.persistencia.entidades.RatingProductoCalificacion;

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
	
	
	/**
	 * Retorna la calificación dada por un usuario al servicio de un poducto.
	 * @param ratingProductoId id del servicio.
	 * @param clienteId el cliente a filtrar.
	 * @return Lista de calificaciones de un servicio o rating.
	 */
	RatingProductoCalificacion buscarCalificacionDeUsuario(Long ratingProductoId, Long clienteId);


	/**
	 * Metodo generico de busqueda, el cual delega a la fabrica de buscadores 
	 * la ejecucion de la busqueda.
	 * @param tipoBusqueda el tipo de busqueda: por fecha, precio, etc.
	 * @param parametros los parametros a usar en la busqueda: fechas, precios, etc.
	 * @return lista de productos devueltos por el buscador usado.
	 */
	List<Producto> buscarProductos(ETipoBusqueda tipoBusqueda, Object... parametros);
}
