package grupo6.web.dto.reportes;

/**
 * Clase que representa un reporte de ventas por ciudad
 * 
 * @author Alejo
 *
 */
public class ReporteVentasCiudadDTO {

	private String ciudad;
	private long totalVentas;
	private double totalDineroEnVentas;

	public ReporteVentasCiudadDTO(String ciudad, long totalVentas,
			double totalDineroEnVentas) {
		this.ciudad = ciudad;
		this.totalVentas = totalVentas;
		this.totalDineroEnVentas = totalDineroEnVentas;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public long getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(int totalVentas) {
		this.totalVentas = totalVentas;
	}

	public double getTotalDineroEnVentas() {
		return totalDineroEnVentas;
	}

	public void setTotalDineroEnVentas(double totalDineroEnVentas) {
		this.totalDineroEnVentas = totalDineroEnVentas;
	}

	public static ReporteVentasCiudadDTO getReporteVentas(
			grupo6.modulo.reports.dao.impl.dto.ReporteVentasCiudadDTO reporte) {
		return new ReporteVentasCiudadDTO(reporte.getCiudad(),
				reporte.getTotalVentas(), reporte.getTotalDineroEnVentas());
	}
}
