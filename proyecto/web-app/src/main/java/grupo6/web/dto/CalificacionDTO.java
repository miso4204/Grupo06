package grupo6.web.dto;

import java.io.Serializable;

/**
 * Calificacion JSON de un producto.
 * 
 * @author caespinosam
 * 
 */
public class CalificacionDTO implements Serializable {

	/**
	 * UID generado.
	 */
	private static final long serialVersionUID = -8658863654377821942L;

	private String nombre;
	private Double puntuacion;
	private Integer cantidadVotantes;

	public String getNombre() {
		return nombre;
	}

	public Double getPuntuacion() {
		return puntuacion;
	}

	public Integer getCantidadVotantes() {
		return cantidadVotantes;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public void setCantidadVotantes(Integer cantidadVotantes) {
		this.cantidadVotantes = cantidadVotantes;
	}

}
