package grupo6.modulo.reports.dao.impl.dto;

import java.util.Date;

/**
 * 
 * @author ralfalk
 *
 */
public class ReporteRatingProductoPaqueteDTO {

	
	private String nombrePaquete;
	private double calificacionPromedio;
	private int votantes;

	public ReporteRatingProductoPaqueteDTO(String nombrePaquete,
			double calificacionPromedio,int votantes) {

		this.nombrePaquete=nombrePaquete;
		this.calificacionPromedio = calificacionPromedio;
		this.setVotantes(votantes);
	}

	
	public String getNombreProducto() {
		return nombrePaquete;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombrePaquete = nombreProducto;
	
	}

	public double getCalificacionPromedio() {
		return calificacionPromedio;
	}

	public void setCalificacionPromedio(float calificacionPromedio) {
		this.calificacionPromedio = calificacionPromedio;
	}



	public int getVotantes() {
		return votantes;
	}


	public void setVotantes(int votantes) {
		this.votantes = votantes;
	}


	
}
