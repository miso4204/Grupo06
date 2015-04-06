package grupo6.marketplace.ecoturismo.modelo;

import grupo6.marketplace.ecoturismo.modelo.enums.RolType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 7608600827181912509L;
	private long id;
	private String usuario;
	private String password;
	private String nombre;
	private String direccion;
	private String telefono;
	private RolType rol;
	private String email;
	private String website;
	private List<FacturaCompra> facturas = new ArrayList<FacturaCompra>();
	private List<Producto> carritoCompras = new ArrayList<Producto>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public RolType getRol() {
		return rol;
	}

	public void setRol(RolType rol) {
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<FacturaCompra> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<FacturaCompra> facturas) {
		this.facturas = facturas;
	}

	public List<Producto> getCarritoCompras() {
		return carritoCompras;
	}

	public void setCarritoCompras(List<Producto> carritoCompras) {
		this.carritoCompras = carritoCompras;
	}

}
