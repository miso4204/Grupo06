package grupo6.modulo.utilidades;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que se encarga de leer el archivo .config de feature IDE 
 * e indica si un caracteristica esta habilitada o no en el producto
 * @author alejo
 *
 */
public class Variability {

	
	
	private static Set<String> featuresSet;

	static {
		featuresSet = new HashSet<String>();
	}
	
	public static boolean isEnable(String feature){
		if(featuresSet.isEmpty()){
			try {
				cargarFeatures();
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		return featuresSet.contains(feature);
	}
	
	public static void cargarFeatures() throws Exception {

		Set<String> featuresSet = new HashSet<String>();

		BufferedReader bufferedReader = null;
		try {
			String currentFeature;
			Path p = Paths.get("../../EcoturismoFeatureIDE/configs/Ecoturismo.config");
			bufferedReader = Files.newBufferedReader(p,
					Charset.defaultCharset());
			while ((currentFeature = bufferedReader.readLine()) != null) {
				featuresSet.add(currentFeature.trim());
			}

		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		setFeaturesSet(featuresSet);
	}

	public static Set<String> getProperties(){
		return featuresSet;
	}
	
	
	
	public static void setFeaturesSet(Set<String> features) {
		featuresSet = features;
	}

}
