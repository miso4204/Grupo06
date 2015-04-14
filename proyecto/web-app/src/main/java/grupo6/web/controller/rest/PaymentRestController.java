package grupo6.web.controller.rest;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.payment.service.view.IPaymentService;
import grupo6.persistencia.entidades.FacturaCompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
public class PaymentRestController extends BaseRestController {

	@Autowired 
	private IPaymentService paymentService;//Inyeccion servicios
		
	/**
	 * Servicio REST que permite realizar el pago por PSE
	 * @param userName usuario que esta realizando la compra
	 * 
	 * @return el true si es valido y false si no o si ocurre un error
	 */
	@RequestMapping(value = "/pay_pse/{userName}", method = RequestMethod.POST, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FacturaCompra pagoPSE(@PathVariable("userName") String userName,@RequestHeader(value="tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		return paymentService.pagoPSE(userName,tipoMoneda);
	}
	
	
	/**
	 * Servicio REST que permite realizar el pago por tarjeta de credito
	 * @param userName usuario que esta realizando la compra
	 * 
	 * @return el true si es valido y false si no o si ocurre un error
	 */
	@RequestMapping(value = "/pay_credit/{userName}", method = RequestMethod.POST, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FacturaCompra pagoCreditCard(@PathVariable("userName") String userName,@RequestHeader(value="tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		return paymentService.pagoCreditCard(userName,tipoMoneda);
	}
	
	/**
	 * Servicio REST que permite realizar el pago por tarjeta de credito
	 * @param userName usuario que esta realizando la compra
	 * 
	 * @return el true si es valido y false si no o si ocurre un error
	 */
	@RequestMapping(value = "/pay_cash/{userName}", method = RequestMethod.POST, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FacturaCompra pagoManual(@PathVariable("userName") String userName,@RequestHeader(value="tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		return paymentService.cashOnDelivery(userName,tipoMoneda);
	}
}
