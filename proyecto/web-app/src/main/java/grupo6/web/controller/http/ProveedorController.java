package grupo6.web.controller.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador web que controla la navegación de las secciones del proveedor.
 * 
 */
@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	
	/**
	 * Muestra la plantilla para la url /proveedor/index.html
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView viewMainPage() {
		ModelAndView modelAndView = new ModelAndView("provider/home_provider");
		return modelAndView;
	}

}
