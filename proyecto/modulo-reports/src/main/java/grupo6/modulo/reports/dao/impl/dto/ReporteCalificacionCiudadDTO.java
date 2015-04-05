package grupo6.modulo.reports.dao.impl.dto;

public class ReporteCalificacionCiudadDTO {

	private String ciudad;
	private float calificacionPromedio;

	public ReporteCalificacionCiudadDTO(String ciudad,
			float calificacionPromedio) {
		this.ciudad = ciudad;
		this.calificacionPromedio = calificacionPromedio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public float getCalificacionPromedio() {
		return calificacionPromedio;
	}

	public void setCalificacionPromedio(float calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}

}
