package grupo6.web.controller.rest;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador REST de prueba.
 * @author caespinosam
 *
 */
@Controller 
@RequestMapping("/services/test") 
public class TestRestController {
	
	/**
	 * Servicio REST de prueba que devuelve los la fecha atual.
	 * 
	 * @returnla fecha actual.
	 */
	@RequestMapping(value = "/fecha_actual", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Date getFechaActual() {
		
		return new Date(); 
	}

}
