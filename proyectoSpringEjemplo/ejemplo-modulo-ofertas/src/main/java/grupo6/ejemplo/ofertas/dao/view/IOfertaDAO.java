package grupo6.ejemplo.ofertas.dao.view;

import grupo6.ejemplo.persistencia.entidades.Oferta;

/**
 * Metodos de acceso a datos.
 * @author caespinosam
 *
 */
public interface IOfertaDAO {

	
	/**
	 * Crea una entidad Oferta.
	 * @param oferta el objeto a persistir.
	 * @return la llave primaria asignada.
	 */
	Long crear(Oferta oferta);
	
	/**
	 * Busca una entidad por llave primaria.
	 * @param llavePrimaria la llave a filtrar.
	 * @return la entidad que corresponde a la llave primaria, null si no existe.
	 */
	Oferta obtener(Long llavePrimaria);
	
	
}
