package grupo6.ejemplo.generadorcodigo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Procesador de plantillas Java -> Java con Velocity.
 * 
 * @author caespinosam
 *
 */
public class ProcesadorCodigo {

	/** Plantillas a procesar. */
	private static Set<String> plantillas;

	static {
		plantillas = new HashSet<String>();
		plantillas.add("BusquedasRestController.java");
		plantillas.add("OfertasRestController.java");
	}

	public static void main(String... s) throws Exception {
		System.out.println("Generando codigo");
		try {

			Properties propiedades = new Properties();
			InputStream inputProperties = ProcesadorCodigo.class.getClassLoader()
					.getResourceAsStream("variabilidad.properties");			
			propiedades.load(inputProperties);
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.init();
			VelocityContext context = new VelocityContext(propiedades);
			System.out.println("properties: " + inputProperties);
			System.out.println("moduloOfertas_crearOferta: " + propiedades.getProperty("moduloOfertas_crearOferta"));
			
			for (String plantilla : plantillas) {
				
				InputStream input = ProcesadorCodigo.class.getClassLoader()
						.getResourceAsStream(plantilla);
				if (input == null) {
					throw new IOException("Template " + plantilla
							+ " no existe");
				}

				InputStreamReader reader = new InputStreamReader(input);
				File generado = new File(
						"./src/main/java/grupo6/ejemplo/web/controller/rest/" + plantilla);

				BufferedWriter out = new BufferedWriter(new FileWriter(
						generado, false));

				if (!velocityEngine.evaluate(context, out, plantilla, reader)) {
					throw new Exception("Error al procesar plantilla "
							+ plantilla);
				}

				out.flush();
				out.close();
			}
			
			String plantilla = "index.jsp";
			InputStream input = ProcesadorCodigo.class.getClassLoader()
					.getResourceAsStream(plantilla);
			if (input == null) {
				throw new IOException("Template " + plantilla
						+ " no existe");
			}

			InputStreamReader reader = new InputStreamReader(input);
			File generado = new File(
					"./src/main/webapp/WEB-INF/pages/" + plantilla);

			BufferedWriter out = new BufferedWriter(new FileWriter(
					generado, false));

			if (!velocityEngine.evaluate(context, out, plantilla, reader)) {
				throw new Exception("Error al procesar plantilla "
						+ plantilla);
			}

			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
