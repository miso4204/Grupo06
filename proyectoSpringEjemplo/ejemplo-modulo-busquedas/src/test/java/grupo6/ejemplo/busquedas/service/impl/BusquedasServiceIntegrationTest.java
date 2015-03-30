package grupo6.ejemplo.busquedas.service.impl;

import grupo6.ejemplo.busquedas.service.view.IBusquedasService;
import grupo6.ejemplo.ofertas.service.impl.OfertaService;
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
public class BusquedasServiceIntegrationTest {

	/** El servicio a probar. **/
	@Autowired // asigna a este atributo el bean de tipo IBusquedasService y que
			   // se llama 'busquedasService' en el contexto de Spring. 
	private IBusquedasService busquedasService;
	/** El servicio del módulo de ofertas. **/
	@Autowired // asigna a este atributo el bean de tipo IOfertaService y que
			   // se llama 'ofertaService' en el contexto de Spring. 
	private IOfertaService ofertaService;
	

	/**
	 * Prueba la busqueda de ofertas por ciudad.
	 * 
	 * @throws Exception
	 *             cualquier error.
	 */
	@Test
	public void buscarOfertasPorCiudadTest() throws Exception {
		Oferta oferta = new Oferta();
		oferta.setCiudad("Bogota");
		oferta.setDescripcion("Oferta de prueba");
		oferta.setFechaFin(new Date());
		Oferta oferta2 = new Oferta();
		oferta2.setCiudad("bogota");
		oferta2.setDescripcion("Oferta de prueba");
		oferta2.setFechaFin(new Date());
		Oferta oferta3 = new Oferta();
		oferta3.setCiudad("medellin");
		oferta3.setDescripcion("Oferta de prueba");
		oferta3.setFechaFin(new Date());
		ofertaService.crearOferta(oferta);
		ofertaService.crearOferta(oferta2);
		ofertaService.crearOferta(oferta3);
		Assert.assertEquals(2, busquedasService.buscarOfertasPorCiudad("BOGOTA").size());
		Assert.assertEquals(1, busquedasService.buscarOfertasPorCiudad("MEDELLIN").size());
	}
	
	
	/**
	 * Prueba el método buscarOfertasTodas().
	 * 
	 * @throws Exception
	 *             cualquier error.
	 */
	@Test
	public void buscarOfertasTodasTest() throws Exception {
		Oferta oferta = new Oferta();
		oferta.setCiudad("Cali");
		oferta.setDescripcion("Oferta de prueba");
		oferta.setFechaFin(new Date());
		Oferta oferta2 = new Oferta();
		oferta2.setCiudad("cali");
		oferta2.setDescripcion("Oferta de prueba");
		oferta2.setFechaFin(new Date());
		Oferta oferta3 = new Oferta();
		oferta3.setCiudad("cali");
		oferta3.setDescripcion("Oferta de prueba");
		oferta3.setFechaFin(new Date());
		ofertaService.crearOferta(oferta);
		ofertaService.crearOferta(oferta2);
		ofertaService.crearOferta(oferta3);
		Assert.assertTrue(busquedasService.buscarOfertasTodas().size() >= 3);
	}

}
