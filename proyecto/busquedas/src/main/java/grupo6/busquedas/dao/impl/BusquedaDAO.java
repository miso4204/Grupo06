package grupo6.busquedas.dao.impl;

import grupo6.busquedas.dao.view.IBusquedaDAO;
import grupo6.persistencia.entidades.Oferta;
import grupo6.persistencia.entidades.enums.TipoOferta;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Implementacion de {@link IBusquedaDAO}.
 * @author caespinosam
 *
 */
@Default
@Stateless
@LocalBean
public class BusquedaDAO implements IBusquedaDAO {
	
	/** Manejador de persistencia JPA.*/
	@PersistenceContext(unitName = "BusquedasPU")
	EntityManager em;
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.busquedas.dao.view.IBusquedaDAO#buscarOfertasPorCiudad(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Oferta> buscarOfertasPorCiudad(String ciudad) {
		Query query = em.createQuery("SELECT o FROM Oferta as o WHERE o.ciudad = :ciudad");
		query.setParameter("ciudad", ciudad);
        return query.getResultList();
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.busquedas.dao.view.IBusquedaDAO#buscarOfertasPorTipo(grupo6.persistencia.entidades.enums.TipoOferta)
	 */
	@SuppressWarnings("unchecked")
	public List<Oferta> buscarOfertasPorTipo(TipoOferta tipoOferta) {
		Query query = em.createQuery("SELECT o FROM Oferta as o WHERE o.tipoOferta = :tipoOferta");
        query.setParameter("tipoOferta", tipoOferta);
        return query.getResultList();
	}

}
