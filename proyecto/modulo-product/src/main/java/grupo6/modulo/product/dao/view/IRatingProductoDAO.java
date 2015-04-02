package grupo6.modulo.product.dao.view;

import grupo6.persistencia.entidades.ETipoRating;
import grupo6.persistencia.entidades.RatingProducto;

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
	Long crear(Long productoId, ETipoRating tipoServicio);

	/**
	 * Buscar un rating por llame primaria.
	 * @param id la llave a buscar.
	 * @return el rating, null en caso que no exista.
	 */
	RatingProducto buscarPorId(Long id);
	
	/**
	 * Actualiza el objeto.
	 * @param rating el objeto a actualizar.
	  */
	void actulizar(RatingProducto rating);

	/**
	 * Lista de ratings filtrados por el producto asociado.
	 * @param productoId id del producto a filtrar a filtrar.
	 * @return Lista de ratings del producto.
	 */
	List<RatingProducto> buscarPorProductoId(Long productoId);

	

}
