package grupo6.modulo.utilidades;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que se encarga de leer el archivo .config de feature IDE 
 * e indica si un caracteristica esta habilitada o no en el producto
 * @author alejo
 *
 */
public class Variability {

	private static final String PRODUCT_CONFIG = "product.config";
	
	private static Set<String> featuresSet;

	static {
		featuresSet = new HashSet<String>();
	}
	
	public static boolean isEnable(String feature){
		if(featuresSet.isEmpty()){
			BufferedReader bufferedReader = null;
			
			try {
				String currentFeature;
				InputStream inputStream = Variability.class.getClassLoader().getResourceAsStream(PRODUCT_CONFIG);
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	 
				while ((currentFeature = bufferedReader.readLine()) != null) {
					featuresSet.add(currentFeature);
				}
	 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeBufferReader(bufferedReader);
			}	
		}
		
		return featuresSet.contains(feature);
	}

	private static void closeBufferReader(BufferedReader bufferedReader) {
		try {
			if (bufferedReader != null){
				bufferedReader.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
