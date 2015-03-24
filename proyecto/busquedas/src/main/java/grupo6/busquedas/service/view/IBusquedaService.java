package grupo6.busquedas.service.view;

import grupo6.persistencia.entidades.Oferta;
import grupo6.persistencia.entidades.enums.TipoOferta;

import java.util.List;

/**
 * Servicios a exponer por el modulo de busquedas.
 * 
 * @author caespinosam
 *
 */
public interface IBusquedaService {
	
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
