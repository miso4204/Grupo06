package grupo6.ejemplo.ofertas.service.impl;

import grupo6.ejemplo.ofertas.dao.view.IOfertaDAO;
import grupo6.ejemplo.ofertas.service.view.IOfertaService;
import grupo6.ejemplo.persistencia.entidades.Oferta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link IOfertaService}.
 */
@Service(value = "ofertaService") // Indica que es un bean Spring de tipo servicio y
		 //  que se creará dicho bean en el contexto Spring  bajo el nombre 'ofertaService'
public class OfertaService implements IOfertaService {
	
	@Autowired // Indica a Spring que asigne de manera automática el bean con
			   // nombre  'ofertaDAO' a este  atributo. Ver clase OfertaDAO.
	private IOfertaDAO ofertaDAO;

	/**
	 * (non-Javadoc)
	 * @see grupo6.ejemplo.ofertas.service.view.IOfertaService#crearOferta(grupo6.ejemplo.persistencia.entidades.Oferta)
	 */
	@Transactional // Indica que el método se ejecuta en un contexto transaccional
	public Long crearOferta(Oferta oferta) {
		return ofertaDAO.crear(oferta);		
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.ejemplo.ofertas.service.view.IOfertaService#obtener(java.lang.Long)
	 */
	@Transactional(readOnly = true) // transacción de solo lectura
	public Oferta obtenerOferta(Long llavePrimaria) {
		return ofertaDAO.obtener(llavePrimaria);
	}

}
