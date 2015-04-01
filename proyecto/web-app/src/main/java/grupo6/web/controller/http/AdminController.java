package grupo6.web.controller.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador web que controla la navegación de las secciones del administrador.
 * 
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	/**
	 * Muestra la plantilla para la url /admin/index.html
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView viewMainPage() {
		ModelAndView modelAndView = new ModelAndView("/admin/home_admin");
		return modelAndView;
	}

}
