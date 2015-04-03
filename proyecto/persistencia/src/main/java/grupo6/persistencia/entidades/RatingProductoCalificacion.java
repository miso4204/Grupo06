package grupo6.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Entidad que representa las calificaciones dadas a un servicio de un producto:
 * Execelente, Malo, etc.
 * 
 * @author caespinosam
 * 
 */
@Entity
@SequenceGenerator(name = "seq_rating_producto_calificacion", initialValue = 1, allocationSize = 100)
public class RatingProductoCalificacion implements Serializable {

	/**
	 * UID generado.
	 */
	private static final long serialVersionUID = -8658863654377821942L;

	/** Identificador único. **/
	private Long id;
	/** El id del servicio a calificar. **/
	private Long ratingProductoId;
	/** El id del cliente quien calificó el producto. */
	private Long clienteId;
	/** La calificación dada por el cliente */
	private ETipoCalificacionRating calificacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_rating_producto_calificacion")
	public Long getId() {
		return id;
	}

	@Column
	public Long getClienteId() {
		return clienteId;
	}

	@Column
	public Long getRatingProductoId() {
		return ratingProductoId;
	}

	@Enumerated(EnumType.STRING)
	@Column
	public ETipoCalificacionRating getCalificacion() {
		return calificacion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public void setRatingProductoId(Long ratingProductoId) {
		this.ratingProductoId = ratingProductoId;
	}

	public void setCalificacion(ETipoCalificacionRating calificacion) {
		this.calificacion = calificacion;
	}

}
