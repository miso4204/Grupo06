package grupo6.web.dto;

/**
 * DTO que representa la petición json para cambiar de password.
 * 
 * @author caespinosam
 *
 */
public class ChangePassDTO {

	private String userName;
	private String passActual;
	private String passNuevo;
	private String passNuevoValidate;

	public String getUserName() {
		return userName;
	}

	public String getPassActual() {
		return passActual;
	}

	public String getPassNuevo() {
		return passNuevo;
	}

	public String getPassNuevoValidate() {
		return passNuevoValidate;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassActual(String passActual) {
		this.passActual = passActual;
	}

	public void setPassNuevo(String passNuevo) {
		this.passNuevo = passNuevo;
	}

	public void setPassNuevoValidate(String passNuevoValidate) {
		this.passNuevoValidate = passNuevoValidate;
	}

}
