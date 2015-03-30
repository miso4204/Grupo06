package grupo6.ejemplo.busquedas.dao.view;

import grupo6.ejemplo.persistencia.entidades.Oferta;

import java.util.List;

/**
 * Metodos de acceso a datos.
 * 
 * @author caespinosam
 * 
 */
public interface IBusquedasDAO {

	/**
	 * Busca ofertas por ciudad.
	 * 
	 * @param ciudad
	 *            la ciudad a filtrar.
	 * @return la lista de ofertas en esa ciudad.
	 */
	List<Oferta> ofertasPorCiudad(String ciudad);
	
	/**
	 * Retorna todas las ofertas.
	 * @return la lista de todas las ofertas.
	 */
	List<Oferta> ofertasTodas();

}
