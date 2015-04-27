package grupo6.web.dto.reportes;

/**
 * Clase que representa un reporte de ventas por rating de producto
 * 
 * @author ralfalk
 *
 */
public class ReporteRatingPorCiudadProductoDTO {

	private String ciudadProducto;
	private String nombreProducto;
	private double calificacionPromedio;
	private int votantes; 

	public ReporteRatingPorCiudadProductoDTO(String ciudadProducto,String nombreProducto,
			double calificacionPromedio,int votantes) {
		this.setCiudadProducto(ciudadProducto);
		this.nombreProducto=nombreProducto;
		this.calificacionPromedio = calificacionPromedio;
		this.setVotantes(votantes);
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

	public void setCalificacionPromedio(float calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}


	public String getCiudadProducto() {
		return ciudadProducto;
	}


	public void setCiudadProducto(String ciudadProducto) {
		this.ciudadProducto = ciudadProducto;
	}
	


	public int getVotantes() {
		return votantes;
	}


	public void setVotantes(int votantes) {
		this.votantes = votantes;
	}


}
