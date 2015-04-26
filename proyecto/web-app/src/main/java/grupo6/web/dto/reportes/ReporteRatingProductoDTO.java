package grupo6.web.dto.reportes;

/**
 * Clase que representa un reporte de ventas por rating de producto
 * 
 * @author ralfalk
 *
 */
public class ReporteRatingProductoDTO {

	private int idProducto;

	private float rating;

	public ReporteRatingProductoDTO(int idProducto, float rating) {
		this.idProducto = idProducto;
		this.rating = rating;

	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(long rating) {
		this.rating = rating;
	}

	public static ReporteRatingProductoDTO getReporteRating(
			grupo6.modulo.reports.dao.impl.dto.ReporteRatingProductoDTO reporte) {
		return new ReporteRatingProductoDTO(reporte.getProducto(),
				reporte.getCalificacionPromedio());
	}
}
