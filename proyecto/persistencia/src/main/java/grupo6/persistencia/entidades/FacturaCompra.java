package grupo6.persistencia.entidades;

import grupo6.modulo.payment.dao.enums.TiposPago;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "seq_factura", initialValue = 1, allocationSize = 100)
@Table(name = "factura_compra")
public class FacturaCompra implements Serializable {

	
	/**
	 * generate
	 */
	private static final long serialVersionUID = -6096553714339129260L;
	private long id;
	private Usuario usuario;
	private Date fechaPago;
	private List<Producto> productosComprados;
	private double totalPagado;
	private TiposPago tipoPago;
	
	//------------------------ GETTERS AND SETTERS ---------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_factura")
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USUARIO_ID")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_PAGO",nullable = false)
	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	@ManyToMany
	@JoinTable(
		      name="FACT_PRODUCTO",
		      joinColumns={@JoinColumn(name="FACTURA_ID", referencedColumnName="ID")},
		      inverseJoinColumns={@JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")})
	public List<Producto> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(List<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}

	@Column(name="TOTAL_PAGADO",nullable = false)
	public double getTotalPagado() {
		return totalPagado;
	}

	public void setTotalPagado(double totalPagado) {
		this.totalPagado = totalPagado;
	}

	@Enumerated(EnumType.STRING)
	public TiposPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TiposPago tipoPago) {
		this.tipoPago = tipoPago;
	}

}
