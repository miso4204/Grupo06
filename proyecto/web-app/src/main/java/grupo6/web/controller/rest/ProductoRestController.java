package grupo6.web.controller.rest;

import grupo6.modulo.product.service.view.IProductoService;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;
import grupo6.web.dto.CalificacionDTO;
import grupo6.web.dto.ProductoRequestDTO;
import grupo6.web.dto.ProductoResponseDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	public @ResponseBody Long crearProducto(@RequestBody ProductoRequestDTO productoDTO) {
		
		Producto producto = new Producto();
		producto.setCiudad(productoDTO.getCiudad());
		producto.setLugar(productoDTO.getLugar());
		producto.setNombre(productoDTO.getNombre());
		producto.setPrecio(productoDTO.getPrecio());
		producto.setUrlImagen(productoDTO.getUrlImagen());
		producto.setFechaInicio(productoDTO.getFechaInicio());
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
	public @ResponseBody List<ProductoResponseDTO> listarProductos() {
		
		List<ProductoResponseDTO> productosDTO = new ArrayList<ProductoResponseDTO>();
		List<Producto> productos = productoService.listarTodosProductos();
		for (Producto producto: productos) {
			productosDTO.add(crearProductoResponseDTO(producto));
		}
		return productosDTO;
	}
	
	
	/**
	 * Servicio REST para buscar productos por precio.
	 * 
	 * @return los productos del sistema filtrados por precio en formato JSON.
	 */
	@RequestMapping(value = "/buscar_por_precio/{precioInicial}/{precioFinal}",
			method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProductoResponseDTO> listarPorPrecio(
			@PathVariable("precioInicial") Double precioInicial,
			@PathVariable("precioFinal") Double precioFinal) {
		
		List<ProductoResponseDTO> productosDTO = new ArrayList<ProductoResponseDTO>();
		List<Producto> productos = 
				productoService.buscarProductosPorPrecio(precioInicial, precioFinal);
		for (Producto producto: productos) {
			productosDTO.add(crearProductoResponseDTO(producto));
		}
		return productosDTO;
	}
	
	
	/**
	 * Servicio REST para buscar productos por fecha.
	 * 
	 * @return los productos del sistema filtrados por precio en formato JSON.
	 */
	@RequestMapping(value = "/buscar_por_fecha/{fechaInicial}/{fechaFinal}",
			method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProductoResponseDTO> listarPorFecha(
			@PathVariable("fechaInicial") 
			@DateTimeFormat(pattern="yyyy-MM-dd")
			Date fechaInicial,
			@DateTimeFormat(pattern="yyyy-MM-dd")
			@PathVariable("fechaFinal") 
			Date fechaFinal) {
		
		List<ProductoResponseDTO> productosDTO = new ArrayList<ProductoResponseDTO>();
		List<Producto> productos = 
				productoService.buscarProductosPorFechaInicio(fechaInicial, fechaFinal);
		for (Producto producto: productos) {
			productosDTO.add(crearProductoResponseDTO(producto));
		}
		return productosDTO;
	}

	
	/**
	 * Crea un objeto ProductoResponseDTO (JSON) a partir de un objeto Producto.
	 * 
	 * @param producto el producto a convertir.
	 * @return la representacion DTO del producto.
	 */
	private ProductoResponseDTO crearProductoResponseDTO(Producto producto) {
		List<RatingProducto> ratings = 
				productoService.buscarRatingPorProductoId(producto.getId());
		ProductoResponseDTO productoDTO = new ProductoResponseDTO();
		producto.setId(producto.getId());
		productoDTO.setNombre(producto.getNombre());
		productoDTO.setCiudad(producto.getCiudad());
		productoDTO.setFechaInicio(producto.getFechaInicio());
		productoDTO.setLugar(producto.getLugar());
		productoDTO.setPrecio(producto.getPrecio());
		//TODO //productoDTO.setUltimaCompra(ultimaCompra);
		productoDTO.setUrlImagen(producto.getUrlImagen());
		List<CalificacionDTO> calificaciones = new ArrayList<CalificacionDTO>();
		for (RatingProducto rating : ratings) {
			double calificacion = 
					productoService.obtenerCalificacionDeServicio(rating.getId());
			int votantes = 
					productoService.obtenerNumeroVotantesDeServicio(rating.getId());
			CalificacionDTO calificacionDTO = new CalificacionDTO();
			calificacionDTO.setNombre(rating.getTipoServicio().name());
//			calificacionDTO.setPuntuacion(calificacion);
//			calificacionDTO.setCantidadVotantes(votantes);
			calificacionDTO.setPuntuacion((double)(1 + (int)(Math.random()*5)));
			calificacionDTO.setCantidadVotantes((1 + (int)(Math.random()*1000000)));
			calificaciones.add(calificacionDTO);
		}
		productoDTO.setCalificaciones(calificaciones);
		
		return productoDTO;
	}
}
