package grupo6.persistencia.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que nos permite almacenar las actividades 
 * que se puede realizar en un plan especifico	
 * @author jhon
 *
 */
@Entity
@SequenceGenerator(name = "seq_actividad", initialValue = 1, allocationSize = 100)
public class Actividad {

	
	private long id;
	private String nombreActividad;
	private String descripcion;
	private Date fechaActividad;
	private double costoActividad;
	private int numPersonas;
	private double costoTotal;
	
	
	/**
	 * 	Constructor por defecto
	 */
	public Actividad() {}


	// ------------------------------------ GUETTERS AND SETTERS  ----------------------------
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_actividad")
	@Column(name = "ID")
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	@Column(name="NOMBRE_ACTIVIDAD",nullable = false)
	public String getNombreActividad() {
		return nombreActividad;
	}


	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}


	@Column(name="DESCRIPCION",nullable = false)
	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ACTIVIDAD",nullable = false)
	public Date getFechaActividad() {
		return fechaActividad;
	}


	public void setFechaActividad(Date fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	@Column(name="COSTO_ACTIVIDAD",nullable = false)
	public double getCostoActividad() {
		return costoActividad;
	}


	public void setCostoActividad(double costoActividad) {
		this.costoActividad = costoActividad;
	}


	@Column(name="NUMERO_PERSONAS",nullable = true)
	public int getNumPersonas() {
		return numPersonas;
	}


	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	@Column(name="COSTO_TOTAL",nullable = false)
	public double getCostoTotal() {
		return costoTotal;
	}


	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}
	
}
