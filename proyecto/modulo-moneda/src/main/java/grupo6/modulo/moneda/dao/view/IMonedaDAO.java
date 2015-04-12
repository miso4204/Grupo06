package grupo6.modulo.moneda.dao.view;

import grupo6.modulo.payment.dao.enums.TipoMoneda;

/**
 * Metodo de acceso a los datos del tipo de moneda.
 * 
 * @author Alejo
 *
 */
public interface IMonedaDAO {

	/**
	 * Retorna el tipo de moneda
	 * 
	 * @param tipoMoneda.
	 * @return {@link TipoMoneda}
	 */
	TipoMoneda getTipoMoneda(String userName);

	/**
	 * Cambia el tipo de moneda.
	 * 
	 * @param userName,tipoMoneda
	 * @return true si se pudo cambiar
	 */
	Boolean setTipoMoneda(String userName,TipoMoneda tipoMoneda);

}
