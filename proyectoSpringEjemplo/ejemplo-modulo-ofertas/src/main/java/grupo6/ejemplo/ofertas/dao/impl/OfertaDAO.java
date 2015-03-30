package grupo6.ejemplo.ofertas.dao.impl;

import grupo6.ejemplo.ofertas.dao.view.IOfertaDAO;
import grupo6.ejemplo.persistencia.dao.BaseDAO;
import grupo6.ejemplo.persistencia.entidades.Oferta;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link IOfertaDAO}.
 */
@Repository(value = "ofertaDAO") // Indica que es un bean Spring para acceso a datos y
			// que se creará dicho bean en el contexto Spring  bajo el nombre 'ofertaDAO'
public class OfertaDAO extends BaseDAO implements IOfertaDAO {

	/**
	 * (non-Javadoc)
	 * @see grupo6.ejemplo.ofertas.dao.view.IOfertaDAO#crear(grupo6.ejemplo.persistencia.entidades.Oferta)
	 */
	@Transactional // Indica que el método se ejecuta en un contexto transaccional
	public Long crear(Oferta oferta) {
		return (Long)getCurrentSession().save(oferta);			 
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.ejemplo.ofertas.dao.view.IOfertaDAO#obtener(java.lang.Long)
	 */
	@Transactional(readOnly = true) // transacción de solo lectura
	public Oferta obtener(Long llavePrimaria) {
		return (Oferta) getCurrentSession()
					.get(Oferta.class, llavePrimaria);
	}

}
