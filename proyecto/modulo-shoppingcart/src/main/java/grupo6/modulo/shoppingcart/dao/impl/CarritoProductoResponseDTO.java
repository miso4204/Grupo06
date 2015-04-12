package grupo6.modulo.shoppingcart.dao.impl;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.persistencia.entidades.Producto;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa el JSON de respuesta de un producto del carrito
 * 
 * @author Alejo
 *
 */
public class CarritoProductoResponseDTO implements Serializable{
	/**
	 * UID generado.
	 */
	private static final long serialVersionUID = -3226161185957295120L;

	/** Identificador único. **/
	private Long id;
	/** Nombre del paquete producto. Ej: paquete semana santa. **/
	private String nombre;
	/** Lugar. ej: Finca xxx **/
	private String lugar;
	/** Ciudad. Ej: Medellin **/
	private String ciudad;
	/** Precio del paquete en COP. **/
	private Double precio;
	/** Fecha inicio en que se debe tomar el paquete. **/
	private Date fechaInicio;
	/** Fecha fin del paquete. Opcional **/
	private Date fechaFin;
	/** Fecha en que se incia la publicacion en el marketplace. **/
	private Date fechaInicioPublicacion;
	/** Fecha en que fianliza la publicacion en el marketplace. **/
	private Date fechaFinPublicacion;
	/** Fecha en que se crea el paquete en el sistema. **/
	private Date fechaCreacion;
	/** Direccion o coordenada usada por google maps. Opcional **/
	private String direccionGoogleMaps;
	/** Las fotos del producto.**/
    private String urlImagen;
    /** El id dle proveedor quien creó producto.*/
    private Long proveedorId;
    /** tipo moneda.*/
    private TipoMoneda tipoMoneda;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Date getFechaInicioPublicacion() {
		return fechaInicioPublicacion;
	}
	public void setFechaInicioPublicacion(Date fechaInicioPublicacion) {
		this.fechaInicioPublicacion = fechaInicioPublicacion;
	}
	public Date getFechaFinPublicacion() {
		return fechaFinPublicacion;
	}
	public void setFechaFinPublicacion(Date fechaFinPublicacion) {
		this.fechaFinPublicacion = fechaFinPublicacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getDireccionGoogleMaps() {
		return direccionGoogleMaps;
	}
	public void setDireccionGoogleMaps(String direccionGoogleMaps) {
		this.direccionGoogleMaps = direccionGoogleMaps;
	}
	public String getUrlImagen() {
		return urlImagen;
	}
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	public Long getProveedorId() {
		return proveedorId;
	}
	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}
	
	public TipoMoneda getTipoMoneda() {
		return tipoMoneda;
	}
	
	public void setTipoMoneda(TipoMoneda tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	
	public static CarritoProductoResponseDTO productoToCarritoProductoDTO(Producto producto){
		CarritoProductoResponseDTO carritoProducto = new CarritoProductoResponseDTO();
		carritoProducto.setId(producto.getId());
		carritoProducto.setNombre(producto.getNombre());
		carritoProducto.setLugar(producto.getLugar());
		carritoProducto.setCiudad(producto.getCiudad());
		carritoProducto.setPrecio(producto.getPrecio());
		carritoProducto.setFechaInicio(producto.getFechaInicio());
		carritoProducto.setFechaFin(producto.getFechaFin());
		carritoProducto.setFechaInicioPublicacion((producto.getFechaInicioPublicacion()));
		carritoProducto.setFechaFinPublicacion((producto.getFechaFinPublicacion()));
		carritoProducto.setFechaCreacion(producto.getFechaCreacion());
		carritoProducto.setDireccionGoogleMaps(producto.getDireccionGoogleMaps());
		carritoProducto.setUrlImagen(producto.getUrlImagen());
		carritoProducto.setProveedorId(producto.getProveedorId());
		carritoProducto.setTipoMoneda(producto.getTipoMoneda());
		return carritoProducto;
	}
	
}
