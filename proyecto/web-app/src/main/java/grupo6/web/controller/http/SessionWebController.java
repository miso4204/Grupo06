package grupo6.web.controller.http;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.web.dto.ResponseDTO;
import grupo6.web.dto.UsuarioDTO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador web para manejar la sesión de los usuarios: login y logout.
 * 
 * @author caespinosam
 *
 */
@Controller
public class SessionWebController {

	/**
	 * Host donde están desplegados los servicios REST. Ver
	 * /WEB-INF/web.properties
	 */
	@Value("${rest.host}")
	private String restHost;

	/**
	 * Verifica las credenciales enviadas mediante el consumo del servicio REST
	 * de login. Si el usuario existe, se deja en sesión un dto que representa
	 * al usuario bajo el atributo 'usuarioSesion'.
	 * 
	 * @param request
	 *            http request
	 * @param response
	 *            http response
	 * @return plantilla index de acuerdo al perfil del usuario, plantilla login
	 *         si no es posible hacer el login.
	 * @throws Exception
	 *             cualquier error.
	 */
	@RequestMapping("/login")
	protected ModelAndView executeLogin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
		ModelAndView model = new ModelAndView("/nonsecure/login");
		try {
			HttpPost httpPost = new HttpPost(restHost
					+ "services/usuario/login");
			httpPost.addHeader("accept", "application/json");
			httpPost.addHeader("Content-type", "application/json");

			Map<String, String> params = new HashMap<String, String>();
			params.put("usuario", request.getParameter("username"));
			params.put("password", request.getParameter("password"));
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(params);
			httpPost.setEntity(new StringEntity(json));

			HttpResponse httResponse = httpClient.execute(httpPost);

			if (httResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = httResponse.getEntity();
				String jsonString = EntityUtils.toString(entity, "UTF-8");
				if (StringUtils.isNotBlank(jsonString)) {
					UsuarioDTO usuarioDTO = new ObjectMapper().readValue(
							jsonString, UsuarioDTO.class);
					HttpSession session = request.getSession(true);
					session.setAttribute("usuarioSesion", usuarioDTO);
					
					
					if ("ADMIN".equals(usuarioDTO.getRol())) {
						model = new ModelAndView("/admin/indexAdmin");
					} else if ("CLIENT".equals(usuarioDTO.getRol())) {
						model = new ModelAndView("/client/indexUser");
					} else if ("PROVIDER".equals(usuarioDTO.getRol())) {
						model = new ModelAndView("/provider/indexProvider");
					} else {
						model.addObject("mensajeError", "Invalid role");
					}
				} else {
					model.addObject("mensajeError", "Invalid credentials");
				}
			} else {
				model.addObject("mensajeError", "Error: "
						+ httResponse.getStatusLine().getReasonPhrase());
			}

		} catch (Exception e) {
			model.addObject("mensajeError", e.getMessage());
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		return model;
	}

	/**
	 * Cierra la sesión de un usuario.
	 * 
	 * @param request
	 *            http request
	 * @param response
	 *            http response
	 * @return la plantilla a mostrar después de cerrar sesión.
	 * @throws Exception
	 *             cuaqluier error.
	 */
	@RequestMapping("/logout")
	protected ModelAndView executeLogout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("/nonsecure/login");
		HttpSession session = request.getSession();
		session.invalidate();
		return model;
	}
	
	
	/**
	 * Cierra la sesión de un usuario.
	 * 
	 * @param request
	 *            http request
	 * @param response
	 *            http response
	 * @return la plantilla a mostrar después de cerrar sesión.
	 * @throws Exception
	 *             cuaqluier error.
	 */
	@RequestMapping("/update_profile")
	protected ModelAndView executeUpdateProfile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpSession session = request.getSession();
		UsuarioDTO usuarioDTO = (UsuarioDTO) session
				.getAttribute("usuarioSesion");
		ModelAndView model = new ModelAndView("/nonsecure/login");
		if (usuarioDTO != null) {
			if ("CLIENT".equals(usuarioDTO.getRol())) {
				model = new ModelAndView("/client/user_profile");
			} else if ("PROVIDER".equals(usuarioDTO.getRol())) {
				model = new ModelAndView("/provider/provider_profile");
			}
			try {
				HttpPost httpPost = new HttpPost(restHost
						+ "services/usuario/actualizar");
				httpPost.addHeader("accept", "application/json");
				httpPost.addHeader("Content-type", "application/json");

				Map<String, String> params = new HashMap<String, String>();
				params.put("id", String.valueOf(usuarioDTO.getId()));
				params.put("nombre", request.getParameter("nombre"));
				params.put("direccion", request.getParameter("direccion"));
				params.put("telefono", request.getParameter("telefono"));
				params.put("email", request.getParameter("email"));
				params.put("website", request.getParameter("website"));
				params.put("tipoMoneda", request.getParameter("tipoMoneda"));
				ObjectWriter ow = new ObjectMapper().writer()
						.withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(params);
				httpPost.setEntity(new StringEntity(json));

				HttpResponse httResponse = httpClient.execute(httpPost);

				if (httResponse.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = httResponse.getEntity();
					String jsonString = EntityUtils.toString(entity, "UTF-8");
					ResponseDTO responseDTO = new ObjectMapper().readValue(
							jsonString, ResponseDTO.class);
					if (responseDTO.getCodigoRespuesta().equalsIgnoreCase("OK")) {
						usuarioDTO.setNombre(request.getParameter("nombre"));
						usuarioDTO.setDireccion(request
								.getParameter("direccion"));
						usuarioDTO
								.setTelefono(request.getParameter("telefono"));
						usuarioDTO.setEmail(request.getParameter("email"));
						usuarioDTO.setWebsite(request.getParameter("website"));
						usuarioDTO.setTipoMoneda(TipoMoneda.valueOf(request.getParameter("tipoMoneda")));

						session.setAttribute("usuarioSesion", usuarioDTO);
						
						model.addObject("mensaje",
								"Your profile has been updated!");						
					} else {
						model.addObject("mensaje",
								"Error: " + responseDTO.getMensaje());
					}
				} else {
					model.addObject("mensaje", "Error: "
							+ httResponse.getStatusLine().getReasonPhrase());
				}

			} catch (Exception e) {
				model.addObject("mensaje", e.getMessage());
				e.printStackTrace();
			} finally {
				httpClient.getConnectionManager().shutdown();
			}
		}
		
		return model;

	}
}
