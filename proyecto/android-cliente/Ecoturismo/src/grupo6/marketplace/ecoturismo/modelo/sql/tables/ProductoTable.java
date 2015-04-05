package grupo6.marketplace.ecoturismo.modelo.sql.tables;

import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.modelo.sql.EcoturismoSqlHelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProductoTable {

	public static final String NOMBRE_TABLA = "Producto";
	public static final String ID_PRODUCTO = "idProducto";
	public static final String ID_PRODUCTO_SERVER = "idProductoServer";
	public static final String NOMBRE_PRODUCTO = "nombreProducto";
	public static final String LUGAR_PRODUCTO = "lugarProducto";
	public static final String CIUDAD_PRODUCTO = "ciudadProducto";
	public static final String FECHA_INICIO_PRODUCTO = "fechaInicio";
	public static final String URL_IMAGEN_PRODUCTO = "urlProducto";
	public static final String PRECIO_PRODUCTO = "precioProducto";


	public static final String CREATE_TABLE = "CREATE TABLE "
			+ NOMBRE_TABLA
			+ " ( "
			+ ID_PRODUCTO
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ ID_PRODUCTO_SERVER
			+ " INTEGER NOT NULL, "
			+ NOMBRE_PRODUCTO
			+ " TEXT NOT NULL, "
			+ LUGAR_PRODUCTO
			+ " TEXT NOT NULL, "
			+ CIUDAD_PRODUCTO
			+ " TEXT NOT NULL, "
			+ FECHA_INICIO_PRODUCTO
			+ " TEXT NOT NULL, "
			+ URL_IMAGEN_PRODUCTO
			+ " TEXT NOT NULL, "
			+ PRECIO_PRODUCTO
			+ " REAL NOT NULL);";

	public static void onCreate(SQLiteDatabase database) {
		android.util.Log.d("Producto ",CREATE_TABLE);
		database.execSQL(CREATE_TABLE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,int newVersion) {
	}
	
	public static void addProductos(EcoturismoSqlHelper ecoturismoSqlHelper,List<Producto> productos){
		
		SQLiteDatabase database = ecoturismoSqlHelper.getWritableDatabase();
		for(Producto p: productos){
			ContentValues values = new ContentValues();
			values.put(ID_PRODUCTO_SERVER,p.getId());
			values.put(NOMBRE_PRODUCTO,p.getNombre());
			values.put(LUGAR_PRODUCTO,p.getLugar());
			values.put(CIUDAD_PRODUCTO,p.getCiudad());
			values.put(FECHA_INICIO_PRODUCTO,p.getFechaInicio());
			values.put(URL_IMAGEN_PRODUCTO,p.getUrlImagen());
			values.put(PRECIO_PRODUCTO,p.getPrecio());
		    
		    database.insert(NOMBRE_TABLA, null,values);
		}
		
		database.close();
	}
	
	public static List<Producto> getProductos(EcoturismoSqlHelper ecoturismoSqlHelper){
		
		List<Producto> productos = new ArrayList<Producto>();
		
		SQLiteDatabase database = ecoturismoSqlHelper.getWritableDatabase();
		
		Cursor cursor = database.rawQuery("SELECT * FROM "+NOMBRE_TABLA, null);
		
		cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Producto p = cursorToProducto(cursor);
	      productos.add(p);
	      cursor.moveToNext();
	    }

	    cursor.close();
		
		database.close();
		
		return productos;
	}
	
	public static Producto cursorToProducto(Cursor cursor) {
	    Producto p = new Producto();
	    p.setId(cursor.getLong(cursor.getColumnIndex(ID_PRODUCTO_SERVER)));
	    p.setNombre(cursor.getString(cursor.getColumnIndex(NOMBRE_PRODUCTO)));
	    p.setLugar(cursor.getString(cursor.getColumnIndex(LUGAR_PRODUCTO)));
	    p.setCiudad(cursor.getString(cursor.getColumnIndex(CIUDAD_PRODUCTO)));
	    p.setPrecio(cursor.getDouble(cursor.getColumnIndex(PRECIO_PRODUCTO)));
	    p.setFechaInicio(cursor.getString(cursor.getColumnIndex(FECHA_INICIO_PRODUCTO)));
	    p.setUrlImagen(cursor.getString(cursor.getColumnIndex(URL_IMAGEN_PRODUCTO)));
		
	    return p;
	}
	
	public static boolean hayProductosCargados(EcoturismoSqlHelper ecoturismoSqlHelper){
		
		boolean hayProductos = false;
		
		SQLiteDatabase database = ecoturismoSqlHelper.getWritableDatabase();
		
		Cursor cursor = database.rawQuery("SELECT * FROM "+NOMBRE_TABLA, null);
		
		hayProductos = cursor.getCount() > 0;

	    cursor.close();
		
		database.close();
		
		return hayProductos;
	}
	
}
