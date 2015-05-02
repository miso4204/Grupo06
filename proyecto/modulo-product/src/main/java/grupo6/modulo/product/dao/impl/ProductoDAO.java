package grupo6.modulo.product.dao.impl;

import grupo6.modulo.product.dao.view.IProductoDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Producto;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link IProductoDAO}.
 */
@Repository(value = "productoDAO") 
public class ProductoDAO extends BaseDAO implements IProductoDAO {
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IProductoDAO#crear(grupo6.persistencia.entidades.Producto)
	 */
	@Transactional
	public Long crear(Producto producto) {
		producto.setFechaCreacion(new Date());		
		return (Long)getCurrentSession().save(producto);
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IProductoDAO#buscarPorId(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public Producto buscarPorId(Long id) {
		return (Producto) getCurrentSession().get(Producto.class, id);
	}

	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IProductoDAO#listarTodos()
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> listarTodos() {
		Query query = getCurrentSession()
				.createQuery("from Producto");
		query.setCacheable(true);
		return query.list();
	}

}
