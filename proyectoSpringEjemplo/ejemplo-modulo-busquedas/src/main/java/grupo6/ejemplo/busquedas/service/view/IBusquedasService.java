package grupo6.ejemplo.busquedas.service.view;

import grupo6.ejemplo.persistencia.entidades.Oferta;

import java.util.List;

/**
 * Servcios expuestos por el módulo de Busquedas.
 */
public interface IBusquedasService {

	/**
	 * Busca ofertas por ciudad.
	 * 
	 * @param ciudad
	 *            la ciudad a filtrar.
	 * @return la lista de ofertas en esa ciudad.
	 */
	List<Oferta> buscarOfertasPorCiudad(String ciudad);
	
	/**
	 * Retorna todas las ofertas.
	 * @return la lista de todas las ofertas.
	 */
	List<Oferta> buscarOfertasTodas();

}
