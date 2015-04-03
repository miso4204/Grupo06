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
 * Entidad que representa los servicios un producto a ser calificado: ubicacion,
 * comodidad, calificacion general, etc.
 * 
 * @author caespinosam
 * 
 */
@Entity
@SequenceGenerator(name = "seq_rating_producto", initialValue = 1, allocationSize = 100)
public class RatingProducto implements Serializable {

	/**
	 * UID generado.
	 */
	private static final long serialVersionUID = -8658863654377821942L;

	/** Identificador único. **/
	private Long id;
	/** El id del producto relacionado. **/
	private Long productoId;
	/** El tipo de servicio a calificar. **/
	private ETipoRating tipoServicio;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_rating_producto")
	public Long getId() {
		return id;
	}

	@Column(nullable = false)
	public Long getProductoId() {
		return productoId;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public ETipoRating getTipoServicio() {
		return tipoServicio;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public void setTipoServicio(ETipoRating tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	
	}
