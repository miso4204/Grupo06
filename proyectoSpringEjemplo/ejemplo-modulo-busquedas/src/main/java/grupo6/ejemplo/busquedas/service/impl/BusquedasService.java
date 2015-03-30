package grupo6.ejemplo.busquedas.service.impl;

import grupo6.ejemplo.busquedas.dao.view.IBusquedasDAO;
import grupo6.ejemplo.busquedas.service.view.IBusquedasService;
import grupo6.ejemplo.persistencia.entidades.Oferta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link IOfertaService}.
 */
@Service(value = "busquedasService") // Indica que es un bean Spring de tipo servicio y
		 //  que se creará dicho bean en el contexto Spring  bajo el nombre 'busquedasService'
public class BusquedasService implements IBusquedasService {
	
	@Autowired // Indica a Spring que asigne de manera automática el bean con
	   // nombre  'busquedasDAO' a este  atributo. Ver clase BusquedasDAO.
	private IBusquedasDAO busquedasDAO;


	/**
	 * (non-Javadoc)
	 * @see grupo6.ejemplo.busquedas.service.view.IBusquedasService#buscarOfertasPorCiudad(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true) // transacción de solo lectura
	public List<Oferta> buscarOfertasPorCiudad(String ciudad) {
		return busquedasDAO.ofertasPorCiudad(ciudad);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.ejemplo.busquedas.service.view.IBusquedasService#buscarOfertasTodas()
	 */
	@Override
	@Transactional(readOnly = true) // transacción de solo lectura
	public List<Oferta> buscarOfertasTodas() {
		return busquedasDAO.ofertasTodas();
	}

}
