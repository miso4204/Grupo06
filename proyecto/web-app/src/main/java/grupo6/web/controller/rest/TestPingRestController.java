package grupo6.web.controller.rest;

import grupo6.web.dto.PingDTO;

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
@RequestMapping("/test") 
public class TestPingRestController extends BaseRestController {
	
	/**
	 * Servicio REST de prueba que devuelve un mensaje ping en JSON.
	 * 
	 * @return mensaje ping.
	 */
	@RequestMapping(value = "/ping", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PingDTO getFechaActual() {
		
		PingDTO ping = new PingDTO();
		ping.setMensaje("Funciona!");
		ping.setHora(new Date().toString());
		return ping; 
	}

}
