package grupo6.web.controller.rest;

import grupo6.modulo.user.service.impl.IUsuarioService;
import grupo6.persistencia.entidades.Usuario;
import grupo6.web.dto.LoginDTO;
import grupo6.web.dto.UsuarioDTO;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador REST de los servicios del usuario.
 * @author jhon
 *
 */
@Controller 
@RequestMapping("/usuario")
public class UsuarioRestController {

	@Autowired 
	private IUsuarioService usuarioService;//Inyeccion servicios	
	
	public static final String ROL_SESSION = "rol";
	public static final String USER_NAME_SESSION = "userName";
	public static final String USER_ID_SESSION = "userId";
	
	/**
	 * Servicio REST que permite identificar un usuario en el sistema 
	 * y deja sus datos principales en session.
	 * @param nombre de usuario y password
	 * 
	 * @return el true si es valido y false si no o si ocurre un error
	 */
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UsuarioDTO login(@RequestBody LoginDTO userLogin) {
		
		UsuarioDTO usuarioDTO = null;
		try {			
			Usuario  user = usuarioService.ingresar(userLogin.getUsuario(), userLogin.getPassword());						
			if (user != null) {
				usuarioDTO = new UsuarioDTO();
				usuarioDTO.setId(user.getId());
				usuarioDTO.setDireccion(user.getDireccion());
				usuarioDTO.setNombre(user.getNombre());
				usuarioDTO.setPassword(user.getPassword());
				usuarioDTO.setRol(user.getRol());
				usuarioDTO.setTelefono(user.getTelefono());
				usuarioDTO.setUsuario(user.getUsuario());
			}
			
		} catch (Exception e) {			
			e.printStackTrace();			
		}
		
		return usuarioDTO;
	}
	
	
	
	
	/**
	 * Servicio REST que crea un usuario.
	 * @param Usuario a crear. Creado a partir de un JSON.
	 * 
	 * @return el id del nuevo usuario.
	 */
	@RequestMapping(value = "/crear", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody long crearUsuario(@RequestBody Usuario user) {
		
		return usuarioService.crearUsuario(user);
	}
	
	
	/**
	 * Servicio REST que acvtualiza la infor de un usuario.
	 * @param Usuario a actualizar. actualizado a partir de un JSON.
	 * 
	 * @return true si actualiza exitosamente y false si no
	 */
	@RequestMapping(value = "/actualizar", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean actualizarUsuario(@RequestBody Usuario user) {
		
		return usuarioService.actualizarUsuario(user);
	}
	
	
	/**
	 * Servicio REST que apermite cambiar la contrasennia de un usuario
	 * @param Pasword anterior a actualizar. actualizado a partir de un JSON.
	 * 
	 * @return true si actualiza exitosamente y false si no
	 */
	@RequestMapping(value = "/change_pass", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean cambiarContrasennia(@RequestBody String passActual, @RequestBody String passNuevo, 
			@RequestBody String passNuevoValidate) {
		
		@SuppressWarnings("unused")
		String mensajeError = ""; // MOSTRAR EN UN MODAL
		
		try {
			//String userName = (String) httpSession.getAttribute(USER_NAME_SESSION);
			// TODO recibir en peticion
			String userName = null;
			return usuarioService.cambiarPassword(userName, passActual, passNuevo, passNuevoValidate);
		} catch (Exception e) {
			mensajeError = e.getMessage();
			return false;
		}
	}
}
