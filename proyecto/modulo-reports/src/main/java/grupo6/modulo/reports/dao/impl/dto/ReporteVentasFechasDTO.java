package grupo6.modulo.reports.dao.impl.dto;

import java.util.Date;

/**
 * Clase que representa un reporte de ventas entre fechas
 * 
 * @author Alejo
 *
 */
public class ReporteVentasFechasDTO {

	private Date fechaInicial;
	private Date fechaFinal;
	private long totalVentas;
	private double totalDineroEnVentas;

	public ReporteVentasFechasDTO(Date fechaInicial, Date fechaFinal,
			long totalVentas, double totalDineroEnVentas) {
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.totalVentas = totalVentas;
		this.totalDineroEnVentas = totalDineroEnVentas;
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

}
