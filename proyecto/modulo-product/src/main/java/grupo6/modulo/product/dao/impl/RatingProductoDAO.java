package grupo6.modulo.product.dao.impl;

import grupo6.modulo.product.dao.view.IRatingProductoDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.EEstadoRating;
import grupo6.persistencia.entidades.ETipoRating;
import grupo6.persistencia.entidades.RatingProducto;

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
	 * @see grupo6.modulo.product.dao.view.IRatingProductoDAO#crear(java.lang.Long, grupo6.persistencia.entidades.ETipoRating)
	 */
	@Transactional
	public Long crear(Long productoId, ETipoRating tipoServucio) {
		
		RatingProducto rating = new RatingProducto();
		rating.setTipoServicio(tipoServucio);
		rating.setProductoId(productoId);
		rating.setEstado(EEstadoRating.SINCALIFICAR);
		return (Long)getCurrentSession().save(rating);
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
	 * @see grupo6.modulo.product.dao.view.IRatingProductoDAO#actulizar(grupo6.persistencia.entidades.RatingProducto)
	 */
	public void actulizar(RatingProducto rating) {
		getCurrentSession().update(rating);		
	}
	
	

}
