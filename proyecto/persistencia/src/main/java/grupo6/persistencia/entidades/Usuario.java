package grupo6.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_usuario", initialValue = 1, allocationSize = 100)
@Table(name = "USUARIO")
public class Usuario implements Serializable {
	
	/**
	 * generate
	 */
	private static final long serialVersionUID = 7608600827181912509L;
	private long id;
	private String usuario;
	private String password;
	private String nombre;
	private String direccion;
	
	
//---------------------- GUETTERS AND SETTERS ------------------	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_usuario")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="usuario",nullable = false, length = 30)
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@Column(name="password",nullable = false, length = 100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="nombre",nullable = false, length = 100)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name="direcciom",nullable = false, length = 180)
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
