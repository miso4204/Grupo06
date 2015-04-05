package grupo6.marketplace.ecoturismo.modelo;

import java.io.Serializable;

/**
 * Calificacion de un producto.
 * 
 * @author caespinosam
 * 
 */
public class CalificacionDTO implements Serializable {

	private static final long serialVersionUID = -8658863654377821942L;

	private String nombre;
	private Double puntuacion;
	private Integer cantidadVotantes;

	public CalificacionDTO(String nombre, Double puntuacion,Integer cantidadVotantes) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.cantidadVotantes = cantidadVotantes;
	}

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
