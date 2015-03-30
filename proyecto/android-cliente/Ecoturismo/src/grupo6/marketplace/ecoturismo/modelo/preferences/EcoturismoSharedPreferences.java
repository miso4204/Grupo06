package grupo6.marketplace.ecoturismo.modelo.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class EcoturismoSharedPreferences {
	
	private static final String NOMBRE_ARCHIVO = "EcoturismoSharedFile";
	
	private SharedPreferences sharedPreferences;

	public EcoturismoSharedPreferences(Context contexto) {
		sharedPreferences = contexto.getSharedPreferences(NOMBRE_ARCHIVO, Context.MODE_PRIVATE);
	}
	
}
