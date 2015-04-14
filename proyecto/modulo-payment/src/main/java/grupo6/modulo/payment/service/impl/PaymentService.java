package grupo6.modulo.payment.service.impl;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.payment.service.view.IPaymentService;
import grupo6.modulo.payment.strategy.IPaymentStrategy;
import grupo6.persistencia.entidades.FacturaCompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "Payment")
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
		return paymentPSEStrategy.pagar(userName,tipoMoneda);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.payment.service.view.IPaymentService#pagoCreditCard(java.lang.String)
	 */
	@Override
	@Transactional
	public FacturaCompra pagoCreditCard(String userName,TipoMoneda tipoMoneda) {
		return paymentCreditCardStrategy.pagar(userName,tipoMoneda);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.payment.service.view.IPaymentService#cashOnDelivery(java.lang.String)
	 */
	@Override
	@Transactional
	public FacturaCompra cashOnDelivery(String userName,TipoMoneda tipoMoneda) {
		return paymentCashOnDeliveryStrategy.pagar(userName,tipoMoneda);
	}



}
