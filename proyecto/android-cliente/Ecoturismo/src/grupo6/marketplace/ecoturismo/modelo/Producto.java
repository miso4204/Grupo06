package grupo6.marketplace.ecoturismo.modelo;

import grupo6.marketplace.ecoturismo.util.CurrencyUtilidades;

/**
 * Clase del modelo que representa un producto del marketplace de ecoturismo  
 * @author Alejo
 *
 */
public class Producto {

	private long id;
	private String categoria;
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String fecha;
	private double precio;
	
	public Producto() {
		
	}
	
	public Producto(String categoria,String nombre, String descripcion, String ciudad,String fecha, double precio) {
		this.categoria = categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.fecha = fecha;
		this.precio = precio;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public double getPrecio() {
		return precio;
	}
	
	public String getPrecioConFormato() {
		return CurrencyUtilidades.formatoDinero(precio, 
												CurrencyUtilidades.LENGUAJE_ESPANOL, 
												CurrencyUtilidades.CODIGO_COLOMBIA
												);
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
