package grupo6.web.dto;

/**
 * DTO para ser convertido en JSON y que representa los datos basicos de un Usuario.
 * 
 * @author caespinosam
 *
 */
public class UsuarioDTO {
	
	private long id;
	private String usuario;
	private String password;
	private String nombre;
	private String direccion;
	private String telefono;
	private String rol;
	
	
	public long getId() {
		return id;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getPassword() {
		return password;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getRol() {
		return rol;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	
	
}
