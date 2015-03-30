package grupo6.marketplace.ecoturismo.modelo.sql.tables;

import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.modelo.sql.EcoturismoSqlHelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CarritoComprasTable {

	private static final String NOMBRE_TABLA = "CarritoCompras";
	private static final String ID_PRODUCTO = "idProducto";
	private static final String CREATE_TABLE = "CREATE TABLE " + NOMBRE_TABLA
			+ " ( " + ID_PRODUCTO + " INTEGER PRIMARY KEY  );";

	
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
	}
	
	public static void agregarACarrito(EcoturismoSqlHelper ecoturismoSqlHelper,Producto producto){
		
		SQLiteDatabase database = ecoturismoSqlHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
	    values.put(ID_PRODUCTO, producto.getId());
	    
	    database.insert(NOMBRE_TABLA, null,values);
		
		database.close();
	}
	
	public static void removerDeCarrito(EcoturismoSqlHelper ecoturismoSqlHelper,Producto producto){
		
		SQLiteDatabase database = ecoturismoSqlHelper.getWritableDatabase();
		
	    database.delete(NOMBRE_TABLA, ID_PRODUCTO +"="+ producto.getId(), null);
		
		database.close();
	}
	
	public static List<Producto> getCarritoCompras(EcoturismoSqlHelper ecoturismoSqlHelper){
		
		List<Producto> productos = new ArrayList<Producto>();
		
		SQLiteDatabase database = ecoturismoSqlHelper.getWritableDatabase();
		
		//Get carrito compras list ids
		Cursor cursorCarritoIds = database.rawQuery("SELECT * FROM "+NOMBRE_TABLA,null);
		
		cursorCarritoIds.moveToFirst();
	    while (!cursorCarritoIds.isAfterLast()) {
	      long idProducto = cursorCarritoIds.getLong(cursorCarritoIds.getColumnIndex(ID_PRODUCTO));
	      
	      Cursor cursorProducto = database.rawQuery("SELECT * FROM "+ProductoTable.NOMBRE_TABLA+" WHERE "+ProductoTable.ID_PRODUCTO+" = "+idProducto, null);
	      cursorProducto.moveToFirst();
	      
	      productos.add(ProductoTable.cursorToProducto(cursorProducto));
	      
	      cursorProducto.close();
	      
	      cursorCarritoIds.moveToNext();
	    }

	    cursorCarritoIds.close();
		
		database.close();
		
		return productos;
	}
}
