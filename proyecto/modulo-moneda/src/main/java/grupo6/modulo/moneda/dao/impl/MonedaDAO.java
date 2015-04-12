package grupo6.modulo.moneda.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import grupo6.modulo.moneda.dao.view.IMonedaDAO;
import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Usuario;

/**
 * Implementación de {@link IMonedaDAO}
 * 
 * @author Alejo
 *
 */
@Repository(value = "monedaDAO")
public class MonedaDAO extends BaseDAO implements IMonedaDAO{

	private static final String USER_NAME = "usuario";
	
	/**
	 * Busca un Usuario por userName
	 * 
	 * @param userName
	 * @return el usuario encontrado o null si no se encontro
	 */
	private Usuario buscarUsuarioPorUserName(String userName) {
		Criteria criteria = getCurrentSession().createCriteria(Usuario.class);
		criteria.add(Restrictions.eq(USER_NAME, userName));
		return (Usuario) criteria.uniqueResult();
	}
	
	
	/**
	 * {@link IMonedaDAO}{@link #getTipoMoneda(String)
	 */
	@Override
	@Transactional
	public TipoMoneda getTipoMoneda(String userName) {
		Usuario usuario = buscarUsuarioPorUserName(userName);
		if(usuario != null){
			return usuario.getTipoMoneda();
		}
		
		return null;
	}
	
	/**
	 * {@link IMonedaDAO}{@link #setTipoMoneda(String, TipoMoneda)}
	 */
	@Override
	@Transactional
	public Boolean setTipoMoneda(String userName, TipoMoneda tipoMoneda) {
		Usuario usuario = buscarUsuarioPorUserName(userName);
		if(usuario != null){
			usuario.setTipoMoneda(tipoMoneda);
			getCurrentSession().update(usuario);
			return true;
		}
		
		return false;
	}
}
