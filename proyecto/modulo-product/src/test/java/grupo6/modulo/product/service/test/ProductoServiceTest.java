package grupo6.modulo.product.service.test;

import grupo6.modulo.product.service.impl.ProductoService;
import grupo6.modulo.product.service.view.IProductoService;
import grupo6.persistencia.entidades.EEstadoRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Prueba de integracion del servicio {@link ProductoService}. 
 * Se usa una BD en memoria mediante hsqldb.
 * 
 * @author caespinosam
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:test-spring-beans.xml")
public class ProductoServiceTest {
	
	/** El servicio a probar. **/
	@Autowired
	private IProductoService productoService;
	
	/**
	 * Prueba el metodo crearProducto.
	 * 
	 * @throws Exception
	 *             cualquier error.
	 */
	@Test
	public void crearProductoTest() throws Exception {
		Producto p = new Producto();
		p.setCiudad("Bogota");
		p.setFechaInicio(new Date());
		p.setLugar("20 de Julio");
		p.setNombre("Paquete semana santa");
		p.setPrecio(200000D);
		
		Long id = productoService.crearProducto(p);
		Producto pencontrado = productoService.buscarProductoPorId(id);
		Assert.assertNotNull(pencontrado);
		List<RatingProducto> ratings = productoService.buscarRatingPorProductoId(id);
		Assert.assertFalse(ratings.isEmpty());
		for (RatingProducto rating : ratings) {
			Assert.assertTrue(rating.getEstado() == EEstadoRating.SINCALIFICAR);
		}		
	}

}
