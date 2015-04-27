package grupo6.modulo.product.factory;

import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Producto;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion de {@link IBusquedaProducto} que busca productos
 * por paquete.
 * @author caespinosam
 *
 */
@Repository(value = "busquedaProductoPaquete") 
@Transactional(readOnly = true)
public class BusquedaProductoPaquete  extends BaseDAO implements IBusquedaProducto {

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.factory.IBusquedaProducto#buscar(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override	
	public List<Producto> buscar(Object... parametros) {

		Criteria criteria = getCurrentSession().createCriteria(Producto.class);		
		criteria.add(Restrictions.ge("nombre",  (String)parametros[0])); 
		
		return (List<Producto>)criteria.list();
	}

}
