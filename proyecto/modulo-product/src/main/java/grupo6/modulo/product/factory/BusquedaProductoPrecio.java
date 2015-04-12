package grupo6.modulo.product.factory;

import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Producto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion de {@link IBusquedaProducto} que busca productos
 * por un rango de precios.
 * @author caespinosam
 *
 */
@Repository(value = "busquedaProductoPrecio") 
@Transactional(readOnly = true)
public class BusquedaProductoPrecio  extends BaseDAO implements IBusquedaProducto {

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.factory.IBusquedaProducto#buscar(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> buscar(Object... parametros) {

		Criteria criteria = getCurrentSession().createCriteria(Producto.class);		
		criteria.add(Restrictions.ge("precio", (Double)parametros[0])); 
		criteria.add(Restrictions.le("precio", (Double)parametros[1]));
		
		return (List<Producto>)criteria.list();
	}

}
