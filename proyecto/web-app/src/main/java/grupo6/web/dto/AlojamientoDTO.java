package grupo6.web.dto;

import grupo6.modulo.payment.dao.enums.TipoAlojamiento;

import java.io.Serializable;

/**
 * Clase que sirve para transferiri los datos mediante JSON
 * @author jhon
 *
 */
public class AlojamientoDTO implements Serializable {
	
	/**
	 * UID generado
	 */
	private static final long serialVersionUID = 5843736093189289951L;
	
	private long id;
	private TipoAlojamiento tipo;
	private int numMaxPersonas;
	private int numeroNoches;// OPCIONAL se utilizara para cuando se haga la compra no cuando se cree por el proveedor
	private double precioPorDia; // costo de un dia en este alojamiento
	private double precioTotal; // OPCIONAL resultado de multiplicar el precioPorDia y el numero de noches
	private boolean aireAcondicionado;
	private boolean piscina;
	private boolean zonasVerdes;
	private boolean vigilancia;
	
	// ------------------- GUETTERS AND SETTERS --------------------
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public TipoAlojamiento getTipo() {
		return tipo;
	}
	public void setTipo(TipoAlojamiento tipo) {
		this.tipo = tipo;
	}
	public int getNumMaxPersonas() {
		return numMaxPersonas;
	}
	public void setNumMaxPersonas(int numMaxPersonas) {
		this.numMaxPersonas = numMaxPersonas;
	}
	public int getNumeroNoches() {
		return numeroNoches;
	}
	public void setNumeroNoches(int numeroNoches) {
		this.numeroNoches = numeroNoches;
	}
	public double getPrecioPorDia() {
		return precioPorDia;
	}
	public void setPrecioPorDia(double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public boolean isAireAcondicionado() {
		return aireAcondicionado;
	}
	public void setAireAcondicionado(boolean aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}
	public boolean isPiscina() {
		return piscina;
	}
	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}
	public boolean isZonasVerdes() {
		return zonasVerdes;
	}
	public void setZonasVerdes(boolean zonasVerdes) {
		this.zonasVerdes = zonasVerdes;
	}
	public boolean isVigilancia() {
		return vigilancia;
	}
	public void setVigilancia(boolean vigilancia) {
		this.vigilancia = vigilancia;
	}

}
