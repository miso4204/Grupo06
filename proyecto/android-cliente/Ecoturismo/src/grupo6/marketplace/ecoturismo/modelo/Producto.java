package grupo6.marketplace.ecoturismo.modelo;

import org.json.JSONObject;

import grupo6.marketplace.ecoturismo.util.CurrencyUtilidades;

/**
 * Clase del modelo que representa un producto del marketplace de ecoturismo  
 * @author Alejo
 *
 */
public class Producto {

	private static final String NOMBRE = "nombre";
	private static final String DESCRIPCION = "descripcion";
	private static final String CIUDAD = "ciudad";
	private static final String FECHA = "fecha";
	private static final String PRECIO = "precio";
	
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
	
	public static String productoToJsonFormat(Producto producto){
		String jsonResult = null;
		try{
			JSONObject json = new JSONObject();
			json.put(NOMBRE, producto.getNombre());
			json.put(DESCRIPCION, producto.getDescripcion());
			json.put(CIUDAD, producto.getCiudad());
			json.put(FECHA, producto.getFecha());
			json.put(PRECIO, producto.getPrecio());
			
			jsonResult = json.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	public static Producto jsonFormatToProducto(String jsonFormat){
		Producto producto = null;
		try{
			JSONObject json = new JSONObject(jsonFormat);
			String nombre = json.getString(NOMBRE);
			String descripcion = json.getString(DESCRIPCION);
			String ciudad = json.getString(CIUDAD);
			String fecha = json.getString(FECHA);
			double precio = json.getDouble(PRECIO);
			
			producto = new Producto(nombre, descripcion, ciudad, fecha, precio);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return producto;
	}
	
}
