package grupo6.marketplace.ecoturismo.modelo;

import grupo6.marketplace.ecoturismo.util.CurrencyUtilidades;

public class Producto {

	private String nombre;
	private String descripcion;
	private String ciudad;
	private String fecha;
	private double precio;
	
	public Producto(String nombre, String descripcion, String ciudad,String fecha, double precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.fecha = fecha;
		this.precio = precio;
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

	public String getPrecio() {
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
