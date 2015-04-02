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
import javax.persistence.Transient;

import org.apache.commons.math3.stat.StatUtils;

/**
 * Entidad que representa las calificaciones de los servicios un producto:
 * ubicacion, comodidad etc.
 * 
 * @author caespinosam
 * 
 */
@Entity
@SequenceGenerator(name = "seq_rating_producto_servicio", initialValue = 1, allocationSize = 100)
public class RatingProducto implements Serializable {

	/**
	 * UID generado.
	 */
	private static final long serialVersionUID = -8658863654377821942L;

	/** Identificador único. **/
	private Long id;
	/** El id del producto relacionado. **/
	private Long productoId;
	/** El id del cliente quien calificó el producto. */
	private Long clienteId;
	/** El tipo de servicio a calificar.**/
	private ETipoRating tipoServicio;
	/** El estado de la calificacion.**/
	private EEstadoRating estado;
	/** Contador de calificación. **/
	private Long excelente = 0L;
	/** Contador de calificación. **/
	private Long muyBueno = 0L;
	/** Contador de calificación. **/
	private Long regular = 0L;
	/** Contador de calificación. **/
	private Long malo = 0L;
	/** Contador de calificación. **/
	private Long pesimo = 0L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_rating_producto_servicio")
	public Long getId() {
		return id;
	}

	@Column(nullable = false)
	public Long getProductoId() {
		return productoId;
	}
	
	@Column
	public Long getClienteId() {
		return clienteId;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public ETipoRating getTipoServicio() {
		return tipoServicio;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public EEstadoRating getEstado() {
		return estado;
	}

	@Column(nullable = false)
	public Long getExcelente() {
		return excelente;
	}

	@Column(nullable = false)
	public Long getMuyBueno() {
		return muyBueno;
	}

	@Column(nullable = false)
	public Long getRegular() {
		return regular;
	}

	@Column(nullable = false)
	public Long getMalo() {
		return malo;
	}

	@Column(nullable = false)
	public Long getPesimo() {
		return pesimo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public void setTipoServicio(ETipoRating tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	public void setEstado(EEstadoRating estado) {
		this.estado = estado;
	}

	public void setExcelente(Long excelente) {
		this.excelente = excelente;
	}

	public void setMuyBueno(Long muyBueno) {
		this.muyBueno = muyBueno;
	}

	public void setRegular(Long regular) {
		this.regular = regular;
	}

	public void setMalo(Long malo) {
		this.malo = malo;
	}

	public void setPesimo(Long pesimo) {
		this.pesimo = pesimo;
	}
	
	/**
	 * Retorna la calificación promedio para el servicio.
	 * @return la calificación promedio para el servicio.
	 */
	@Transient
	public double getPromedioCalificacion() {	
		
		return StatUtils.mean(new double[]{
				(double)excelente,
				(double)muyBueno, 
				(double)regular, 
				(double)malo,
				(double)pesimo});
	}
	
	/**
	 * Califica el servicio actual.
	 */
	@Transient
	public void calificar(ETipoCalificacionRating calificacion) {
		if (calificacion == ETipoCalificacionRating.EXECELENTE) {
			excelente++;
		} else if (calificacion == ETipoCalificacionRating.MALO) {
			malo++;
		} else if (calificacion == ETipoCalificacionRating.MUYBUENO) {
			muyBueno++;
		} else if (calificacion == ETipoCalificacionRating.PESIMO) {
			pesimo++;
		} else if (calificacion == ETipoCalificacionRating.REGULAR) {
			malo++;
		}
		setEstado(EEstadoRating.CALIFICADO);

	}

}
