package grupo6.marketplace.ecoturismo.modelo;

import grupo6.marketplace.ecoturismo.util.CurrencyUtilidades;

import java.io.Serializable;
import java.util.List;

/**
 * Entidad que representa el producto o paquete turistico, el cual se vende en
 * el marketplace.
 * 
 * @author Alejo
 * 
 */
public class Producto implements Serializable {

	private static final long serialVersionUID = -8658863654377821942L;

	private Long id;
	private String nombre;
	private String lugar;
	private String ciudad;
	private Double precio;
	private String fechaInicio;
	private String urlImagen;
	private List<CalificacionDTO> calificaciones;

	public String getPrecioConFormato() {
		return CurrencyUtilidades.formatoDinero(precio,
				CurrencyUtilidades.LENGUAJE_INGLES,
				CurrencyUtilidades.CODIGO_COLOMBIA);
	}

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

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public List<CalificacionDTO> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<CalificacionDTO> calificaciones) {
		this.calificaciones = calificaciones;
	}

	

}
