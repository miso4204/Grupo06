package grupo6.modulo.product.service.impl;

import grupo6.modulo.product.dao.view.IProductoDAO;
import grupo6.modulo.product.dao.view.IRatingProductoDAO;
import grupo6.modulo.product.service.view.IProductoService;
import grupo6.persistencia.entidades.ETipoCalificacionRating;
import grupo6.persistencia.entidades.ETipoRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;
import grupo6.persistencia.entidades.RatingProductoCalificacion;

import java.util.Date;
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
	 * @see grupo6.modulo.product.service.view.IProductoService#buscarProductosPorUbicacion(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<Producto> buscarProductosPorUbicacion(String ubicacion) {
		return productoDAO.buscarPorUbicacion(ubicacion);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#buscarProductosPorPrecio(double, double)
	 */
	@Transactional(readOnly = true)
	public List<Producto> buscarProductosPorPrecio(double precioInicial,
			double precioFinal) {
		return productoDAO.buscarPorPrecio(precioInicial, precioFinal);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#buscarProductosPorFechaInicio(java.util.Date, java.util.Date)
	 */
	@Transactional(readOnly = true)
	public List<Producto> buscarProductosPorFechaInicio(Date fechaInicial,
			Date fechaFinal) {
		return productoDAO.buscarPorFechaInicio(fechaInicial, fechaFinal);
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
					new RatingProductoCalificacion();
			calificacionServicio.setClienteId(clienteId);
			calificacionServicio.setRatingProductoId(servicioId);
			calificacionServicio.setCalificacion(calificacion);
			ratingProductoDAO.crearCalificacion(calificacionServicio);
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
