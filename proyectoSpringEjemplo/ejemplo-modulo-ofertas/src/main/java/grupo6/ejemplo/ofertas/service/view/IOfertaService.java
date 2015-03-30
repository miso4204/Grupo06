package grupo6.ejemplo.ofertas.service.view;

import grupo6.ejemplo.persistencia.entidades.Oferta;

/**
 * Servcios expuestos por el módulo de Ofertas.
 */
public interface IOfertaService {

	/**
	 * Crea una Oferta.
	 * @param oferta la oferta a crear.
	 */
	Long crearOferta(Oferta oferta);
	
	/**
	 * Busca una entidad por llave primaria.
	 * @param llavePrimaria la llave a filtrar.
	 * @return la entidad que corresponde a la llave primaria, null si no existe.
	 */
	Oferta obtenerOferta(Long llavePrimaria);
}
