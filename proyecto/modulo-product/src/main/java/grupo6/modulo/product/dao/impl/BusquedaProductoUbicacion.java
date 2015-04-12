package grupo6.modulo.product.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import grupo6.modulo.product.dao.view.IBusquedaProducto;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Producto;

/**
 * Implementacion de {@link IBusquedaProducto} que busca productos
 * por ubicacion.
 * @author caespinosam
 *
 */
@Repository(value = "busquedaProductoUbicacion")
@Transactional(readOnly = true)
public class BusquedaProductoUbicacion  extends BaseDAO implements IBusquedaProducto {

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IBusquedaProducto#buscar(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> buscar(Object... parametros) {

		Criteria criteria = getCurrentSession().createCriteria(Producto.class);		
		criteria.add(Restrictions.or(
					Restrictions.ilike("lugar", (String)parametros[0]),
					Restrictions.ilike("ciudad", (String)parametros[0]))				
				); 
				
		return (List<Producto>)criteria.list();
	}

}
