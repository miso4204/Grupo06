package grupo6.modulo.payment.service.impl;

import java.util.List;

import grupo6.modulo.payment.dao.impl.PaymentDAO;
import grupo6.modulo.payment.service.view.IPaymentService;
import grupo6.persistencia.entidades.FacturaCompra;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "Payment")
public class PaymentService implements IPaymentService {

	@Autowired
	private PaymentDAO paymentDAO;// Inyeccion de dependencias
	
	
	
	
	@Override
	public FacturaCompra pagoPSE(Usuario userCompra, List<Producto> productos) {
		return paymentDAO.pagoPSE(userCompra, productos);
	}

	@Override
	public FacturaCompra pagoCreditCard(Usuario userCompra, List<Producto> productos) {
		return paymentDAO.pagoCreditCard(userCompra, productos);
	}

	@Override
	public FacturaCompra cashOnDelivery(Usuario userCompra, List<Producto> productos) {
		return paymentDAO.cashOnDelivery(userCompra, productos);
	}



}
