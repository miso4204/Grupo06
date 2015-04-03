package grupo6.modulo.product.dao.view;

import grupo6.persistencia.entidades.ETipoRating;
import grupo6.persistencia.entidades.RatingProducto;
import grupo6.persistencia.entidades.RatingProductoCalificacion;

import java.util.List;

/**
 * Metodos de acceso a datos.
 * 
 * @author caespinosam
 * 
 */
public interface IRatingProductoDAO {

	/**
	 * Crear un rating de un producto.
	 * @param productoId el producto asociado.
	 * @param tipoServicio tipo de rating.
	 * @return el id del objeto creado.
	 */
	Long crearRating(Long productoId, ETipoRating tipoServicio);
	
	/**
	 * Crea una calificacion a un rating o servicio. 
	 * @param calificacion la calificacion del servicio.
	 * @return id del objeto creado.
	 */
	Long crearCalificacion(RatingProductoCalificacion calificacion);

	/**
	 * Buscar un rating por llame primaria.
	 * @param id la llave a buscar.
	 * @return el rating, null en caso que no exista.
	 */
	RatingProducto buscarPorId(Long id);
	
	
	/**
	 * Lista de ratings filtrados por el producto asociado.
	 * @param productoId id del producto a filtrar a filtrar.
	 * @return Lista de ratings del producto.
	 */
	List<RatingProducto> buscarPorProductoId(Long productoId);
	
	/**
	 * Lista de calificaciones de un determinado servicio calificado.
	 * @param ratingProductoId id del servicio.
	 * @return Lista de calificaciones de un servicio o rating.
	 */
	List<RatingProductoCalificacion> buscarCalificacionesDeServicio(Long ratingProductoId);

	

}
