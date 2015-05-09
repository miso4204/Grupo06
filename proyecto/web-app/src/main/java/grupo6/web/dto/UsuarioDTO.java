package grupo6.web.dto;

import grupo6.modulo.payment.dao.enums.TipoMoneda;

/**
 * DTO para ser convertido en JSON y que representa los datos basicos de un Usuario.
 * Estos objetos se guardarán en sesión como identificador dle usuario actual.
 * 
 * @author caespinosam
 *
 */
public class UsuarioDTO {
	
	private long id;
	private String usuario;
	private String password;
	private String nombre;
	private String direccion;
	private String telefono;
	private String rol;
	private String email;
	private String website;
	private TipoMoneda tipoMoneda;
	private Double descuentoPse;
	private Double descuentoTc;
	private Double descuentoCash;
	
	
	public long getId() {
		return id;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getPassword() {
		return password;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getRol() {
		return rol;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setRol(String rol) {
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
	public TipoMoneda getTipoMoneda() {
		return tipoMoneda;
	}
	public void setTipoMoneda(TipoMoneda tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
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
