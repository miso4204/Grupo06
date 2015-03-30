package grupo6.marketplace.ecoturismo.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Archivo local que almacena datos locales de la aplicación.
 * @author Alejo
 *
 */
public class EcoturismoLocalData {

	private SharedPreferences sharedPreferences;
	protected static final String TAG = "EcoturismoLocalData";
	protected static final String NOMBRE_ARCHIVO = "EcoturismoLocalData";
	
	//Carrito compras
	protected static final String CARRITO_COMPRAS_KEY = "Carrito_Compras_Ecoturismo";
	
	public EcoturismoLocalData(Context contexto) {
		sharedPreferences = contexto.getSharedPreferences(NOMBRE_ARCHIVO, Context.MODE_PRIVATE);
	}
	
	public Set<String> getCarritoComprasJson(){
		return sharedPreferences.getStringSet(CARRITO_COMPRAS_KEY, new HashSet<String>());
	}
	
	public List<Producto> getCarritoCompras(){
		Set<String> carritoCompras = getCarritoComprasJson();
		List<Producto> productos = new ArrayList<Producto>();
		for(String json: carritoCompras){
			Producto p = Producto.jsonFormatToProducto(json);
			productos.add(p);
		}
		return productos;
	}
	
	public boolean agregarProductoACarrito(String jsonProducto){
		Set<String> carritoCompras = getCarritoComprasJson();
		
		if(!carritoCompras.contains(jsonProducto)){
			
			carritoCompras.add(jsonProducto);
			
			SharedPreferences.Editor editor = sharedPreferences.edit();
			synchronized (editor) {
				editor.putStringSet(CARRITO_COMPRAS_KEY,carritoCompras);
				return editor.commit();
			}
		}
		return false;
	}
	
	public boolean removerProductoDeCarrito(String jsonProducto){
		Set<String> carritoCompras = getCarritoComprasJson();
		if(carritoCompras != null && carritoCompras.size() > 0 ){
			carritoCompras.remove(jsonProducto);
			
			SharedPreferences.Editor editor = sharedPreferences.edit();
			synchronized (editor) {
				editor.putStringSet(CARRITO_COMPRAS_KEY,carritoCompras);
				return editor.commit();	
			}
		}
		return false;
	}
	
}
