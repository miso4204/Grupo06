package grupo6.modulo.user.dao.impl;

import grupo6.modulo.user.dao.view.IUsuarioDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Usuario;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase que permite implementar la logica necesaria para
 * la manipulacion de informacion del usuario en bd
 * @author jhon
 *
 */
@Repository(value="usuarioDAO")
public class UsuarioDAO extends BaseDAO implements IUsuarioDAO {

	/**
	 * Constructor por defecto
	 */
	public UsuarioDAO() {
	}

	/**
	 * @see IUsuarioDAO
	 */
	@Override
	@Transactional
	public long crearUsuario(Usuario user) {
		
		return (long) getCurrentSession().save(user);
	}

	/**
	 * @see IUsuarioDAO
	 */
	@Override
	@Transactional
	public boolean ingresar(String usuario, String password) {
		
		Criteria criteria = getCurrentSession().createCriteria(Usuario.class);		
		criteria.add(Restrictions.eq("user", usuario)); 
		criteria.add(Restrictions.eq("pass", password));	
		Usuario user = (Usuario) criteria.uniqueResult();
		
		if (user != null) {
			return true;
		}
		
		return false;
	}

	/**
	 * Aqui no se puede actualizar el password!!
	 * @throws Exception 
	 * @see IUsuarioDAO
	 */
	@Transactional
	@Override
	public boolean actualizarUsuario(Usuario user) {
		
		Usuario usuarioEncontrado = this.buscarUsuarioPorUsername(user.getUsuario());
		
		if (usuarioEncontrado != null) {
			
			usuarioEncontrado.setDireccion(user.getDireccion());
			usuarioEncontrado.setNombre(user.getNombre());
			usuarioEncontrado.setTelefono(user.getTelefono());
			getCurrentSession().update(usuarioEncontrado);
			
			return true;
		} else {
			
			System.out.println("El usuario no existe en la bd username = " + user.getUsuario());
			return false;
		}
	}

	@Transactional
	@Override
	public boolean cambiarPassword(String usuario, String passAnterior, String passNuevo) {
		
		Usuario usuarioEncontrado = this.buscarUsuarioPorUsername(usuario);
		
		if (usuarioEncontrado != null && 
				passAnterior.equals(usuarioEncontrado.getPassword())) {
			
			usuarioEncontrado.setPassword(passNuevo);
			getCurrentSession().update(usuarioEncontrado);
		}
		
		return false;
	}

	/**
	 * @see IUsuarioDAO
	 */
	@Transactional(readOnly=true)
	@Override
	public Usuario buscarPorId(long id) {
		
		return (Usuario) getCurrentSession().get(Usuario.class, id);
	}

	/**
	 * @see IUsuarioDAO
	 */
	@Transactional(readOnly=true)
	@Override
	public Usuario buscarUsuarioPorUsername (String username) {
		
		Criteria criteria = getCurrentSession().createCriteria(Usuario.class);		
		criteria.add(Restrictions.eq("user", username)); 	
		Usuario usuarioEncontrado = (Usuario) criteria.uniqueResult();
		
		return usuarioEncontrado;
	}

}
