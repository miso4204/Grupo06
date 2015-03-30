package grupo6.marketplace.ecoturismo.application;

import java.util.ArrayList;
import java.util.List;

import grupo6.marketplace.ecoturismo.modelo.Categoria;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.modelo.sql.EcoturismoSqlHelper;
import grupo6.marketplace.ecoturismo.modelo.sql.tables.CategoriaTable;
import grupo6.marketplace.ecoturismo.modelo.sql.tables.ProductoTable;
import android.app.Application;
/**
 * Clase que se encarga de encapsular la Aplicación 
 * @author Alejo
 *
 */

public class EcoturismoApplication extends Application{

	private EcoturismoSqlHelper ecoturismoSqlHelper;
	
	public EcoturismoSqlHelper getEcoturismoSqlHelper() {
		if(ecoturismoSqlHelper == null){
			ecoturismoSqlHelper = new EcoturismoSqlHelper(this);
		}
		return ecoturismoSqlHelper;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		ecoturismoSqlHelper = new EcoturismoSqlHelper(this);
		cargarCategorias();
		cargarProductos();
	}
	
	private void cargarCategorias() {
		List<Categoria> categorias =  new ArrayList<Categoria>();
		categorias.add(new Categoria("Alojamiento"));
		categorias.add(new Categoria("Restaurantes"));
		categorias.add(new Categoria("Atracciones"));
		CategoriaTable.addCategorias(getEcoturismoSqlHelper(), categorias );
	}

	private void cargarProductos() {
		List<Producto> productos = new ArrayList<Producto>();
		productos.add(new Producto("Alojamiento","Hotel Campestre", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "San Gil", "25 Marzo 2015", 45000));
		productos.add(new Producto("Atracciones","Cabaña", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Barichara", "25 Marzo 2015", 185000));
		productos.add(new Producto("Restaurantes","Restuarante Donde Juancho", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Neiva", "25 Marzo 2015", 85000));
		ProductoTable.addProductos(getEcoturismoSqlHelper(), productos );
	}
	
	
}
