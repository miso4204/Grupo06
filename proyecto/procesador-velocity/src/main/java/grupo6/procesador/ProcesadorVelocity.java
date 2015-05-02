package grupo6.procesador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Procesador de plantillas Java -> Java con Velocity.
 * Ejcutar desde parent-build.
 * 
 * @author caespinosam
 *
 */
public class ProcesadorVelocity {

	/** Plantillas de controladores a procesar. */
	private static Set<String> controladores;
	/** Plantillas html  a procesar del cliente. */
	private static Set<String> plantillasWebClient;
	/** Plantillas html del cliente a procesar del proveedor. */
	private static Set<String> plantillasWebProvider;

	static {
		controladores = new HashSet<String>();
		controladores.add("velocity/controladores/ProductoRestController.java");		
		
		plantillasWebClient = new HashSet<String>();
		plantillasWebClient.add("velocity/paginas/client/destinationDetails.jsp");		
		plantillasWebClient.add("velocity/paginas/client/indexUser.jsp");		
		plantillasWebClient.add("velocity/paginas/client/user_profile.jsp");	
		
		plantillasWebProvider = new HashSet<String>();
		plantillasWebProvider.add("velocity/paginas/provider/indexProvider.jsp");		
	}

	public static void main(String... s) throws Exception {
		System.out.println("Generando codigo, Ejecutando desde: " + (new File(".")).getAbsolutePath() );
	
		try {

			Properties propiedades = new Properties();
			InputStream inputProperties = ProcesadorVelocity.class.getClassLoader()
					.getResourceAsStream("velocity/variabilidad.properties");			
			propiedades.load(inputProperties);
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.init();
			VelocityContext context = new VelocityContext(propiedades);
					
			for (String plantilla : controladores) {
				
				InputStream input = ProcesadorVelocity.class.getClassLoader()
						.getResourceAsStream(plantilla);
				if (input == null) {
					throw new IOException("Template " + plantilla
							+ " no existe");
				}
								
				InputStreamReader reader = new InputStreamReader(input);
				String nombrePlantilla = plantilla.split("/")[2];
				Path p = Paths.get("../web-app/src/main/java/grupo6/web/controller/rest/"  + nombrePlantilla);
				BufferedWriter out = Files.newBufferedWriter(p, Charset.defaultCharset());

				if (!velocityEngine.evaluate(context, out, plantilla, reader)) {
					throw new Exception("Error al procesar plantilla "
							+ plantilla);
				}

				out.flush();
				out.close();
			}
			
			procesarPlantillasWeb("client", context, velocityEngine, plantillasWebClient);
			procesarPlantillasWeb("provider", context, velocityEngine, plantillasWebProvider);

		} catch (Exception e) {
			System.out.println("--------> ERROR CONTROLADO, PERO NO SE EJECUTA VELOCITY: ");
			e.printStackTrace();			
		}
	}
	
	
	/**
	 * Procesar las plantillas web.
	 * 
	 * @param subcarpeta subcarpeta del usuario: client, provider, admin
	 * @param context contexto de velocity
	 * @param velocityEngine engine de velocity
	 * @throws Exception error al procesar plantillas
	 */
	private static void procesarPlantillasWeb(String subcarpeta,
			VelocityContext context, VelocityEngine velocityEngine,
			Set<String> plantillasWeb) throws Exception {

		for (String plantilla : plantillasWeb) {
			InputStream input = ProcesadorVelocity.class.getClassLoader()
					.getResourceAsStream(plantilla);
			if (input == null) {
				throw new IOException("Template " + plantilla
						+ " no existe");
			}

			InputStreamReader reader = new InputStreamReader(input);
			String nombrePlantilla = plantilla.split("/")[3];
			Path p = Paths.get("../web-app/src/main/webapp/WEB-INF/pages/" 
						+ subcarpeta + "/" + nombrePlantilla);
			BufferedWriter out = Files.newBufferedWriter(p, Charset.defaultCharset());
			
			if (!velocityEngine.evaluate(context, out, plantilla, reader)) {
				throw new Exception("Error al procesar plantilla "
						+ plantilla);
			}

			out.flush();
			out.close();
		}
	}

}
