package grupo6.marketplace.ecoturismo.modelo.sql.tables;

import android.database.sqlite.SQLiteDatabase;

public class CalificacionTable {

	public static final String NOMBRE_TABLA = "Calificacion";
	public static final String ID_CALIFICACION = "idCalificacion";
	public static final String ID_PRODUCTO = "idProducto";
	public static final String NOMBRE_CALIFICACION = "nombreCalificacion";
	public static final String PUNTUACION_CALIFICACION = "puntuacion";
	public static final String CANTIDAD_VOTANTES_CALIFICACION = "cantidadVotantes";


	public static final String CREATE_TABLE = "CREATE TABLE "
			+ NOMBRE_TABLA
			+ " ( "
			+ ID_CALIFICACION
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ NOMBRE_CALIFICACION
			+ " TEXT NOT NULL, "
			+ PUNTUACION_CALIFICACION
			+ " REAL NOT NULL, "
			+ CANTIDAD_VOTANTES_CALIFICACION
			+ " INTEGER NOT NULL, "
			+ ID_PRODUCTO
			+ " INTEGER NOT NULL, "
			+ " FOREIGN KEY ( "+ID_PRODUCTO+ " ) REFERENCES "+ProductoTable.NOMBRE_TABLA+" ( "+ProductoTable.ID_PRODUCTO+" )); ";

	public static void onCreate(SQLiteDatabase database) {
		android.util.Log.d("Calificacion ",CREATE_TABLE);
		database.execSQL(CREATE_TABLE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,int newVersion) {
	}
	
	
}
