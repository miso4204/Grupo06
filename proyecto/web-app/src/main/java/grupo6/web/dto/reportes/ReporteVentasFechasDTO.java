package grupo6.web.dto.reportes;

import grupo6.web.json.serializer.JsonDateDeserializer;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * Clase que representa un reporte de ventas entre fechas
 * 
 * @author Alejo
 *
 */
public class ReporteVentasFechasDTO {

	private Date fechaInicial;
	private Date fechaFinal;
	private int totalVentas;
	private double totalDineroEnVentas;

	public ReporteVentasFechasDTO(Date fechaInicial, Date fechaFinal,
			int totalVentas, double totalDineroEnVentas) {
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.totalVentas = totalVentas;
		this.totalDineroEnVentas = totalDineroEnVentas;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public int getTotalVentas() {
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

	public static ReporteVentasFechasDTO getReporteVentas(
			grupo6.modulo.reports.dao.impl.dto.ReporteVentasFechasDTO reporte) {
		return new ReporteVentasFechasDTO(reporte.getFechaInicial(),
				reporte.getFechaFinal(), reporte.getTotalVentas(),
				reporte.getTotalDineroEnVentas());

	}

}
