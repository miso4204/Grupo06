package grupo6.web.jms;

import grupo6.modulo.payment.dao.enums.TiposPago;
import grupo6.modulo.payment.strategy.IPaymentStrategy;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Recibe un mensaje JMS de la cola dessoft-queue. El contenido del mensaje es
 * un objeto.
 * 
 * @author caespinosam
 *
 */
@Profile("pago_asincrono")
@Service
public class JmsMessageReceiver implements MessageListener {
	
	/** Las estregias de pago. */
	@Autowired
	private IPaymentStrategy paymentCashOnDeliveryStrategy;
	@Autowired
	private IPaymentStrategy paymentCreditCardStrategy;
	@Autowired
	private IPaymentStrategy paymentPSEStrategy;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message message) {
		System.out.println("Recibiendo mensaje de cola: " + message);
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				if (objectMessage.getObject() instanceof PaymentDTO) {
					PaymentDTO paymentDto = (PaymentDTO) objectMessage
							.getObject();
					System.out.println("-> Tipo pago: " + paymentDto.getTipoPago());
					if (paymentDto.getTipoPago() == TiposPago.PSE) {
						paymentPSEStrategy.pagar(paymentDto.getUserName(),
								paymentDto.getTipoMoneda());
					}
					else if (paymentDto.getTipoPago() == TiposPago.CASH_ON_DELIVERY) {
						paymentCashOnDeliveryStrategy.pagar(paymentDto.getUserName(),
								paymentDto.getTipoMoneda());
					}
					else if (paymentDto.getTipoPago() == TiposPago.CREDIT_CARD) {
						paymentCreditCardStrategy.pagar(paymentDto.getUserName(),
								paymentDto.getTipoMoneda());
					}
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}

	}
}
