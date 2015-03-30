package grupo6.ejemplo.busquedas.dao.impl;

import grupo6.ejemplo.busquedas.dao.view.IBusquedasDAO;
import grupo6.ejemplo.persistencia.dao.BaseDAO;
import grupo6.ejemplo.persistencia.entidades.Oferta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementaci�n de {@link IBusquedasDAO}.
 */
@Repository(value = "busquedasDAO") // Indica que es un bean Spring para acceso a datos y
			// que se crear� dicho bean en el contexto Spring  bajo el nombre 'ofertaDAO'

public class BusquedasDAO extends BaseDAO implements IBusquedasDAO {

	/**
	 * (non-Javadoc)
	 * @see grupo6.ejemplo.busquedas.dao.view.IBusquedasDAO#ofertasPorCiudad(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true) // transacci�n de solo lectura
	public List<Oferta> ofertasPorCiudad(String ciudad) {
		
		Criteria criteria = getCurrentSession().createCriteria(Oferta.class);		
		criteria.add(Restrictions.ilike("ciudad", ciudad));
		
		return (List<Oferta>)criteria.list();
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.ejemplo.busquedas.dao.view.IBusquedasDAO#ofertasTodas()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true) // transacci�n de solo lectura
	public List<Oferta> ofertasTodas() {
		return (List<Oferta>)getCurrentSession()
				.createQuery("from Oferta").list();
	}

	
	
	
}
