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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	
	private static final ExecutorService executor = Executors.newFixedThreadPool(2);

	/**
	 * Construye el comando de sistema operativo
	 * para ejecutar Maven con los respectivos perfiles.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("----> Iniciando generador....");
		cargarFeatures();
		
		StringBuilder comandoMvnTomcat = new StringBuilder(
				"mvn clean install tomcat7:run ");
		StringBuilder perfilesMvn = new StringBuilder("-Pdesarrollo");

		boolean conCache = Variability.isEnable(FeaturesNames.PERFORMANCE);
		System.out.println("con cache: " + conCache);
		if (conCache) {
			perfilesMvn.append(",con_cache");
		} else {
			perfilesMvn.append(",sin_cache");
		}
		
		boolean conEscalabilidad = Variability.isEnable(FeaturesNames.SCALABILITY);
		System.out.println("con escalabilidad: " + conEscalabilidad);
		if (conEscalabilidad) {
			perfilesMvn.append(" -Dspring.profiles.active=pago_asincrono");
		} else {
			perfilesMvn.append(" -Dspring.profiles.active=pago_sincrono");
		}
		comandoMvnTomcat.append(perfilesMvn);
		
		// abre una nueva ventana de consola (Windows)
		final String comandoSO = "cmd.exe /c start cmd.exe /K \"cd..  && cd web-app && "
				+ comandoMvnTomcat.toString() + "\"";				
		System.out.println("comando a ejecutar: " + comandoMvnTomcat);
		
		
		// lanza ActiveMQ para JMS
		if (conEscalabilidad) {
			executor.execute(new Runnable() {
			    public void run() {
			    	Process p;
					try {
						p = Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /K \"cd..  && cd web-app && "
								+ " mvn clean activemq:run " + "\"");
						p.waitFor();
					} catch (Exception e) {					
						e.printStackTrace();
					}		
					
			    }
			});
			Thread.sleep(10000); // da tiempo de espera hasta que suba la cola jms antes de subir Tomcat
		}
		
		// lanza Tomcat
		executor.execute(new Runnable() {
		    public void run() {
		    	Process p;
				try {
					p = Runtime.getRuntime().exec(comandoSO);
					p.waitFor();
				} catch (Exception e) {					
					e.printStackTrace();
				}		
				
		    }
		});
		
		
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
