package grupo6.web.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.payment.dao.enums.TiposPago;
import grupo6.modulo.payment.service.view.IPaymentService;
import grupo6.persistencia.entidades.FacturaCompra;

/**
 * Servicio de pagos que delega el proceso
 * a un proceso asincrono.
 * @author caespinosam
 *
 */
@Profile("pago_asincrono")
@Service(value = "paymentService")
public class PaymentServiceJms implements IPaymentService {
	
	/**
	 * Sender de los mensajes a la cola.
	 */
	@Autowired
	private JmsMessageSender jmsMessageSender;

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.payment.service.view.IPaymentService#pagoPSE(java.lang.String, grupo6.modulo.payment.dao.enums.TipoMoneda)
	 */
	@Override
	public FacturaCompra pagoPSE(String userName, TipoMoneda tipoMoneda) {
		jmsMessageSender.send(new PaymentDTO(userName, tipoMoneda, TiposPago.PSE));
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.payment.service.view.IPaymentService#pagoCreditCard(java.lang.String, grupo6.modulo.payment.dao.enums.TipoMoneda)
	 */
	@Override
	public FacturaCompra pagoCreditCard(String userName, TipoMoneda tipoMoneda) {
		jmsMessageSender.send(new PaymentDTO(userName, tipoMoneda, TiposPago.CREDIT_CARD));
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.payment.service.view.IPaymentService#cashOnDelivery(java.lang.String, grupo6.modulo.payment.dao.enums.TipoMoneda)
	 */
	@Override
	public FacturaCompra cashOnDelivery(String userName, TipoMoneda tipoMoneda) {
		jmsMessageSender.send(new PaymentDTO(userName, tipoMoneda, TiposPago.CASH_ON_DELIVERY));
		return null;
	}

}
