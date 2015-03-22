package grupo6.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entidad cliente del market place.
 * @author caespinosam
 *
 */
@Entity
public class Cliente {
	
	/** Id del cliente.**/
	private Long id;
	/**El  username **/
	private String  usuario;
	/** La clave para ingresar**/
	private String clave;
	/** Nombre completo del cliente.**/
	private String nombre;
	
	
	
	@Id
	public Long getId() {
		return id;
	}	

	@Column(nullable = false, length = 50)
	public String getUsuario() {
		return usuario;
	}
	
	@Column(nullable = false, length = 50)
	public String getClave() {
		return clave;
	}
	
	@Column(nullable = false, length = 50)
	public String getNombre() {
		return nombre;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
}
