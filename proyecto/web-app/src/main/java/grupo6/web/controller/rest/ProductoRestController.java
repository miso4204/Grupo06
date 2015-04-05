package grupo6.web.controller.rest;

import grupo6.modulo.product.service.view.IProductoService;
import grupo6.modulo.user.service.impl.IUsuarioService;
import grupo6.persistencia.entidades.ETipoCalificacionRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;
import grupo6.persistencia.entidades.RatingProductoCalificacion;
import grupo6.persistencia.entidades.Usuario;
import grupo6.web.dto.CalificacionResponseDTO;
import grupo6.web.dto.CalificarRequestDTO;
import grupo6.web.dto.ProductoRequestDTO;
import grupo6.web.dto.ProductoResponseDTO;
import grupo6.web.dto.UsuarioDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	/** Servicios de usuarios.*/
	@Autowired 
	private IUsuarioService usuarioService;	
	
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
		producto.setProveedorId(productoDTO.getProveedorId());
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
			productosDTO.add(crearProductoResponseDTO(producto, null));
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
			productosDTO.add(crearProductoResponseDTO(producto, null));
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
			productosDTO.add(crearProductoResponseDTO(producto, null));
		}
		return productosDTO;
	}
	
	
	/**
	 * Servicio REST para buscar un producto por id.
	 * 
	 * @return el producto en formato JSON.
	 */
	@RequestMapping(value = "/buscar_por_id/{id}",
			method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ProductoResponseDTO obtenerProductoPorId(
			@PathVariable("id") Long id, 
			@RequestHeader(value="clientId", required = false) Long clientId) {		
		
		ProductoResponseDTO productoDTO = null;
		Producto producto = productoService.buscarProductoPorId(id);
	    if (producto != null) {
	    	productoDTO = crearProductoResponseDTO(producto, clientId);
	    }
		return productoDTO;
	}
	
	
	/**
	 * Servicio REST para calificar un producto.
	 * @param  request el request dto construido a partir
	 * de un json y que contiene los datos básicos para 
	 * la calificación. 
	 */
	@RequestMapping(value = "/calificar_producto",
			method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void calificarProducto(
			@RequestBody CalificarRequestDTO request) {		
		
		ETipoCalificacionRating calificacion =
					ETipoCalificacionRating.getTipoCalificacion(request.getPuntaje());
		if (calificacion != null) {
			productoService.calificarProducto(request.getClienteId(), 
					request.getServicioId(), calificacion);
		}		
	}

	
	/**
	 * Crea un objeto ProductoResponseDTO (JSON) a partir de un objeto Producto.
	 * 
	 * @param producto el producto a convertir.
	 * @param clienteId el id de un cliente para verificar si ya votó un servicio del producto.
	 * @return la representacion DTO del producto.
	 */
	private ProductoResponseDTO crearProductoResponseDTO(Producto producto, Long clienteId) {
		List<RatingProducto> ratings = 
				productoService.buscarRatingPorProductoId(producto.getId());
		ProductoResponseDTO productoDTO = new ProductoResponseDTO();
		productoDTO.setId(producto.getId());
		productoDTO.setNombre(producto.getNombre());
		productoDTO.setCiudad(producto.getCiudad());
		productoDTO.setFechaInicio(producto.getFechaInicio());
		productoDTO.setLugar(producto.getLugar());
		productoDTO.setPrecio(producto.getPrecio());
		//TODO //productoDTO.setUltimaCompra(ultimaCompra);
		productoDTO.setUrlImagen(producto.getUrlImagen());
		List<CalificacionResponseDTO> calificaciones = new ArrayList<CalificacionResponseDTO>();
		for (RatingProducto rating : ratings) {
			double calificacion = 
					productoService.obtenerCalificacionDeServicio(rating.getId());
			int votantes = 
					productoService.obtenerNumeroVotantesDeServicio(rating.getId());
			CalificacionResponseDTO calificacionDTO = new CalificacionResponseDTO();
			calificacionDTO.setId(rating.getId());
			calificacionDTO.setNombre(rating.getTipoServicio().name());
			//calificacionDTO.setPuntuacion(calificacion);
			//calificacionDTO.setCantidadVotantes(votantes);
			calificacionDTO.setPuntuacion((double)(1 + (int)(Math.random()*5)));
			calificacionDTO.setCantidadVotantes((1 + (int)(Math.random()*1000000)));
			if (clienteId != null) {
				RatingProductoCalificacion calUsuario = 
						productoService.buscarCalificacionDeUsuario(
								rating.getId(), clienteId);
				if (calUsuario != null) {
					calificacionDTO.setVotada(true);
					calificacionDTO.setCalificacionDada(
							calUsuario.getCalificacion().getPuntaje());
				}
			}
			if (producto.getProveedorId() != null) {
				Usuario proveedor = 
						usuarioService.buscarPorId(producto.getProveedorId());
				productoDTO.setProveedor(crearUsuarioDTO(proveedor));
			}
			
					
			calificaciones.add(calificacionDTO);
		}
		productoDTO.setCalificaciones(calificaciones);
		
		return productoDTO;
	}
	
	
	/**
	 * Crea un objeto UsuarioDTO (JSON) a partir de un objeto Usuario.
	 * 
	 * @param usuario el usuario a convertir..
	 
	 * @return la representacion DTO del usuario.
	 */
	private UsuarioDTO crearUsuarioDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setDireccion(usuario.getDireccion());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setNombre(usuario.getNombre());
		usuarioDTO.setTelefono(usuario.getTelefono());
		usuarioDTO.setWebsite(usuario.getWebsite());
		return usuarioDTO;
	}
}
