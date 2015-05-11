package grupo6.web.controller.rest;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.product.factory.ETipoBusqueda;
import grupo6.modulo.product.service.view.IProductoService;
import grupo6.modulo.user.service.impl.IUsuarioService;
import grupo6.modulo.utilidades.FeaturesNames;
import grupo6.modulo.utilidades.Variability;
import grupo6.persistencia.entidades.Actividad;
import grupo6.persistencia.entidades.Alojamiento;
import grupo6.persistencia.entidades.ETipoCalificacionRating;
import grupo6.persistencia.entidades.ETipoRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;
import grupo6.persistencia.entidades.RatingProductoCalificacion;
import grupo6.persistencia.entidades.Usuario;
import grupo6.persistencia.entidades.Vuelo;
import grupo6.web.dto.ActividadDTO;
import grupo6.web.dto.AlojamientoDTO;
import grupo6.web.dto.CalificacionResponseDTO;
import grupo6.web.dto.CalificarRequestDTO;
import grupo6.web.dto.ProductoRequestDTO;
import grupo6.web.dto.ProductoResponseDTO;
import grupo6.web.dto.ResponseDTO;
import grupo6.web.dto.UsuarioDTO;
import grupo6.web.dto.VueloDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class ProductoRestController extends BaseRestController {
	
	/** Srvicios de producto.*/
	@Autowired 
	private IProductoService productoService;
	/** Servicios de usuarios.*/
	@Autowired 
	private IUsuarioService usuarioService;	
	
	
	/**
	 * Servicio REST para consultar un alojamiento mediante su id.
	 * @param id del alojamiento 

	 */
	@RequestMapping(value = "/alojamiento/{id}", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AlojamientoDTO buscarAlojamiento(@PathVariable("id") Long id) {
		
		return convertirAlojamientoToDTO(productoService.buscarAlojamientoPorId(id));
	}
	
	/**
	 * Servicio REST para consultar un vuelo mediante su id.
	 * @param id del vuelo 

	 */
	@RequestMapping(value = "/vuelo/{id}", method = RequestMethod.GET,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody VueloDTO buscarVuelo(@PathVariable("id") Long id) {

		Vuelo vuelo = productoService.buscarVueloPorId(id);
		
		if (vuelo != null) {
			
			return convertirVuelotoToDTO(productoService.buscarVueloPorId(id));
		} else {
			return null;
		}
		
	}
	
	/**
	 * Servicio REST para consultar una actividad mediante su id.
	 * @param id de la actividad 

	 */
	@RequestMapping(value = "/actividad/{id}", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ActividadDTO buscarActividad(@PathVariable("id") Long id) {
		
		return convertirActividadtoToDTO(productoService.buscarActividadPorId(id));
	}
	
	
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
		
		Long idVuelo = 0L;
		Long idAloja = 0L;
		
		try {
			if (productoDTO.getVuelo() != null) {
				VueloDTO vueloProd = productoDTO.getVuelo();
				Vuelo vueloEnti =  convertVueloDTOTOVueloEntity(vueloProd);
				idVuelo = productoService.crearVuelo(vueloEnti);
			}else {
				System.out.println("El vuelo se encuentra null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se envió un vuelo para crear o se presentaron problemas al hacerlo");
		}
		
		
		try {
			if (productoDTO.getAlojamiento() != null) {
				AlojamientoDTO alojamientoProd = productoDTO.getAlojamiento();
				Alojamiento alojaEntity = convertirALojamientoDTOToAlojamientoEntity(alojamientoProd);
				idAloja = productoService.crearAlojamiento(alojaEntity);
			} else {
				System.out.println("El alojamiento se encuentra null");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se envió un alojamiento para crear o se presentaron problemas al crearlo");
		}
		
		
		Producto producto = new Producto();
		producto.setCiudad(productoDTO.getCiudad());
		producto.setLugar(productoDTO.getLugar());
		producto.setNombre(productoDTO.getNombre());
		producto.setPrecio(productoDTO.getPrecio());
		producto.setUrlImagen(productoDTO.getUrlImagen());
		producto.setFechaInicio(productoDTO.getFechaInicio());
		producto.setFechaCreacion(new Date());
		producto.setProveedorId(productoDTO.getProveedorId());
		producto.setTipoMoneda(productoDTO.getTipoMoneda());
		producto.setDescripcionPaquete(productoDTO.getDescripcion());
		
		if (idAloja != 0){
			producto.setIdAlojamiento(idAloja);
		}
		
		if (idVuelo  != 0){
			producto.setIdVuelo(idVuelo);
		}
		
		if (productoDTO.getActividades() != null) {
			producto.setActividades(productoDTO.getActividades());
		}
		
		return productoService.crearProducto(producto); 
	}
	
	
	/**
	 * Servicio REST para cerar un alojamiento.
	 * @param alojamiento a crear en formato JSON
	 * 
	 * @return el id del nuevo alojamiento.
	 */
	@RequestMapping(value = "/alojamiento", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long crearAlojamiento(@RequestBody AlojamientoDTO alojamientoDTO) {
		
		Alojamiento alojamiento = convertirALojamientoDTOToAlojamientoEntity(alojamientoDTO);
		return productoService.crearAlojamiento(alojamiento);
	}
	
	
	/**
	 * Servicio REST para obtener los alojamientoq ue existen 
	 * en la base de datos
	 * 
	 * @return Listado de alojamiento que existen en la base de datos.
	 */
	@RequestMapping(value = "/alojamientos", method = RequestMethod.GET,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlojamientoDTO> obtenerAlojamientos() {
		
		List<AlojamientoDTO> alojamientosDTO = new ArrayList<AlojamientoDTO>();
		List<Alojamiento> alojamientosObtenidos = productoService.obtenerAlojamientos();
		
		for (Alojamiento alojamiento : alojamientosObtenidos) {
			alojamientosDTO.add(convertirAlojamientoToDTO(alojamiento));
		}
		
		return alojamientosDTO;
	}	

	
	/**
	 * Servicio REST para actualizar una actividad .
	 * @param actividad a actualizar en formato JSON
	 */
	@RequestMapping(value = "/actividad", method = RequestMethod.PUT, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean actualizarActividad(@RequestBody ActividadDTO actividadDTO) {
		
		Actividad actividad = converActivDTOTOActividadENtity(actividadDTO);
		return productoService.actualizarActividad(actividad);
	}
	
	/**
	 * Servicio REST para actualizar un alojamiento.
	 * @param alojamiento a actualizar en formato JSON

	 */
	@RequestMapping(value = "/alojamiento", method = RequestMethod.PUT, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean actualizarAlojamiento(@RequestBody AlojamientoDTO alojamientoDTO) {
		
		Alojamiento alojamiento = convertirALojamientoDTOToAlojamientoEntity(alojamientoDTO);
		return productoService.actualizarAlojamiento(alojamiento);
	}
	
	/**
	 * Servicio REST para cerar un vuelo.
	 * @param vuelo a crear en formato JSON
	 * 
	 * @return el id del nuevo vuelo.
	 */
	@RequestMapping(value = "/vuelo", method = RequestMethod.POST, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long crearVuelo(@RequestBody VueloDTO vueloDTO) {
		
		try {
			
			Vuelo vuelo = convertVueloDTOTOVueloEntity(vueloDTO);
			return productoService.crearVuelo(vuelo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Servicio REST para actualizar un vuelo.
	 * @param vuelo a actualizar en formato JSON
	 */
	@RequestMapping(value = "/vuelo", method = RequestMethod.PUT, 
						consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean actualizarVuelo(@RequestBody VueloDTO vueloDTO) {
		
		try {
			
			Vuelo vuelo = convertVueloDTOTOVueloEntity(vueloDTO);
			return productoService.actualizarVuelo(vuelo);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	/**
	 * Servicio REST para listar todos los productos del sistema.
	 * 
	 * @return listar todos los productos del sistema en formato JSON.
	 */
	@RequestMapping(value = "/listar", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProductoResponseDTO> listarProductos(@RequestHeader(value="tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		
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
			@PathVariable("precioFinal") Double precioFinal,
			@RequestHeader(value="tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		
		List<ProductoResponseDTO> productosDTO = new ArrayList<ProductoResponseDTO>();
		List<Producto> productos =  productoService.buscarProductos(ETipoBusqueda.POR_PRECIO, precioInicial, precioFinal,tipoMoneda);
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
			Date fechaFinal,
			@RequestHeader(value="tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		
		List<ProductoResponseDTO> productosDTO = new ArrayList<ProductoResponseDTO>();
		List<Producto> productos = 
				productoService.buscarProductos(ETipoBusqueda.POR_FECHA, fechaInicial, fechaFinal);
		for (Producto producto: productos) {
			productosDTO.add(crearProductoResponseDTO(producto, null));
		}
		return productosDTO;
	}
	
	#if($ByLocation == "true") 
	/**
	 * Servicio REST para buscar productos por ubicación (lugar o ciudad).
	 * 
	 * @return los productos del sistema filtrados por lugar en formato JSON.
	 */
	@RequestMapping(value = "/buscar_por_lugar/{lugar}",
			method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDTO> listarPorLugar(
			@PathVariable("lugar") String lugar,
			@RequestHeader(value="tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		boolean isSearchByLocation = Variability.isEnable(FeaturesNames.SEARCH_BY_LOCATION);
		if(isSearchByLocation){
			List<ProductoResponseDTO> productosDTO = new ArrayList<ProductoResponseDTO>();
			List<Producto> productos = 
					productoService.buscarProductos(ETipoBusqueda.POR_UBICACION, lugar);
			for (Producto producto: productos) {
				productosDTO.add(crearProductoResponseDTO(producto, null));
			}
			return devolverRespuestaExitosa("", productosDTO);	
		}else{
			throw new UnsupportedOperationException("Busqueda no soportada");
		}
	}
	#end
	
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
			@RequestHeader(value="clientId", required = false) Long clientId,
			@RequestHeader(value="tipoMoneda", required = false) TipoMoneda tipoMoneda) {		
		
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
			List<RatingProducto> ratings = 
					productoService.buscarRatingPorProductoId(request.getServicioId());					 
			for (RatingProducto rating : ratings) {
				if (rating.getTipoServicio() == ETipoRating.GENERAL) {
					productoService.calificarProducto(request.getClienteId(), 
							rating.getId(), calificacion);
					return;
				}
			}			
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
		productoDTO.setTipoMoneda(producto.getTipoMoneda());
		productoDTO.setDescripcion(producto.getDescripcionPaquete());
		
		if (producto.getActividades() != null) {
			System.out.println("__________ fecha llegada" + producto.getActividades().get(0).getFechaActividad());
			productoDTO.setActividades((producto.getActividades()));
		}
		
		Long idVuelo = producto.getIdVuelo();	
		if (idVuelo!= 0){
			Vuelo vuelo = productoService.buscarVueloPorId(idVuelo);
			VueloDTO vueloDTO = convertirVuelotoToDTO(vuelo);
			productoDTO.setVuelo(vueloDTO);
		} else {
			System.out.println("El id del vuelo esta vácio");
		}
		
		
		Long idAlojamiento = producto.getIdAlojamiento();	
		if (idAlojamiento!= 0){
			Alojamiento alojamiento = productoService.buscarAlojamientoPorId(idAlojamiento);
			AlojamientoDTO alojamientoDTO = convertirAlojamientoToDTO(alojamiento);
			productoDTO.setAlojamiento(alojamientoDTO);
		} else {
			System.out.println("El id del alojamiento esta vácio");
		}
		
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
			calificacionDTO.setPuntuacion(calificacion);
			calificacionDTO.setCantidadVotantes(votantes);
//			calificacionDTO.setPuntuacion((double)(1 + (int)(Math.random()*5)));
//			calificacionDTO.setCantidadVotantes((1 + (int)(Math.random()*1000000)));
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
								
				if (proveedor.getDescuentoCash() != null
						&& proveedor.getDescuentoCash() > 0) {
					productoDTO.setDescuentoCash(proveedor.getDescuentoCash());
					productoDTO.setPosibleDescuento(true);
				}
				if (proveedor.getDescuentoPse() != null
						&& proveedor.getDescuentoPse() > 0) {
					productoDTO.setDescuentoPse(proveedor.getDescuentoPse());
					productoDTO.setPosibleDescuento(true);
				}
				if (proveedor.getDescuentoTc()!= null
						&& proveedor.getDescuentoTc() > 0) {
					productoDTO.setDescuentoTc(proveedor.getDescuentoTc());
					productoDTO.setPosibleDescuento(true);
				}
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
	
	/**
	 *Metodo complementario que permite transformar una entidad alojamiento 
	 *a un alojamientoDTO que viaja por red
	 * @param alojamiento
	 * @return alojamientoDTO
	 */
	public AlojamientoDTO convertirAlojamientoToDTO(Alojamiento alojamiento) {
			
		AlojamientoDTO aloDTO = new AlojamientoDTO();
		aloDTO.setAireAcondicionado(alojamiento.isAireAcondicionado());
		aloDTO.setId(alojamiento.getId());
		aloDTO.setNumeroNoches(alojamiento.getNumeroNoches());
		aloDTO.setNumMaxPersonas(alojamiento.getNumMaxPersonas());
		aloDTO.setPiscina(alojamiento.isPiscina());
		aloDTO.setPrecioPorDia(alojamiento.getPrecioPorDia());
		aloDTO.setPrecioTotal(alojamiento.getPrecioTotal());
		aloDTO.setTipo(alojamiento.getTipo());
		aloDTO.setVigilancia(alojamiento.isVigilancia());
		aloDTO.setZonasVerdes(alojamiento.isZonasVerdes());
		
		return aloDTO;
	}
	
	
	/**
	 * Metodo que nos permite convertir un DTO que representa el objeto JSON
	 * de entrada a un metotodo en la entidad que nos permiterira 
	 * persistirla en BD
	 * @param alojamientoDTO
	 * @return alojamiento de tipo entidad
	 */
	public Alojamiento convertirALojamientoDTOToAlojamientoEntity (AlojamientoDTO alojamientoDTO){
		
		Alojamiento alojamiento = new Alojamiento();
		
		alojamiento.setAireAcondicionado(alojamientoDTO.isAireAcondicionado());
		alojamiento.setNumeroNoches(alojamientoDTO.getNumeroNoches());
		alojamiento.setNumMaxPersonas(alojamientoDTO.getNumMaxPersonas());
		alojamiento.setPiscina(alojamientoDTO.isPiscina());
		alojamiento.setPrecioPorDia(alojamientoDTO.getPrecioPorDia());
		alojamiento.setPrecioTotal(alojamientoDTO.getPrecioTotal());
		alojamiento.setTipo(alojamientoDTO.getTipo());
		alojamiento.setVigilancia(alojamientoDTO.isVigilancia());
		alojamiento.setZonasVerdes(alojamientoDTO.isZonasVerdes());
		
		return alojamiento;
	}
	
	/**
	 * Metodo que permite convertir una entidad vuelo en un
	 * vuelo DTO para que viaje
	 * @param vuelo
	 * @return vueloDTO
	 */
	public VueloDTO convertirVuelotoToDTO(Vuelo vuelo) {
		
		VueloDTO vueDTO = new VueloDTO();
		vueDTO.setAerolinea(vuelo.getAerolinea());
		vueDTO.setDestino(vuelo.getDestino());
		vueDTO.setFechaLlegada(vuelo.getFechaLlegada().toString());
		vueDTO.setFechaSalida(vuelo.getFechaSalida().toString());
		vueDTO.setId(vuelo.getId());
		vueDTO.setNumPersonas(vuelo.getNumPersonas());
		vueDTO.setOrigen(vuelo.getOrigen());
		vueDTO.setPrecioTotal(vuelo.getPrecioTotal());
		vueDTO.setPrecioVuelo(vuelo.getPrecioVuelo());
		return vueDTO;
	}
	
	
	/**
	 * Metodos que nos permite convertir un vueloDTO que nos llega
	 * en formato JSON a un vueo que es una entidad y nos permite almacenarlo
	 * en la BD
	 * @param vueloDTO
	 * @return vuelo de entidad 
	 */
	public Vuelo convertVueloDTOTOVueloEntity(VueloDTO vueloDTO) {
		
		
		Vuelo vuelo = new Vuelo();
		
		try {
			
			vuelo.setAerolinea(vueloDTO.getAerolinea());
			vuelo.setDestino(vueloDTO.getDestino());
			vuelo.setFechaLlegada(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vueloDTO.getFechaLlegada()));
			vuelo.setFechaSalida(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vueloDTO.getFechaSalida()));
			vuelo.setNumPersonas(vueloDTO.getNumPersonas());
			vuelo.setOrigen(vueloDTO.getOrigen());
			vuelo.setPrecioTotal(vueloDTO.getPrecioTotal());
			vuelo.setPrecioVuelo(vueloDTO.getPrecioVuelo());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Se presento un error al intentar convertir el vuelo dto a un vuelo de entidad posiblemente en el casteo de fechas ");
		}

		return vuelo;
	}
	
	
	/**
	 * Metodo que permite convertir una entidad actividad en su version DTO
	 * para que viaje por la red
	 * @param actividad
	 * @return actividad DTO
	 */
	public ActividadDTO convertirActividadtoToDTO(Actividad actividad) {
		
		ActividadDTO actiDTO = new ActividadDTO();
		actiDTO.setCostoActividad(actividad.getCostoActividad());
		actiDTO.setCostoTotal(actividad.getCostoTotal());
		actiDTO.setDescripcion(actividad.getDescripcion());
		System.out.println("___________ FECHA EN CONVERTIR " + actividad.getFechaActividad().toString());
		actiDTO.setFechaActividad(actividad.getFechaActividad().toString());
		actiDTO.setId(actividad.getId());
		actiDTO.setNombreActividad(actividad.getNombreActividad());
		actiDTO.setNumPersonas(actividad.getNumPersonas());
		
		return actiDTO;
	}
	
	/**
	 * Metodo que nos permite convertir un objeto de entrada como actividadDTO
	 * a un objeto de entidad de la actividad
	 * @param actividadDTO
	 * @return actividad DTO que contiene los datos reopresentados por el DTO
	 */
	public Actividad converActivDTOTOActividadENtity(ActividadDTO actividadDTO) {
		
		Actividad actividad = new Actividad();
		
		try {
			actividad.setId(actividadDTO.getId());
			actividad.setCostoActividad(actividadDTO.getCostoActividad());
			actividad.setCostoTotal(actividadDTO.getCostoTotal());
			actividad.setDescripcion(actividadDTO.getDescripcion());
			actividad.setFechaActividad(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(actividadDTO.getFechaActividad()));
			actividad.setNombreActividad(actividadDTO.getNombreActividad());
			actividad.setNumPersonas(actividadDTO.getNumPersonas());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Se presento un error mientras se convertia la actividad DTO a la actividad entidad");
		}

		return actividad;
	}
}
