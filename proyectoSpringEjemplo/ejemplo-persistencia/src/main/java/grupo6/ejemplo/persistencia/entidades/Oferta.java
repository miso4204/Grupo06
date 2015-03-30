package grupo6.ejemplo.persistencia.entidades;

import java.io.Serializable;
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
 * Entidad Oferta del MarketPlace.
 * 
 * @author caespinosam
 * 
 */
@Entity
@SequenceGenerator(name = "seq_oferta", initialValue = 1, allocationSize = 100)
public class Oferta implements Serializable {

	/**
	 * UID generado.
	 */
	private static final long serialVersionUID = 7981098048810756321L;

	/** Identificador único de la oferta. **/
	private Long id;
	/** La ciudad de la oferta. */
	private String ciudad;
	/** la descripcion de la oferta. */
	private String descripcion;
	/** La fecha de finalizacion de la oferta. */
	private Date fechaFin;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_oferta")
	public Long getId() {
		return id;
	}

	@Column(nullable = false, length = 50)
	public String getCiudad() {
		return ciudad;
	}

	@Column(nullable = false, length = 50)
	public String getDescripcion() {
		return descripcion;
	}

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getId() + "-" + getDescripcion() + "-" + getCiudad();
	}

}
