package grupo6.modulo.product.service.test;

import grupo6.modulo.product.factory.ETipoBusqueda;
import grupo6.modulo.product.service.impl.ProductoService;
import grupo6.modulo.product.service.view.IProductoService;
import grupo6.persistencia.entidades.ETipoCalificacionRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;

import java.util.Arrays;
import java.util.Calendar;
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
		String[] actividades = {"a1", "a2"};
		p.setActividades(Arrays.asList(actividades));
		
		Long id = productoService.crearProducto(p);
		Producto pencontrado = productoService.buscarProductoPorId(id);
		Assert.assertNotNull(pencontrado);
		Assert.assertTrue(pencontrado.getActividades().size() == 2);
		List<RatingProducto> ratings = productoService.buscarRatingPorProductoId(id);
		Assert.assertFalse(ratings.isEmpty());
		Assert.assertFalse(productoService.listarTodosProductos().isEmpty());
				
	}

	/**
	 * Prueba el metodo buscarProductosPorUbicacion.
	 * 
	 * @throws Exception
	 *             cualquier error.
	 */
	@Test
	public void buscarProductosPorUbicacionTest() throws Exception {
		Producto p = new Producto();
		p.setCiudad("Bogota");
		p.setFechaInicio(new Date());
		p.setLugar("Hotel xxx");
		p.setNombre("Paquete semana santa");
		p.setPrecio(200000D);
		Producto p2 = new Producto();
		p2.setCiudad("Bogota");
		p2.setFechaInicio(new Date());
		p2.setLugar("Hotel xxx");
		p2.setNombre("Paquete semana santa");
		p2.setPrecio(200000D);
		
		productoService.crearProducto(p);
		productoService.crearProducto(p2);		
		
		List<Producto> pencontrado = 
				productoService.buscarProductos(ETipoBusqueda.POR_UBICACION,
						"HOtEL xxX");
		Assert.assertFalse(pencontrado.isEmpty());
		Assert.assertEquals(2, pencontrado.size());
			
	}

	
	/**
	 * Prueba el metodo buscarProductosPorPrecio.
	 * 
	 * @throws Exception
	 *             cualquier error.
	 */
	@Test
	public void buscarProductosPorPrecioTest() throws Exception {
		Producto p = new Producto();
		p.setCiudad("Bogota");
		p.setFechaInicio(new Date());
		p.setLugar("Hotel");
		p.setNombre("Paquete semana santa");
		p.setPrecio(1000000D);
		Producto p2 = new Producto();
		p2.setCiudad("Bogota");
		p2.setFechaInicio(new Date());
		p2.setLugar("Hotel");
		p2.setNombre("Paquete semana santa");
		p2.setPrecio(2000000D);
		
		productoService.crearProducto(p);
		productoService.crearProducto(p2);		
		
		List<Producto> pencontrado = 
				productoService.buscarProductos(ETipoBusqueda.POR_PRECIO, 1000000D, 2000000D);
		Assert.assertFalse(pencontrado.isEmpty());
		Assert.assertEquals(2, pencontrado.size());
		pencontrado = 
				productoService.buscarProductos(ETipoBusqueda.POR_PRECIO, 1000001D, 2000000D);
		Assert.assertEquals(1, pencontrado.size());
			
	}
	
	/**
	 * Prueba el metodo buscarProductosPorFechaInicio.
	 * 
	 * @throws Exception
	 *             cualquier error.
	 */
	@Test
	public void buscarProductosPorFechaInicioTest() throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2020);
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2022);
		
		Producto p = new Producto();
		p.setCiudad("Bogota");
		p.setFechaInicio(c.getTime());
		p.setLugar("HOtel");
		p.setNombre("Paquete semana santa");
		p.setPrecio(1000D);
		Producto p2 = new Producto();
		p2.setCiudad("Bogota");
		p2.setFechaInicio(c2.getTime());
		p2.setLugar("Hotel");
		p2.setNombre("Paquete semana santa");
		p2.setPrecio(20D);
		
		productoService.crearProducto(p);
		productoService.crearProducto(p2);		
		
		List<Producto> pencontrado = 
				productoService.buscarProductos(ETipoBusqueda.POR_FECHA, c.getTime(), c2.getTime());
		Assert.assertFalse(pencontrado.isEmpty());
		Assert.assertEquals(2, pencontrado.size());
		pencontrado = 
				productoService.buscarProductos(ETipoBusqueda.POR_FECHA, c2.getTime(), c2.getTime());
		Assert.assertEquals(1, pencontrado.size());
			
	}
	
	
	/**
	 * Prueba el metodo calificarProducto.
	 * 
	 * @throws Exception
	 *             cualquier error.
	 */
	@Test
	public void calificarProductoTest() throws Exception {
		
		Producto p = new Producto();
		p.setCiudad("Bogota");
		p.setFechaInicio(new Date());
		p.setLugar("HOtel");
		p.setNombre("Paquete semana santa");
		p.setPrecio(1000D);
				
		Long id = productoService.crearProducto(p);
		List<RatingProducto> ratings = productoService.buscarRatingPorProductoId(id);
		for (RatingProducto rating : ratings) {
			productoService.calificarProducto(1L, rating.getId(), ETipoCalificacionRating.MALO);
			productoService.calificarProducto(1L, rating.getId(), ETipoCalificacionRating.MALO);
			productoService.calificarProducto(2L, rating.getId(), ETipoCalificacionRating.MALO);
			productoService.calificarProducto(3L, rating.getId(), ETipoCalificacionRating.EXECELENTE);
		}	
		ratings = productoService.buscarRatingPorProductoId(id);
		for (RatingProducto rating : ratings) {
			Assert.assertEquals(3D, 
					productoService.obtenerCalificacionDeServicio(rating.getId()), .2);
			Assert.assertEquals(3, 
					productoService.obtenerNumeroVotantesDeServicio(rating.getId()));
		}	
			
	}

}
