package grupo6.modulo.payment.strategy;

import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Usuario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

/**
 * Contiene metodos base para las estrategias de pago. 
 * @author caespinosam
 *
 */
public class PaymentBaseStrategy extends BaseDAO {
	
	/**
	 * Busca un Usuario por userName
	 * 
	 * @param userName
	 * @return el usuario encontrado o null si no se encontro
	 */
	@Transactional(readOnly = true)
	protected  Usuario buscarUsuarioPorUserName(String userName) {
		Criteria criteria = getCurrentSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("usuario", userName));
		return (Usuario) criteria.uniqueResult();
	}
	
	/**
	 * Limpia el carrito de compras al pagar exitosamente
	 * 
	 * @param userCompra
	 */
	protected void limpiarCarritoCompras(Usuario userCompra) {
		// Limpiar el carrito de compras
		userCompra.getCarritoCompras().clear();
		getCurrentSession().update(userCompra);
	}

}
