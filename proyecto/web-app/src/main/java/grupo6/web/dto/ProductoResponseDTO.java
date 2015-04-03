package grupo6.web.dto;

import grupo6.web.json.serializer.JsonDateDeserializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * DTO que representa un Producto JSON a devolver a la capa web. 
 * @author caespinosam
 * 
 */
public class ProductoResponseDTO implements Serializable  {
	
	/**
	 * UID generado.
	 */
	private static final long serialVersionUID = -8658863654377821942L;

	/** Id del producto en el sistema*/
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
	/** Las fotos del producto. **/
	private String urlImagen;
	private Date ultimaCompra;
	/** Calificaciones asociadas al producto.**/
	private List<CalificacionDTO> calificaciones;
	
	public Long getId() {
		return id;
	}


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

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public Date getUltimaCompra() {
		return ultimaCompra;
	}


	public String getUrlImagen() {
		return urlImagen;
	}
	
	public List<CalificacionDTO> getCalificaciones() {
		return calificaciones;
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

	public void setUltimaCompra(Date ultimaCompra) {
		this.ultimaCompra = ultimaCompra;
	}

	public void setCalificaciones(List<CalificacionDTO> calificaciones) {
		this.calificaciones = calificaciones;
	}

	
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
