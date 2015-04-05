package grupo6.web.dto;

import grupo6.web.json.serializer.JsonDateSerializer;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * DTO que represneta un Producto creado a partir de un JSON.
 * 
 * @author caespinosam
 * 
 */
public class ProductoRequestDTO implements Serializable {

	/**
	 * UID generado.
	 */
	private static final long serialVersionUID = -8658863654377821942L;

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
	/** Las fotos del producto. **/
	private String urlImagen;
	/** Id del proveedor que crea el producto.*/
	private Long proveedorId;

	public String getNombre() {
		return nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public String getCiudad() {
		return ciudad;
	}

	public Double getPrecio() {
		return precio;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
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
	
	

}
