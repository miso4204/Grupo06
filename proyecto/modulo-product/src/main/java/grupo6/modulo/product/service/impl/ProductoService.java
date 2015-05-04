package grupo6.modulo.product.service.impl;

import grupo6.modulo.product.dao.view.IProductoDAO;
import grupo6.modulo.product.dao.view.IRatingProductoDAO;
import grupo6.modulo.product.factory.BusquedaProductosFactory;
import grupo6.modulo.product.factory.ETipoBusqueda;
import grupo6.modulo.product.factory.IBusquedaProducto;
import grupo6.modulo.product.service.view.IProductoService;
import grupo6.persistencia.entidades.Actividad;
import grupo6.persistencia.entidades.Alojamiento;
import grupo6.persistencia.entidades.ETipoCalificacionRating;
import grupo6.persistencia.entidades.ETipoRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;
import grupo6.persistencia.entidades.RatingProductoCalificacion;
import grupo6.persistencia.entidades.Vuelo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link IProductoService}.
 */
@Service(value = "productoService") 
public class ProductoService implements IProductoService {

	/** DAO de productos. */
	@Autowired 
	private IProductoDAO productoDAO;
	/** DAO de ratings. */
	@Autowired 
	private IRatingProductoDAO ratingProductoDAO;
	
	
	
	/**
	 * Metodo que permite buscar un alojamiento por ID returna null si no lo encuentra
	 */
	@Override
	@Transactional(readOnly = true)
	public Alojamiento buscarAlojamientoPorId(Long Id) {
		return productoDAO.buscarAlojamientoPorId(Id);
	}

	/**
	 * Metodo que permite buscar vuelo por id
	 */
	@Override
	@Transactional(readOnly = true)
	public Vuelo buscarVueloPorId(Long Id) {

		return productoDAO.buscarVueloPorId(Id);
	}

	/**
	 * Buscar actividad pro id devuelve null si hay error
	 */
	@Override
	@Transactional(readOnly = true)
	public Actividad buscarActividadPorId(Long Id) {

		return productoDAO.buscarActividadPorId(Id);
	}
	
	/**
	 * Metodo que no permite actualizar un alojamiento en la BD
	 * @param alojamiento
	 */
	@Override
	@Transactional
	public boolean actualizarAlojamiento(Alojamiento alojamiento) {
		return productoDAO.actualizarAlojamiento(alojamiento);
		
	}
	
	/**
	 * Metodo que retorna todos los alojamientos
	 * @return todos los alojamientos de que esten en la BD
	 */
	@Override
	@Transactional
	public List<Alojamiento> obtenerAlojamientos(){
		return productoDAO.obtenerAlojamientos();
	}

	/**
	 * Metodo que permite crear un vuelo 
	 * @param vuelo
	 */
	@Override
	@Transactional
	public boolean actualizarVuelo(Vuelo vuelo) {
		return productoDAO.actualizarVuelo(vuelo);
	}

	/**
	 * Metodo que permite crear una actividad 
	 * @param actividad
	 */
	@Transactional
	public boolean actualizarActividad(Actividad actividad){
		
		return productoDAO.actualizarActividad(actividad);
	}
	
	/**
	 * Metodo que no permite crear un alohjamiento en la BD
	 * @param alojamiento
	 * @return id del alojamiento creado
	 */
	@Transactional
	public Long crearAlojamiento(Alojamiento alojamiento) {
		
		Long idAlojamiento = 0l;
		
		if (alojamiento != null) {
			
			idAlojamiento = productoDAO.crearAlojamiento(alojamiento);
		}
		
		return idAlojamiento;
	}
	
