package grupo6.persistencia.entidades;

import grupo6.modulo.payment.dao.enums.TipoAlojamiento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_alojamiento", initialValue = 1, allocationSize = 100)
public class Alojamiento {

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
	
	/**
	 * Constructor de la clase
	 */
	public Alojamiento() {	}

	
	// ------------------------------------ GUETTERS AND SETTERS ----------------------------------
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_alojamiento")
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	public TipoAlojamiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoAlojamiento tipo) {
		this.tipo = tipo;
	}

	@Column(name = "NUM_MAX_PERSONAS", nullable=false)	
	public int getNumMaxPersonas() {
		return numMaxPersonas;
	}

	public void setNumMaxPersonas(int numMaxPersonas) {
		this.numMaxPersonas = numMaxPersonas;
	}

	@Column(name = "NUMERO_NOCHES", nullable=false)
	public int getNumeroNoches() {
		return numeroNoches;
	}

	public void setNumeroNoches(int numeroNoches) {
		this.numeroNoches = numeroNoches;
	}

	@Column(name = "PRECIO_DIA", nullable=false)
	public double getPrecioPorDia() {
		return precioPorDia;
	}

	public void setPrecioPorDia(double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}

	@Column(name = "PRECIO_TOTAL", nullable=true)
	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	@Column(name = "AIRE_ACONDICIONADO", nullable=false)
	public boolean isAireAcondicionado() {
		return aireAcondicionado;
	}

	public void setAireAcondicionado(boolean aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}
	
	@Column(name = "PSICINA", nullable=false)
	public boolean isPiscina() {
		return piscina;
	}

	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}

	@Column(name = "ZONA_VERDE", nullable=false)
	public boolean isZonasVerdes() {
		return zonasVerdes;
	}

	public void setZonasVerdes(boolean zonasVerdes) {
		this.zonasVerdes = zonasVerdes;
	}

	@Column(name = "VIGILANCIA", nullable=false)
	public boolean isVigilancia() {
		return vigilancia;
	}

	public void setVigilancia(boolean vigilancia) {
		this.vigilancia = vigilancia;
	}
	
}
