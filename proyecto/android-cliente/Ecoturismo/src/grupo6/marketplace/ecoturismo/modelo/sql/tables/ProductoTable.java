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

	public static final String NOMBRE_CATEGORIA = "nombreCategoria";

	public static final String NOMBRE_PRODUCTO = "nombreProducto";

	public static final String DESCRIPCION_PRODUCTO = "descripcionProducto";

	public static final String CIUDAD_PRODUCTO = "ciudadProducto";

	public static final String FECHA_PRODUCTO = "fechaProducto";

	public static final String PRECIO_PRODUCTO = "precioProducto";

	public static final String CREATE_TABLE = "CREATE TABLE "
			+ NOMBRE_TABLA
			+ " ( "
			+ ID_PRODUCTO
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ NOMBRE_CATEGORIA
			+ " TEXT NOT NULL, "
			+ NOMBRE_PRODUCTO
			+ " TEXT NOT NULL, "
			+ DESCRIPCION_PRODUCTO
			+ " TEXT NOT NULL, "
			+ CIUDAD_PRODUCTO
			+ " TEXT NOT NULL, "
			+ FECHA_PRODUCTO
			+ " TEXT NOT NULL, "
			+ PRECIO_PRODUCTO
			+ " REAL NOT NULL,FOREIGN KEY("
			+ NOMBRE_CATEGORIA
			+ ") REFERENCES Categoria("
			+ NOMBRE_CATEGORIA
			+ "));";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,int newVersion) {
	}
	
	public static void addProductos(EcoturismoSqlHelper ecoturismoSqlHelper,List<Producto> productos){
		
		SQLiteDatabase database = ecoturismoSqlHelper.getWritableDatabase();
		for(Producto p: productos){
			ContentValues values = new ContentValues();
			values.put(NOMBRE_CATEGORIA,p.getCategoria());
			values.put(NOMBRE_PRODUCTO,p.getNombre());
			values.put(DESCRIPCION_PRODUCTO,p.getDescripcion());
			values.put(CIUDAD_PRODUCTO,p.getCiudad());
			values.put(FECHA_PRODUCTO,p.getFecha());
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
	    p.setId(cursor.getLong(cursor.getColumnIndex(ID_PRODUCTO)));
	    p.setCategoria(cursor.getString(cursor.getColumnIndex(NOMBRE_CATEGORIA)));
	    p.setNombre(cursor.getString(cursor.getColumnIndex(NOMBRE_PRODUCTO)));
	    p.setDescripcion(cursor.getString(cursor.getColumnIndex(DESCRIPCION_PRODUCTO)));
	    p.setCiudad(cursor.getString(cursor.getColumnIndex(CIUDAD_PRODUCTO)));
	    p.setFecha(cursor.getString(cursor.getColumnIndex(FECHA_PRODUCTO)));
	    p.setPrecio(cursor.getDouble(cursor.getColumnIndex(PRECIO_PRODUCTO)));
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
