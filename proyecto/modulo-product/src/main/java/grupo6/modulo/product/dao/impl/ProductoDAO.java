package grupo6.modulo.product.dao.impl;

import grupo6.modulo.product.dao.view.IProductoDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Producto;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
	 * @see grupo6.modulo.product.dao.view.IProductoDAO#buscarPorUbicacion(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Producto> buscarPorUbicacion(String ubicacion) {
		
		Criteria criteria = getCurrentSession().createCriteria(Producto.class);		
		criteria.add(Restrictions.or(
					Restrictions.ilike("lugar", ubicacion),
					Restrictions.ilike("ciudad", ubicacion))				
				); 
				
		return (List<Producto>)criteria.list();
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IProductoDAO#buscarPorPrecio(double, double)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> buscarPorPrecio(double precioInicial,
			double precioFinal) {
		
		Criteria criteria = getCurrentSession().createCriteria(Producto.class);		
		criteria.add(Restrictions.ge("precio", precioInicial)); 
		criteria.add(Restrictions.le("precio", precioFinal));
		
		return (List<Producto>)criteria.list();
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IProductoDAO#buscarPorFechaInicio(java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> buscarPorFechaInicio(Date fechaInicial, Date fechaFinal) {
		
		Criteria criteria = getCurrentSession().createCriteria(Producto.class);		
		criteria.add(Restrictions.ge("fechaInicio", fechaInicial)); 
		criteria.add(Restrictions.le("fechaInicio", fechaFinal));
		
		return (List<Producto>)criteria.list();
	}

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IProductoDAO#listarTodos()
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> listarTodos() {
		return (List<Producto>)getCurrentSession()
				.createQuery("from Producto").list();
	}

}
