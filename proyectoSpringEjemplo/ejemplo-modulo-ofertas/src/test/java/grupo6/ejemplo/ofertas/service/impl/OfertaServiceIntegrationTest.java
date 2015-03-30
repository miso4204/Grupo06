package grupo6.ejemplo.ofertas.service.impl;

import grupo6.ejemplo.ofertas.service.view.IOfertaService;
import grupo6.ejemplo.persistencia.entidades.Oferta;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Prueba de integracion del servicio {@link OfertaService}. 
 * Se usa una BD en memoria mediante hsqldb.
 * 
 * @author caespinosam
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:test-spring-beans.xml") // Crea los beans y los pone en el contexto de Spring
public class OfertaServiceIntegrationTest {

	/** El servicio a probar. **/
	@Autowired // asigna a este atributo el bean de tipo IOfertaService y que
			   // se llama 'ofertaService' en el contexto de Spring. 
	private IOfertaService ofertaService;
	

	/**
	 * Prueba la creación de ofertas.
	 * 
	 * @throws Exception
	 *             cualquier error.
	 */
	@Test
	public void crearOfertaTest() throws Exception {
		Oferta oferta = new Oferta();
		oferta.setCiudad("Bogota");
		oferta.setDescripcion("Oferta de prueba");
		oferta.setFechaFin(new Date());
		Long id = ofertaService.crearOferta(oferta);
		Assert.assertNotNull(ofertaService.obtenerOferta(id));
	}
	

}
