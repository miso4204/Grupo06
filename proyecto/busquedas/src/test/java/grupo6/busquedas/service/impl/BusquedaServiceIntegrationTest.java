package grupo6.busquedas.service.impl;

import grupo6.busquedas.dao.impl.BusquedaDAO;
import grupo6.busquedas.service.view.IBusquedaService;
import grupo6.persistencia.entidades.Oferta;
import grupo6.persistencia.entidades.enums.TipoOferta;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Prueba de integracion del servicio {@link BusquedaService}. Se hace uso de
 * Arquillian para simular un contenedor de aplicaciones y así hacer funcionar
 * los EJBs y el manejador de persistencia que apunta a una BD en memoria.
 * 
 * @author caespinosam
 * 
 */
@RunWith(Arquillian.class)
public class BusquedaServiceIntegrationTest {

	/** El servicio a probar. **/
	@Inject
	private IBusquedaService busquedaService;
	/**
	 * Manejador de persistencia JPA para ingresar datos de prueba directamente
	 * a la BD.
	 */
	@PersistenceContext(unitName = "BusquedasPU")
	EntityManager em;
	@Inject
	UserTransaction utx;

	/**
	 * Configura el servidor de aplicaciones embebido.
	 */
	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "TestBusquedas.war")
				.addPackage(BusquedaDAO.class.getPackage())
				.addPackage(BusquedaService.class.getPackage())
				.addAsResource("persistenceTest.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource("beansTest.xml", "beans.xml");
	}

	/**
	 * Prueba la busqueda por ciudad.
	 * 
	 * @throws Exception
	 *             cualquier error.
	 */
	@Test
	public void buscarOfertasPorCiudadTest() throws Exception {
		utx.begin();
		em.joinTransaction();
		for (int i = 0; i < 3; i++) {
			Oferta f1 = new Oferta();
			f1.setCiudad("Bogota");
			f1.setDescripcion("oferta " + i);
			f1.setFechaFin(new Date());
			f1.setTipoOferta(TipoOferta.OF1);
			em.persist(f1);
		}
		utx.commit();
		List<Oferta> ofertas = busquedaService.buscarOfertasPorCiudad("Bogota");
		Assert.assertEquals(3, ofertas.size());
		System.out.println(ofertas);
	}

	/**
	 * Prueba la busqueda por tipo.
	 * 
	 * @throws Exception
	 *             cualquier error.
	 */
	@Test
	public void buscarOfertasPorTipoTest() throws Exception {
		utx.begin();
		em.joinTransaction();
		for (int i = 0; i < 3; i++) {
			Oferta f1 = new Oferta();
			f1.setCiudad("Medellin");
			f1.setDescripcion("oferta " + i);
			f1.setFechaFin(new Date());
			f1.setTipoOferta(TipoOferta.OF2);
			em.persist(f1);
		}
		utx.commit();
		List<Oferta> ofertas = busquedaService
				.buscarOfertasPorTipo(TipoOferta.OF2);
		Assert.assertEquals(3, ofertas.size());
		System.out.println(ofertas);
	}

}
