package grupo6.modulo.utilidades;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Clase que se encarga de leer el archivo .config de feature IDE 
 * e indica si un caracteristica esta habilitada o no en el producto
 * @author alejo
 *
 */
public class Variability {

	private static final String PRODUCT_CONFIG = "src/main/resources/product.config";
	
	public static boolean isEnable(String feature){
		BufferedReader bufferedReader = null;
		boolean isEnable = false;
		try {
			String currentFeature;
 
			bufferedReader = new BufferedReader(new FileReader(PRODUCT_CONFIG));
 
			while ((currentFeature = bufferedReader.readLine()) != null) {
				if(feature.equalsIgnoreCase(currentFeature)){
					isEnable = true;
					break;
				}
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeBufferReader(bufferedReader);
		}
		return isEnable;
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
