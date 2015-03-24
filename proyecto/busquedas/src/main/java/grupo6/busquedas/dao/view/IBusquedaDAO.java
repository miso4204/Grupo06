package grupo6.busquedas.dao.view;

import grupo6.persistencia.entidades.Oferta;
import grupo6.persistencia.entidades.enums.TipoOferta;

import java.util.List;
/**
 * Metodos de acceso a datos del modulo de busqueda.
 * @author caespinosam
 *
 */
public interface IBusquedaDAO {

	
	/**
	 * Busca ofertas por ciudad.
	 * @param ciudad la ciudad a filtrar.
	 * @return la lista de ofertas de esa ciudad.
	 */
	List<Oferta> buscarOfertasPorCiudad(String ciudad);
	
	/**
	 * Busca ofertas por tipo de oferta.
	 * @param tipoOferta el tipo de oferta a filtrar.
	 * @return la lista de todas las ofertas de ese tipo.
	 */
	List<Oferta> buscarOfertasPorTipo(TipoOferta tipoOferta);
}
