package grupo6.marketplace.ecoturismo.modelo;

/**
 * Clase que representa un reporte de ventas entre fechas
 * 
 * @author Alejo
 *
 */
public class ReporteVentasFechasDTO {

	private String fechaInicial;
	private String fechaFinal;
	private long totalVentas;
	private double totalDineroEnVentas;

	public ReporteVentasFechasDTO(String fechaInicial, String fechaFinal,
			long totalVentas, double totalDineroEnVentas) {
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.totalVentas = totalVentas;
		this.totalDineroEnVentas = totalDineroEnVentas;
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
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
	
	@Override
	public String toString() {
		return "Fecha Inicial : "+fechaInicial
				+"\n"
				+"Fecha Final : "+fechaFinal
				+"\n"
				+"Total en ventas : "+totalVentas
				+"\n"
				+"Total Dinero : $ "+ totalDineroEnVentas;
	}

}
