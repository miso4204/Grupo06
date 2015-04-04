package grupo6.web.dto;

/**
 * Clase creada para traer los parametros pro json de usuario y password
 * @author jhon
 *
 */
public class LoginDTO {
	
	private String usuario;
	private String password;

	/**
	 * Constructor por defecto
	 */
	public LoginDTO() {
		
	}
	
	
	//---------------------------- GUEETERS AND SETTERS  ------------------------------

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
