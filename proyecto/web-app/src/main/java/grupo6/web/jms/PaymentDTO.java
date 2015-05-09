package grupo6.web.jms;

import java.io.Serializable;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.payment.dao.enums.TiposPago;

/**
 * Representa un pago que es enviado a la cola jms.
 * 
 * @author caespinosam
 *
 */
public class PaymentDTO implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2922615969219452954L;
	/**
	 * EL username del cliente quien paga.
	 */
	private String userName;
	/**
	 * Tipo de moneda.
	 */
	private TipoMoneda tipoMoneda;
	/**
	 * Tipo de pago: PSE, TC, CASH.
	 */
	private TiposPago tipoPago;
	

	
	public PaymentDTO(String userName, TipoMoneda tipoMoneda, TiposPago tipoPago) {
		this.userName = userName;
		this.tipoMoneda = tipoMoneda;
		this.tipoPago = tipoPago;
	}

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

	public TiposPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TiposPago tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getUserName() + "-" + getTipoMoneda() + "-" + getTipoPago();
	}

}
