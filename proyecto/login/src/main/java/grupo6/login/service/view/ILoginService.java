package grupo6.login.service.view;

/**
 * Define los servicios de login.
 * 
 * @author caespinosam
 *
 */
public interface ILoginService {

	/**
	 * Metodo basico de login.
	 * 
	 * @param usuario el username.
	 * @param clave la contrase�a del usuario.
	 * @return true si las credenciales son validas, false en caso contrario.
	 */
	boolean login(String usuario, String clave);
}
