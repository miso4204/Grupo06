package grupo6.web.dto;

import grupo6.modulo.payment.dao.enums.Aerolineas;

import java.io.Serializable;

/**
 * DTO que represneta un Producto creado a partir de un JSON.
 * 
 * @author jhon
 * 
 */
public class VueloDTO implements Serializable {

	/**
	 * UID generado
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Aerolineas aerolinea;
	private int numPersonas; // OPCIONAL para cuando ya se va a pagar
	private double precioVuelo; // Costo del trayecto
	private double precioTotal; // Resultado de multiplicar el precioVuelo por el numPersonas
	private String origen;
	private String destino;
	private String fechaSalida;
	private String fechaLlegada;
	
	//------------------------------ GUETTERS AND SETTERS  -------------------------------
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Aerolineas getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(Aerolineas aerolinea) {
		this.aerolinea = aerolinea;
	}
	public int getNumPersonas() {
		return numPersonas;
	}
	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}
	public double getPrecioVuelo() {
		return precioVuelo;
	}
	public void setPrecioVuelo(double precioVuelo) {
		this.precioVuelo = precioVuelo;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	//@JsonSerialize(using = JsonDateSerializer.class)
	public String getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	//@JsonSerialize(using = JsonDateSerializer.class)
	public String getFechaLlegada() {
		return fechaLlegada;
	}
	public void setFechaLlegada(String fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
		
}
