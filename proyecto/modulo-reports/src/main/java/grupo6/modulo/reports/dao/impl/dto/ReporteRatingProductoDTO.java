package grupo6.modulo.reports.dao.impl.dto;

/**
 * 
 * @author ralfalk
 *
 */
public class ReporteRatingProductoDTO {
	private int idProducto;
	private float calificacionPromedio;

	public ReporteRatingProductoDTO(int idProducto,
			float calificacionPromedio) {
		this.idProducto = idProducto;
		this.calificacionPromedio = calificacionPromedio;
	}

	public int getProducto() {
		return idProducto;
	}

	public void setCiudad(int idProducto) {
		this.idProducto = idProducto;
	}

	public float getCalificacionPromedio() {
		return calificacionPromedio;
	}

	public void setCalificacionPromedio(float calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}

}
