package grupo6.modulo.product.dao.impl;

import grupo6.modulo.product.dao.view.IRatingProductoDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.ETipoRating;
import grupo6.persistencia.entidades.RatingProducto;
import grupo6.persistencia.entidades.RatingProductoCalificacion;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link IRatingProductoDAO}.
 */
@Repository(value = "ratingProductoDAO") 
public class RatingProductoDAO extends BaseDAO implements IRatingProductoDAO {

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IRatingProductoDAO#crearRating(java.lang.Long, grupo6.persistencia.entidades.ETipoRating)
	 */
	@Transactional
	public Long crearRating(Long productoId, ETipoRating tipoServucio) {
		
		RatingProducto rating = new RatingProducto();
		rating.setTipoServicio(tipoServucio);
		rating.setProductoId(productoId);
		return (Long)getCurrentSession().save(rating);
	}
	

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IRatingProductoDAO#cerarCalificacion(grupo6.persistencia.entidades.RatingProductoCalificacion)
	 */
	@Transactional
	public Long crearCalificacion(RatingProductoCalificacion calificacion) {
		return (Long)getCurrentSession().save(calificacion);
	}
	
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IRatingProductoDAO#buscarPorId(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public RatingProducto buscarPorId(Long id) {
		return (RatingProducto) getCurrentSession().get(RatingProducto.class, id);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IRatingProductoDAO#buscarPorProductoId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RatingProducto> buscarPorProductoId(Long productoId) {
		
		Criteria criteria = getCurrentSession().createCriteria(RatingProducto.class);		
		criteria.add(Restrictions.eq("productoId", productoId)); 		
		
		return (List<RatingProducto>)criteria.list();
	}

	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IRatingProductoDAO#buscarCalificacionesDeServicio(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<RatingProductoCalificacion> buscarCalificacionesDeServicio(
			Long ratingProductoId) {
		
		Criteria criteria =
				getCurrentSession().createCriteria(RatingProductoCalificacion.class);		
		criteria.add(Restrictions.eq("ratingProductoId", ratingProductoId)); 		
		
		return (List<RatingProductoCalificacion>)criteria.list();
	}

	

}
