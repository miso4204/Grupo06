package grupo6.marketplace.ecoturismo.application;

import java.util.ArrayList;
import java.util.List;

import grupo6.marketplace.ecoturismo.modelo.CalificacionDTO;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.modelo.Usuario;
import grupo6.marketplace.ecoturismo.modelo.enums.RolType;
import grupo6.marketplace.ecoturismo.modelo.sql.EcoturismoSqlHelper;
import grupo6.marketplace.ecoturismo.modelo.sql.tables.ProductoTable;
import android.app.Application;
/**
 * Clase que se encarga de encapsular la Aplicacion 
 * @author Alejo
 *
 */

public class EcoturismoApplication extends Application{

	private EcoturismoSqlHelper ecoturismoSqlHelper;
	private Usuario usuario;
	
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
		cargarProductos();
	}
	
	private void cargarProductos() {
		if(!ProductoTable.hayProductosCargados(getEcoturismoSqlHelper())){
			List<CalificacionDTO> calificaciones = new ArrayList<CalificacionDTO>(); 
			calificaciones.add(new CalificacionDTO("GENERAL", 4.5, 370));
			calificaciones.add(new CalificacionDTO("UBICACION", 5.0, 340));
			calificaciones.add(new CalificacionDTO("ATENCION", 4.0, 500));
			
			List<Producto> productos = new ArrayList<Producto>();
			Producto p1 = new Producto();
			p1.setId(1L);
			p1.setNombre("2 dias + comidas");
			p1.setLugar("Hotel");
			p1.setCiudad("Armenia");
			p1.setPrecio(50000D);
			p1.setFechaInicio("2015-05-23");
			p1.setUrlImagen("http://www.integralocal.es/upload/Image/Actividades/2012/Junio%2012/CampamentoCantabria_12.jpg");
			p1.setCalificaciones(calificaciones);
			productos.add(p1);
			
			Producto p2 = new Producto();
			p2.setId(2L);
			p2.setNombre("3 dias + comidas");
			p2.setLugar("Finca");
			p2.setCiudad("Pereira");
			p2.setPrecio(45000D);
			p2.setFechaInicio("2015-03-13");
			p2.setUrlImagen("http://www.integralocal.es/upload/Image/Actividades/2012/Junio%2012/CampamentoCantabria_12.jpg");
			p2.setCalificaciones(calificaciones);
			productos.add(p2);
			
			Producto p3 = new Producto();
			p3.setId(3L);
			p3.setNombre("1 Noche");
			p3.setLugar("Casa");
			p3.setCiudad("Manizales");
			p3.setPrecio(35000D);
			p3.setFechaInicio("2015-07-13");
			p3.setUrlImagen("http://www.integralocal.es/upload/Image/Actividades/2012/Junio%2012/CampamentoCantabria_12.jpg");
			p3.setCalificaciones(calificaciones);
			productos.add(p3);
			
			ProductoTable.addProductos(getEcoturismoSqlHelper(), productos );	
		}
		
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public RolType getRol() {
		return usuario.getRol();
	}
}
