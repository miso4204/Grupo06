package grupo6.web.controller.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador web principal que muestra el template html.
 * 
 */
@Controller
@RequestMapping
public class MainController {
	
	/**
	 * Muestra como index el template web-inf/pages/nonsecure/index.jsp
	 * @return
	 */
	@RequestMapping
	public ModelAndView viewMainPage() {
		ModelAndView modelAndView = new ModelAndView("nonsecure/index");
		return modelAndView;
	}
	
	
}
