package grupo6.web.controller.rest;

import javax.servlet.http.HttpSession;

import grupo6.modulo.payment.service.view.IPaymentService;
import grupo6.modulo.user.service.impl.IUsuarioService;
import grupo6.persistencia.entidades.FacturaCompra;
import grupo6.persistencia.entidades.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Controlador REST de los servicios del payment.
 * @author jhon
 *
 */
@Controller 
@RequestMapping("/payment")
public class PaymentRestController {

	@Autowired 
	private IPaymentService paymentService;//Inyeccion servicios

	@Autowired 
	private IUsuarioService usuarioService;//Inyeccion servicios del usuario
	
	@Autowired
	private HttpSession httpSession; // Usado para manejar la seesion del usuario
	
	/**
	 * Servicio REST que permite realizar el pago por PSE
	 * @param userCompra usuario que esta realizando la compra
	 * 
	 * @return el true si es valido y false si no o si ocurre un error
	 */
	@RequestMapping(value = "/pay_pse", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FacturaCompra pagoPSE() {
		
		long idUsuarioSession = (long) httpSession.getAttribute(UsuarioRestController.USER_ID_SESSION);
		Usuario userCompra = usuarioService.buscarPorId(idUsuarioSession);
		return paymentService.pagoPSE(userCompra);
	}
	
	
	/**
	 * Servicio REST que permite realizar el pago por tarjeta de credito
	 * @param userCompra usuario que esta realizando la compra
	 * 
	 * @return el true si es valido y false si no o si ocurre un error
	 */
	@RequestMapping(value = "/pay_credit", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FacturaCompra pagoCreditCard() {
		
		long idUsuarioSession = (long) httpSession.getAttribute(UsuarioRestController.USER_ID_SESSION);
		Usuario userCompra = usuarioService.buscarPorId(idUsuarioSession);
		return paymentService.pagoCreditCard(userCompra);
	}
	
	/**
	 * Servicio REST que permite realizar el pago por tarjeta de credito
	 * @param userCompra usuario que esta realizando la compra
	 * 
	 * @return el true si es valido y false si no o si ocurre un error
	 */
	@RequestMapping(value = "/pay_cash", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FacturaCompra pagoManual() {
		
		long idUsuarioSession = (long) httpSession.getAttribute(UsuarioRestController.USER_ID_SESSION);
		Usuario userCompra = usuarioService.buscarPorId(idUsuarioSession);
		return paymentService.cashOnDelivery(userCompra);
	}
}
