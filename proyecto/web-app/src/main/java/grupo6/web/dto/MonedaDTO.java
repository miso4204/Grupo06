package grupo6.web.dto;

import grupo6.modulo.payment.dao.enums.TipoMoneda;

/**
 * DTO que reprenseta el JSON de entrada al cambiar de moneda 
 * @author Alejo
 *
 */
public class MonedaDTO {

	private String userName;
	private TipoMoneda tipoMoneda;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public TipoMoneda getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(TipoMoneda tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

}
