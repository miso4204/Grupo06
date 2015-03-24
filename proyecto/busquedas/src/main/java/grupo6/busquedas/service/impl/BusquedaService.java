package grupo6.busquedas.service.impl;

import grupo6.busquedas.dao.view.IBusquedaDAO;
import grupo6.busquedas.service.view.IBusquedaService;
import grupo6.persistencia.entidades.Oferta;
import grupo6.persistencia.entidades.enums.TipoOferta;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * Implementacion de {@link IBusquedaService}.
 * @author caespinosam
 *
 */
@Default
@Stateless
@LocalBean
public class BusquedaService implements IBusquedaService {

	/** DAO de busquedas.*/
	 @Inject
     private IBusquedaDAO busquedaDao;
	
	 /**
	  * (non-Javadoc)
	  * @see grupo6.busquedas.service.view.IBusquedaService#buscarOfertasPorCiudad(java.lang.String)
	  */
	public List<Oferta> buscarOfertasPorCiudad(String ciudad) {
		return busquedaDao.buscarOfertasPorCiudad(ciudad);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.busquedas.service.view.IBusquedaService#buscarOfertasPorTipo(grupo6.persistencia.entidades.enums.TipoOferta)
	 */
	public List<Oferta> buscarOfertasPorTipo(TipoOferta tipoOferta) {
		return busquedaDao.buscarOfertasPorTipo(tipoOferta);
	}

}
