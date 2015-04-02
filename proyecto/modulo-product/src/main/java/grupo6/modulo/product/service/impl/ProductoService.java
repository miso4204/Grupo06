package grupo6.modulo.product.service.impl;

import grupo6.modulo.product.dao.view.IProductoDAO;
import grupo6.modulo.product.dao.view.IRatingProductoDAO;
import grupo6.modulo.product.service.view.IProductoService;
import grupo6.persistencia.entidades.ETipoCalificacionRating;
import grupo6.persistencia.entidades.ETipoRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;

import java.util.Date;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.summary.Product;
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
	public List<Product> buscarProductosPorUbicacion(String ubicacion) {
		return productoDAO.buscarPorUbicacion(ubicacion);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#buscarProductosPorPrecio(double, double)
	 */
	@Transactional(readOnly = true)
	public List<Product> buscarProductosPorPrecio(double precioInicial,
			double precioFinal) {
		return productoDAO.buscarPorPrecio(precioInicial, precioFinal);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#buscarProductosPorFechaInicio(java.util.Date, java.util.Date)
	 */
	@Transactional(readOnly = true)
	public List<Product> buscarProductosPorFechaInicio(Date fechaInicial,
			Date fechaFinal) {
		return productoDAO.buscarPorFechaInicio(fechaInicial, fechaFinal);
	}
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#calificarProducto(java.lang.Long, grupo6.persistencia.entidades.ETipoCalificacionRating)
	 */
	public void calificarProducto(Long servicioId,
			ETipoCalificacionRating calificacion) {

		RatingProducto ratingServicio = ratingProductoDAO.buscarPorId(servicioId);
		if (ratingServicio != null) {			
			ratingServicio.calificar(calificacion);
			ratingProductoDAO.actulizar(ratingServicio);
		}
		
	}
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.service.view.IProductoService#buscarRatingPorProductoId(java.lang.Long)
	 */
	public List<RatingProducto> buscarRatingPorProductoId(Long productoId) {
		return ratingProductoDAO.buscarPorProductoId(productoId);
	}


	
	/**
	 * Crea los ratings default a una nuevo producto o paquete recien creado.
	 * @param productoId el id del producto recien creado.
	 */
	private void asignarRatingsDefault(Long productoId) {
		
		ratingProductoDAO.crear(productoId, ETipoRating.GENERAL);
		ratingProductoDAO.crear(productoId, ETipoRating.UBICACION);
		ratingProductoDAO.crear(productoId, ETipoRating.ATENCION);
		ratingProductoDAO.crear(productoId, ETipoRating.LIMPIEZA);
		ratingProductoDAO.crear(productoId, ETipoRating.CUARTOS);
		ratingProductoDAO.crear(productoId, ETipoRating.COMODIDAD);
	}

	
}