	/**
	 * Metodo que permite crear un vuelo 
	 * @param vuelo
	 * @return id del vuelo creado
	 */
	@Transactional
	public Long crearVuelo(Vuelo vuelo) {
		
		Long idVuelo = 0l;
		
		if (vuelo != null) {
			idVuelo = productoDAO.crearVuelo(vuelo);
		}
		
		return idVuelo;
	}
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#crearProducto(grupo6.persistencia.entidades.Producto)
	 */
	@Transactional
	public Long crearProducto(Producto producto) {
	
		Long productoId = productoDAO.crear(producto);
		asignarRatingsDefault(productoId);
		return productoId;
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#buscarProductoPorId(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public Producto buscarProductoPorId(Long id) {
		return productoDAO.buscarPorId(id);
	}


	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#calificarProducto(java.lang.Long, java.lang.Long, grupo6.persistencia.entidades.ETipoCalificacionRating)
	 */
	@Transactional
	public void calificarProducto(Long clienteId, Long servicioId,
			ETipoCalificacionRating calificacion) {

		RatingProducto ratingServicio = ratingProductoDAO.buscarPorId(servicioId);
		if (ratingServicio != null) {	
			
			RatingProductoCalificacion calificacionServicio =
					ratingProductoDAO.buscarCalificacionDeUsuario(ratingServicio.getId(), clienteId);
			if (calificacionServicio == null) { // no lo ha calificado
				calificacionServicio = 
						new RatingProductoCalificacion();
				calificacionServicio.setClienteId(clienteId);
				calificacionServicio.setRatingProductoId(servicioId);
				calificacionServicio.setCalificacion(calificacion);
				ratingProductoDAO.crearCalificacion(calificacionServicio);	
			}			
		}
		
	}
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#buscarRatingPorProductoId(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public List<RatingProducto> buscarRatingPorProductoId(Long productoId) {
		return ratingProductoDAO.buscarPorProductoId(productoId);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#obtenerCalificacionDeServicio(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public double obtenerCalificacionDeServicio(Long servicioId) {
		
		List<RatingProductoCalificacion> calificaciones = 
				ratingProductoDAO.buscarCalificacionesDeServicio(servicioId);
		int sumatoriaCalificaciones = 0;
		double promedioCalificacion = 0;
		if (!calificaciones.isEmpty()) {
			for (RatingProductoCalificacion calificacion: calificaciones) {
				sumatoriaCalificaciones =
						sumatoriaCalificaciones + calificacion.getCalificacion().getPuntaje();
				
			}
			promedioCalificacion = sumatoriaCalificaciones / calificaciones.size();
		}
		
		return promedioCalificacion;
	}
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#obtenerNumeroVotantesDeServicio(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public int obtenerNumeroVotantesDeServicio(Long servicioId) {
		
		List<RatingProductoCalificacion> calificaciones = 
				ratingProductoDAO.buscarCalificacionesDeServicio(servicioId);
		
		return calificaciones.size();
	}

	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#listarTodosProductos()
	 */
	@Transactional(readOnly = true)
	public List<Producto> listarTodosProductos() {
		return productoDAO.listarTodos();
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#buscarCalificacionDeUsuario(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public RatingProductoCalificacion buscarCalificacionDeUsuario(
			Long ratingProductoId, Long clienteId) {
		
		return ratingProductoDAO.buscarCalificacionDeUsuario(ratingProductoId, clienteId);
	}
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#buscarProductos(grupo6.modulo.product.factory.ETipoBusqueda, java.lang.Object[])
	 */
	@Override
	public List<Producto> buscarProductos(ETipoBusqueda tipoBusqueda,
			Object... parametros) {
		
		IBusquedaProducto buscador = 
				BusquedaProductosFactory.crearBuscador(tipoBusqueda);
		return buscador.buscar(parametros);		
	}


	
	/**
	 * Crea los ratings default a una nuevo producto o paquete recien creado.
	 * @param productoId el id del producto recien creado.
	 */
	private void asignarRatingsDefault(Long productoId) {
		
		ratingProductoDAO.crearRating(productoId, ETipoRating.GENERAL);
		ratingProductoDAO.crearRating(productoId, ETipoRating.UBICACION);
		ratingProductoDAO.crearRating(productoId, ETipoRating.ATENCION);
		ratingProductoDAO.crearRating(productoId, ETipoRating.LIMPIEZA);
		ratingProductoDAO.crearRating(productoId, ETipoRating.CUARTOS);
		ratingProductoDAO.crearRating(productoId, ETipoRating.COMODIDAD);
	}

	
}
