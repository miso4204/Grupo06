package grupo6.modulo.user.dao.impl;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
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
		
		user.setTipoMoneda(TipoMoneda.DOLAR); // Asignamos por defecto el tipo de moneda como DOLAR para que luego se cambie
		return (long) getCurrentSession().save(user);
	}

	/**
	 * @see IUsuarioDAO
	 */
	@Override
	@Transactional
	public Usuario ingresar(String usuario, String password) {
		
		Criteria criteria = getCurrentSession().createCriteria(Usuario.class);		
		criteria.add(Restrictions.eq("usuario", usuario)); 
		criteria.add(Restrictions.eq("password", password));	
		Usuario user = (Usuario) criteria.uniqueResult();
				
		if (user != null) {
			return user;
		}
		
		return null;
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
			
			throw new IllegalArgumentException("Username no existe");			
		}
	}

	@Transactional
	@Override
	public boolean cambiarPassword(String usuario, String passAnterior, String passNuevo, String passNuevoValidate) throws Exception {
		
		if (passNuevo != null && !passNuevo.equals("")){
			if (passNuevoValidate != null && !passNuevoValidate.equals("")) {
				if (passNuevo.equals(passNuevoValidate)) {
					
					Usuario usuarioEncontrado = this.buscarUsuarioPorUsername(usuario);
					
					if (usuarioEncontrado != null && 
							passAnterior.equals(usuarioEncontrado.getPassword())) {
						
						usuarioEncontrado.setPassword(passNuevo);
						getCurrentSession().update(usuarioEncontrado);
						return true;
					}else {						
						throw new IllegalArgumentException("Su contraseña actual introducida no coincide con la almacenada en nuestro sistema");
					}
				}else {
					throw new IllegalArgumentException("La validación de la nueva clave no coincide");
				}
			}else {
				throw new IllegalArgumentException("La validación de la nueva clave no puede estar vacia");
			}
		}else {
			throw new IllegalArgumentException("Nueva clave no puede estar vacia");
		}

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
		criteria.add(Restrictions.eq("usuario", username)); 	
		Usuario usuarioEncontrado = (Usuario) criteria.uniqueResult();
		
		return usuarioEncontrado;
	}

}
