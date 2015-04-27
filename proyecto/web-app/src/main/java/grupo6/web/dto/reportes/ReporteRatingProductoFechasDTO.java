package grupo6.web.dto.reportes;

import java.util.Date;

/**
 * Clase que representa un reporte de ventas entre fechas
 * 
 * @author Ralf alk
 *
 */
public class ReporteRatingProductoFechasDTO {

	private Date fechaInicial;
	private Date fechaFinal;
	private String nombreProducto;
	private double calificacionPromedio;

	public ReporteRatingProductoFechasDTO(Date fechaInicial, Date fechaFinal,
			String nombreProducto, double calificacionPromedio) {
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.setNombreProducto(nombreProducto);
		this.setCalificacionPromedio(calificacionPromedio);
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getCalificacionPromedio() {
		return calificacionPromedio;
	}

	public void setCalificacionPromedio(double calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}
	
	public static ReporteRatingProductoFechasDTO getReporteRatingEntreFechas(
			grupo6.modulo.reports.dao.impl.dto.ReporteRatingProductoFechasDTO reporte) {
		return new ReporteRatingProductoFechasDTO(reporte.getFechaInicial(),
				reporte.getFechaFinal(),reporte.getNombreProducto(),
				reporte.getCalificacionPromedio());
	}

	
}
