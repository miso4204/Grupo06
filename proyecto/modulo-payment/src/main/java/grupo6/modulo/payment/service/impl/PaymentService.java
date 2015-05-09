package grupo6.modulo.payment.service.impl;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.payment.service.view.IPaymentService;
import grupo6.modulo.payment.strategy.IPaymentStrategy;
import grupo6.persistencia.entidades.FacturaCompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de pagos sincrono.
 * @author caespinosam
 *
 */
@Profile("pago_sincrono")
@Service(value = "paymentService")
public class PaymentService implements IPaymentService {

	/** Las estregias de pago. */
	@Autowired
	private IPaymentStrategy paymentCashOnDeliveryStrategy;
	@Autowired
	private IPaymentStrategy paymentCreditCardStrategy;
	@Autowired
	private IPaymentStrategy paymentPSEStrategy;
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.payment.service.view.IPaymentService#pagoPSE(java.lang.String)
	 */
	@Override
	@Transactional
	public FacturaCompra pagoPSE(String userName,TipoMoneda tipoMoneda) {
		System.out.println("Pagando PSE sincrono: " + userName);
		return paymentPSEStrategy.pagar(userName,tipoMoneda);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.payment.service.view.IPaymentService#pagoCreditCard(java.lang.String)
	 */
	@Override
	@Transactional
	public FacturaCompra pagoCreditCard(String userName,TipoMoneda tipoMoneda) {
		System.out.println("Pagando TC sincrono: " + userName);
		return paymentCreditCardStrategy.pagar(userName,tipoMoneda);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.payment.service.view.IPaymentService#cashOnDelivery(java.lang.String)
	 */
	@Override
	@Transactional
	public FacturaCompra cashOnDelivery(String userName,TipoMoneda tipoMoneda) {
		System.out.println("Pagando CASH sincrono: " + userName);
		return paymentCashOnDeliveryStrategy.pagar(userName,tipoMoneda);
	}



}
