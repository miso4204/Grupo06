package grupo6.marketplace.ecoturismo.modelo;


import grupo6.marketplace.ecoturismo.modelo.enums.TiposPago;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa una factura de compra de un producto comprado por un usuario
 * @author Alejo
 *
 */
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public List<Producto> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(List<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}

	public double getTotalPagado() {
		return totalPagado;
	}

	public void setTotalPagado(double totalPagado) {
		this.totalPagado = totalPagado;
	}

	public TiposPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TiposPago tipoPago) {
		this.tipoPago = tipoPago;
	}

}
