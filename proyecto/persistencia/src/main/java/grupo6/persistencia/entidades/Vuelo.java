package grupo6.persistencia.entidades;

import grupo6.modulo.payment.dao.enums.Aerolineas;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad que permite modelar los viajes disponibles para los productos (planes)
 * que ofrecen los proveedores en la aplicacion
 * @author jhon
 *
 */
@Entity
@SequenceGenerator(name = "seq_vuelo", initialValue = 1, allocationSize = 100)
public class Vuelo {
	
	private long id;
	private Aerolineas aerolinea;
	private int numPersonas;
	private double precioVuelo; // Costo del trayecto
	private double precioTotal; // Resultado de multiplicar el precioVuelo por el numPersonas
	private String origen;
	private String destino;
	private Calendar fechaSalida;
	private Calendar fechaLlegada;
	
	/**
	 * Constructor por defecto de la clase 
	 */
	public Vuelo() {	}

	
	// ---------------------- GUETTERS AND SETTERS ------------------------
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_vuelo")
	@Column(name = "ID")
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	@Enumerated(EnumType.STRING)
	public Aerolineas getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(Aerolineas aerolinea) {
		this.aerolinea = aerolinea;
	}

	@Column(name="NUM_PERSONAS",nullable = false)
	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	@Column(name="PRECIO_VUELO",nullable = false)
	public double getPrecioVuelo() {
		return precioVuelo;
	}

	public void setPrecioVuelo(double precioVuelo) {
		this.precioVuelo = precioVuelo;
	}

	@Column(name="PRECIO_TOTAL",nullable = false)
	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	@Column(name="ORIGEN",nullable = false, length=40)
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	@Column(name="DESTINO",nullable = false, length=40)
	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_SALIDA",nullable = false)
	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_LLEGADA",nullable = false)
	public Calendar getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(Calendar fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
	
}
