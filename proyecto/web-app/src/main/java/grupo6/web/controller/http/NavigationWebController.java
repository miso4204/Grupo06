package grupo6.web.controller.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador de navegación de páginas. 
 * 
 * @author caespinosam
 *
 */
@Controller
public class NavigationWebController {

	
	/**
	 * 
	 * EL MAPEO DE CADA PETICIÓN *.JSP A UNA PLANTILLA JSP
	 */
	
	
	
	@RequestMapping("/nonsecure/login.jsp")
	protected ModelAndView showNonSecureLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/nonsecure/login");
	}

	@RequestMapping("/nonsecure/index.jsp")
	protected ModelAndView showNonSecureIndex(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/nonsecure/index");
	}

	@RequestMapping("/admin/home_admin.jsp")
	protected ModelAndView showAdminHome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/admin/home_admin");
	}

	@RequestMapping("/admin/IndexAdmin.jsp")
	protected ModelAndView showAdminIndex(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/admin/IndexAdmin");
	}

	@RequestMapping("/client/destinationDetails.jsp")
	protected ModelAndView showClientDestinationDetails(
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		return new ModelAndView("/client/destinationDetails");
	}

	@RequestMapping("/client/home_client.jsp")
	protected ModelAndView showClientHomeClient(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/client/home_client");
	}

	@RequestMapping("/client/indexUser.jsp")
	protected ModelAndView showClientIndex(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/client/indexUser");
	}

	@RequestMapping("/client/payment.jsp")
	protected ModelAndView showClientPayment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/client/payment");
	}

	@RequestMapping("/client/searchResult.jsp")
	protected ModelAndView showClientSearchResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/client/searchResult");
	}
	
	@RequestMapping("/provider/home_provider.jsp")
	protected ModelAndView showProviderHome(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/provider/home_provider");
	}
	
	@RequestMapping("/client/successPayment.jsp")
	protected ModelAndView showSuccessPayment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/client/successPayment");
	}
	
	@RequestMapping("/client/user_profile.jsp")
	protected ModelAndView showUserProfile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("/client/user_profile");
	}
}
