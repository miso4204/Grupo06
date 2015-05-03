package grupo6.generador;

import grupo6.modulo.utilidades.FeaturesNames;
import grupo6.modulo.utilidades.Variability;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Lee el archivo de variabilidad y construye y ejecuta el comando mvn con los
 * parametros necesarios para activar los perfiles Maven y Spring que manejan
 * las caracteristicas variables.
 * 
 * @author caespinosam
 *
 */
public class GeneradorVariabilidad {

	/**
	 * Construye el comando de sistema operativo
	 * para ejecutar Maven con los respectivos perfiles.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("----> Iniciando generador....");
		cargarFeatures();
		StringBuilder comandoMvn = new StringBuilder(
				"mvn clean install tomcat7:run ");
		StringBuilder perfilesMvn = new StringBuilder("-Pdesarrollo");

		boolean conCache = Variability.isEnable(FeaturesNames.PERFORMANCE);
		System.out.println("con cache: " + conCache);
		if (conCache) {
			perfilesMvn.append(",con_cache");
		} else {
			perfilesMvn.append(",sin_cache");
		}
		comandoMvn.append(perfilesMvn);

		// abre una nueva ventana de consola (Windows)
		String comandoSO = "cmd.exe /c start cmd.exe /K \"cd..  && cd web-app && "
				+ comandoMvn.toString() + "\"";				
		System.out.println("comando a ejecutar: " + comandoMvn);
		Process p = Runtime.getRuntime().exec(comandoSO);		
		p.waitFor();
		System.out.println("----> Fin generador.");
	}

	/**
	 * Carga en memoria el archivo de configuracion de variabilidad del proyecto.
	 */
	public static void cargarFeatures() throws Exception {

		Set<String> featuresSet = new HashSet<String>();

		BufferedReader bufferedReader = null;
		try {
			String currentFeature;
			Path p = Paths.get("../web-app/src/main/resources/product.config");
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

		Variability.setFeaturesSet(featuresSet);
	}

}
