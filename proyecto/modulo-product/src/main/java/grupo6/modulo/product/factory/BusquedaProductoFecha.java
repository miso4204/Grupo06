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
 * por un rango de fechas en la que se toma del paquete.
 * @author caespinosam
 *
 */
@Repository(value = "busquedaProductoFecha") 
@Transactional(readOnly = true)
public class BusquedaProductoFecha  extends BaseDAO implements IBusquedaProducto {

	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.factory.IBusquedaProducto#buscar(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override	
	public List<Producto> buscar(Object... parametros) {

		Criteria criteria = getCurrentSession().createCriteria(Producto.class);		
		criteria.add(Restrictions.ge("fechaInicio",  (Date)parametros[0])); 
		criteria.add(Restrictions.le("fechaInicio",  (Date)parametros[1]));
		
		return (List<Producto>)criteria.list();
	}

}
