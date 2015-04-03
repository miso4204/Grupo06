package grupo6.web.controller.rest;

import grupo6.modulo.product.service.view.IProductoService;
import grupo6.persistencia.entidades.Producto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador REST de los servicios del producto.
 * @author caespinosam
 *
 */
@Controller 
@RequestMapping("/producto")
public class ProductoRestController {
	
	/** Srvicios de producto.*/
	@Autowired 
	private IProductoService productoService;
	
	/**
	 * Servicio REST que crea un producto.
	 * @param en producto a crear. Creado a partir de un JSON.
	 * 
	 * @return el id del nuevo producto.
	 */
	@RequestMapping(value = "/crear", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long crearProducto(@RequestBody Producto producto) {
		
		producto.setFechaInicioPublicacion(new Date());
		producto.setFechaCreacion(new Date());
		return productoService.crearProducto(producto); 
	}
	
	/**
	 * Servicio REST para listar todos los productos del sistema.
	 * 
	 * @return listar todos los productos del sistema en formato JSON.
	 */
	@RequestMapping(value = "/listar", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Producto> listarProductos() {
		
		return productoService.listarTodosProductos();
	}

}
