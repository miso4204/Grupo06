package grupo6.web.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que nos permite realizar el transporte de datos en los 
 * metodos REST
 * @author jhon
 *
 */
public class ActividadDTO implements Serializable {

	/**
	 * UID generado
	 */
	private static final long serialVersionUID = -7376840953026232726L;
	
	private long id;
	private String nombreActividad;
	private String descripcion;
	private Date fechaActividad;
	private double costoActividad;
	private int numPersonas;
	private double costoTotal;
	
	/**
	 * Constructor
	 */
	public ActividadDTO() {	}

	
	//---------------------- GUETTERS AND SETTERS  ------------------------
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(Date fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public double getCostoActividad() {
		return costoActividad;
	}

	public void setCostoActividad(double costoActividad) {
		this.costoActividad = costoActividad;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}

}
