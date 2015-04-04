package grupo6.web.dto;

import java.io.Serializable;

/**
 * Calificacion JSON de un producto.
 * 
 * @author caespinosam
 * 
 */
public class CalificacionResponseDTO implements Serializable {

	/**
	 * UID generado.
	 */
	private static final long serialVersionUID = -8658863654377821942L;

	private Long id;
	private String nombre;
	private Double puntuacion;
	private Integer cantidadVotantes;
	private Boolean votada;
	private Integer calificacionDada;

	public Long getId() {
		return id;
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
	

	public Boolean getVotada() {
		return votada;
	}


	public Integer getCalificacionDada() {
		return calificacionDada;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setVotada(Boolean votada) {
		this.votada = votada;
	}

	public void setCalificacionDada(Integer calificacionDada) {
		this.calificacionDada = calificacionDada;
	}
	

}
