package grupo6.web.controller.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador web que controla la navegación de las secciones del cliente.
 * 
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	/**
	 * Muestra la plantilla para la url /cliente/index.html
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView viewMainPage() {
		ModelAndView modelAndView = new ModelAndView("client/home_client");
		return modelAndView;
	}

}
