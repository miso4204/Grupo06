package grupo6.web.controller.rest;

import grupo6.modulo.user.service.impl.IUsuarioService;
import grupo6.persistencia.entidades.Usuario;
import grupo6.web.dto.ChangePassDTO;
import grupo6.web.dto.LoginDTO;
import grupo6.web.dto.ResponseDTO;
import grupo6.web.dto.UsuarioDTO;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UsuarioRestController extends BaseRestController {

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

		Usuario user = usuarioService.ingresar(userLogin.getUsuario(),
				userLogin.getPassword());
		if (user != null) {
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setId(user.getId());
			usuarioDTO.setDireccion(user.getDireccion());
			usuarioDTO.setNombre(user.getNombre());
			usuarioDTO.setPassword(user.getPassword());
			usuarioDTO.setRol(user.getRol());
			usuarioDTO.setTelefono(user.getTelefono());
			usuarioDTO.setUsuario(user.getUsuario());
			usuarioDTO.setEmail(user.getEmail());
			usuarioDTO.setWebsite(user.getWebsite());
			usuarioDTO.setTipoMoneda(user.getTipoMoneda());
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
	public @ResponseBody ResponseEntity<ResponseDTO> crearUsuario(@RequestBody Usuario user) {		
		
		long id = usuarioService.crearUsuario(user);
		return devolverRespuestaExitosa("Usuario creado exitosamente", id);	
		
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
	public @ResponseBody ResponseEntity<ResponseDTO> actualizarUsuario(@RequestBody UsuarioDTO userDTO) {
		
		   Usuario user =  usuarioService.buscarPorId(userDTO.getId());
		   if (user == null) {
			   throw new IllegalArgumentException("Usuario no existe");
		   }
		   user.setNombre(userDTO.getNombre());
		   user.setDireccion(userDTO.getDireccion());
		   user.setTelefono(userDTO.getTelefono());
		   user.setEmail(userDTO.getEmail());
		   user.setWebsite(userDTO.getWebsite());
		   user.setTipoMoneda(userDTO.getTipoMoneda());
		   boolean b =  usuarioService.actualizarUsuario(user);	
		   if (b) {
			   return devolverRespuestaExitosa("Usuario actualizado", b);
		   }
		   else {
			   throw new IllegalArgumentException("Usuario no actualizado");
		   }
		  
	}
	
	
	/**
	 * Servicio REST que apermite cambiar la contrasennia de un usuario
	 * @param Pasword anterior a actualizar. actualizado a partir de un JSON.
	 * 
	 * @return true si actualiza exitosamente y false si no
	 * @throws Exception 
	 */
	@RequestMapping(value = "/change_pass", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDTO> cambiarContrasennia(@RequestBody ChangePassDTO changePass) throws Exception {
		
		boolean b = usuarioService.cambiarPassword(changePass.getUserName(),
					changePass.getPassActual(), changePass.getPassNuevo(), changePass.getPassNuevoValidate());
		return devolverRespuestaExitosa("Clave cambiada exitosamente", b);	
	}
}
