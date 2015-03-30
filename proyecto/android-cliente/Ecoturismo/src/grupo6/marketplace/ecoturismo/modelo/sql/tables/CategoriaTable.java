package grupo6.marketplace.ecoturismo.modelo.sql.tables;

import grupo6.marketplace.ecoturismo.modelo.Categoria;
import grupo6.marketplace.ecoturismo.modelo.sql.EcoturismoSqlHelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CategoriaTable {

	private static final String NOMBRE_CATEGORIA = "nombreCategoria";

	private static final String NOMBRE_TABLA = "Categoria";

	private static final String CREATE_TABLE = "CREATE TABLE "+NOMBRE_TABLA+" ( "
			+ NOMBRE_CATEGORIA
			+ " TEXT PRIMARY KEY  );";
	
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
	}
	
	public static void addCategorias(EcoturismoSqlHelper ecoturismoSqlHelper,List<Categoria> categorias){
		
		SQLiteDatabase database = ecoturismoSqlHelper.getWritableDatabase();
		for(Categoria c: categorias){
			ContentValues values = new ContentValues();
		    values.put(NOMBRE_CATEGORIA, c.getNombreCategoria());
		    database.insert(NOMBRE_TABLA, null,values);
		}
		
		database.close();
	}
	
	public static List<Categoria> getCategorias(EcoturismoSqlHelper ecoturismoSqlHelper){
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		SQLiteDatabase database = ecoturismoSqlHelper.getWritableDatabase();
		
		Cursor cursor = database.rawQuery("SELECT * FROM "+NOMBRE_TABLA, null);
		
		cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Categoria c = cursorToCategoria(cursor);
	      categorias.add(c);
	      cursor.moveToNext();
	    }

	    cursor.close();
		
		database.close();
		
		return categorias;
	}
	
	private static Categoria cursorToCategoria(Cursor cursor) {
	    Categoria c = new Categoria();
	    c.setNombreCategoria(cursor.getString(cursor.getColumnIndex(NOMBRE_CATEGORIA)));
	    return c;
	}
	
	public static boolean hayCategoriasCargadas(EcoturismoSqlHelper ecoturismoSqlHelper){
		
		boolean hayCategorias = false;
		
		SQLiteDatabase database = ecoturismoSqlHelper.getWritableDatabase();
		
		Cursor cursor = database.rawQuery("SELECT * FROM "+NOMBRE_TABLA, null);
		
		hayCategorias = cursor.getCount() > 0;

	    cursor.close();
		
		database.close();
		
		return hayCategorias;
	}
}
