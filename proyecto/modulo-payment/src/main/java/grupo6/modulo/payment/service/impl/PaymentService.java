package grupo6.modulo.payment.service.impl;

import grupo6.modulo.payment.dao.view.IPaymentDAO;
import grupo6.modulo.payment.service.view.IPaymentService;
import grupo6.persistencia.entidades.FacturaCompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "Payment")
public class PaymentService implements IPaymentService {

	@Autowired
	private IPaymentDAO paymentDAO;// Inyeccion de dependencias
	
	
	@Override
	@Transactional
	public FacturaCompra pagoPSE(String userName) {
		return paymentDAO.pagoPSE(userName);
	}

	@Override
	@Transactional
	public FacturaCompra pagoCreditCard(String userName) {
		return paymentDAO.pagoCreditCard(userName);
	}

	@Override
	@Transactional
	public FacturaCompra cashOnDelivery(String userName) {
		return paymentDAO.cashOnDelivery(userName);
	}



}
