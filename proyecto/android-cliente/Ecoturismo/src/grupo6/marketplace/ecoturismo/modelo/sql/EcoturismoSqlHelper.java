package grupo6.marketplace.ecoturismo.modelo.sql;

import grupo6.marketplace.ecoturismo.modelo.sql.tables.CarritoComprasTable;
import grupo6.marketplace.ecoturismo.modelo.sql.tables.ProductoTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EcoturismoSqlHelper extends SQLiteOpenHelper {

	  private static final String DATABASE_NAME = "ecomarket.db";
	  private static final int DATABASE_VERSION = 1;

	  public EcoturismoSqlHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    ProductoTable.onCreate(database);
	    CarritoComprasTable.onCreate(database);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase database, int oldVersion,int newVersion) {
		  ProductoTable.onUpgrade(database, oldVersion, newVersion);
		  CarritoComprasTable.onUpgrade(database, oldVersion, newVersion);
	  }
}
	 
