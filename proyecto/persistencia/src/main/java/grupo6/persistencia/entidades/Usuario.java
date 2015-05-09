package grupo6.persistencia.entidades;

import grupo6.modulo.payment.dao.enums.TipoMoneda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@SequenceGenerator(name = "seq_usuario", initialValue = 1, allocationSize = 100)
@Table(name = "usuario", uniqueConstraints = {@UniqueConstraint(columnNames={"usuario"})})
public class Usuario implements Serializable {
	
	/**
	 * generate
	 */
	private static final long serialVersionUID = 7608600827181912509L;
	private long id;
	private String usuario;
	private String password;
	private String nombre;
	private String direccion;
	private String telefono;
	private List<FacturaCompra> facturas = new ArrayList<FacturaCompra>();
	private String rol;
	private String email;
	private String website; // util para el proveedor
	private List<Producto> carritoCompras = new ArrayList<Producto>();
	private TipoMoneda tipoMoneda =  TipoMoneda.DOLAR;
	private Double descuentoPse = 0.0;
	private Double descuentoTc = 0.0;
	private Double descuentoCash = 0.0;
	
//---------------------- GUETTERS AND SETTERS ------------------	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_usuario")
	@Column(name="UsuarioId")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="usuario",nullable = false, length = 30)
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@Column(name="password",nullable = false, length = 100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="nombre",nullable = false, length = 100)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name="direcciom",nullable = false, length = 180)
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Column(name="telefono",nullable = false, length = 50)
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@OneToMany
	@JoinTable(name = "facturas", 
			   joinColumns = @JoinColumn(name="UsuarioId", referencedColumnName="UsuarioId"), 
			   inverseJoinColumns = @JoinColumn(name = "facturaId", referencedColumnName="ID"))
	public List<FacturaCompra> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<FacturaCompra> facturas) {
		this.facturas = facturas;
	}
	
	@Column(name="rol",nullable = false, length = 50)
	public String getRol() {
		return rol;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipoMoneda",nullable = false, length = 50)
	public TipoMoneda getTipoMoneda() {
		return tipoMoneda;
	}
	
	public void setTipoMoneda(TipoMoneda tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	@OneToMany
	@JoinTable(name = "CarritoCompras", 
			   joinColumns = @JoinColumn(name="UsuarioId", referencedColumnName="UsuarioId"), 
			   inverseJoinColumns = @JoinColumn(name = "productoId", referencedColumnName="ID"))
	public List<Producto> getCarritoCompras() {
		return carritoCompras;
	}
	public void setCarritoCompras(List<Producto> carritoCompras) {
		this.carritoCompras = carritoCompras;
	}

	public String getEmail() {
		return email;
	}

	public String getWebsite() {
		return website;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	public Double getDescuentoPse() {
		return descuentoPse;
	}
	public void setDescuentoPse(Double descuentoPse) {
		this.descuentoPse = descuentoPse;
	}
	public Double getDescuentoTc() {
		return descuentoTc;
	}
	public void setDescuentoTc(Double descuentoTc) {
		this.descuentoTc = descuentoTc;
	}
	public Double getDescuentoCash() {
		return descuentoCash;
	}
	public void setDescuentoCash(Double descuentoCash) {
		this.descuentoCash = descuentoCash;
	}	
	
	
	
	
}
