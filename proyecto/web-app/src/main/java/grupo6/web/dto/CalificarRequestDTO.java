package grupo6.web.dto;

/**
 * Modelo dto de una solicitud de calificación de servicio que es construido
 * a partir de un JSON.
 * 
 * @author caespinosam
 *
 */
public class CalificarRequestDTO {
	
	private Long servicioId;
	private Integer puntaje; // 1-5

	

	public Long getServicioId() {
		return servicioId;
	}

	public void setServicioId(Long servicioId) {
		this.servicioId = servicioId;
	}

	public Integer getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}

}
