package grupo6.modulo.moneda.service.impl;

import grupo6.modulo.moneda.dao.view.IMonedaDAO;
import grupo6.modulo.moneda.service.view.IMonedaService;
import grupo6.modulo.payment.dao.enums.TipoMoneda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación de {@link IMonedaService}.
 * 
 * @author Alejo
 *
 */
@Service(value = "MonedaService")
public class MonedaService implements IMonedaService{

	/** DAO de monedas. */
	@Autowired
	private IMonedaDAO monedaDAO;

	/**
	 * 
	 */
	@Override
	public TipoMoneda getTipoMoneda(String userName) {
		return monedaDAO.getTipoMoneda(userName);
	}

	@Override
	public Boolean setTipoMoneda(String userName, TipoMoneda tipoMoneda) {
		return monedaDAO.setTipoMoneda(userName, tipoMoneda);
	}
	
	

	
}
