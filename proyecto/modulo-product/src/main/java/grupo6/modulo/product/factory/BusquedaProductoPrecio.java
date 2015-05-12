package grupo6.modulo.product.factory;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Producto;
import grupo6.utilidades.Currency;

import java.util.ArrayList;
import java.util.HashSet;
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

		double precioInicial = (Double)parametros[0];
		double precioFinal = (Double)parametros[1];
		TipoMoneda tipoMonedaUsuario = (TipoMoneda) parametros[2];
		
		if(tipoMonedaUsuario == null){
			tipoMonedaUsuario = TipoMoneda.DOLAR;
		}
		
		Criteria criteria = getCurrentSession().createCriteria(Producto.class);
		criteria.setCacheable(true);
		
		List<Producto> productos = (List<Producto>)criteria.list();
		productos = new ArrayList<Producto>(new HashSet(productos));
		List<Producto> productosEncontrados = new ArrayList<Producto>();
		
		for(Producto p : productos){
			double valor = Currency.getConversion(p.getTipoMoneda(), tipoMonedaUsuario, p.getPrecio());
			if( valor >= precioInicial && valor <= precioFinal ){
				productosEncontrados.add(p);
			}
		}
		return productosEncontrados;
	}

}
