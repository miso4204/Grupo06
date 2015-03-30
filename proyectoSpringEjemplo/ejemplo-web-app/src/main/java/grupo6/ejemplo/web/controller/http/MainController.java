package grupo6.ejemplo.web.controller.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador web principal que muestra el template html.
 * 
 */
@Controller
public class MainController {
	
	/**
	 * Muestra como index el template web-inf/pages/index.jsp
	 * @return
	 */
	@RequestMapping(value = "/")
	public ModelAndView viewMainPage() {
		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;
	}

}
