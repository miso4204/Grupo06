package grupo6.modulo.user.service.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grupo6.modulo.user.dao.view.IUsuarioDAO;
import grupo6.modulo.user.service.impl.IUsuarioService;
import grupo6.persistencia.entidades.Usuario;

/**
 * Clase que permite exponer los metodos como servicios REST
 * @author jhon
 *
 */
@Service(value = "usuarioService")
public class UsuarioService implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuarioDAO; //Inyeccion de dependencias
	
	
	@Transactional(readOnly = true)
	@Override
	public Usuario buscarPorId(long id) {
		return usuarioDAO.buscarPorId(id);
	}

	@Transactional
	@Override
	public long crearUsuario(Usuario user) {
		return usuarioDAO.crearUsuario(user);
	}

	@Transactional(readOnly = true)
	@Override
	public Usuario ingresar(String usuario, String password) {
		return usuarioDAO.ingresar(usuario, password);
	}

	@Transactional
	@Override
	public boolean actualizarUsuario(Usuario user) {
		return usuarioDAO.actualizarUsuario(user);
	}

	@Transactional
	@Override
	public boolean cambiarPassword(String usuario, String passAnterior, String passActual, String passNuevoValidate) throws Exception {
		return usuarioDAO.cambiarPassword(usuario, passAnterior, passActual,passNuevoValidate);
	}

	@Transactional(readOnly = true)
	@Override
	public Usuario buscarUsuarioPorUsername(String username) {
		return usuarioDAO.buscarUsuarioPorUsername(username);
	}


}
