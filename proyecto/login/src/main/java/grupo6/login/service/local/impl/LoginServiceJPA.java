package grupo6.login.service.local.impl;

import grupo6.login.dao.local.view.ILoginDAO;
import grupo6.login.service.view.ILoginService;
import grupo6.persistencia.entidades.Cliente;

/**
 * Implementación JPA de {@link ILoginService}.
 * 
 * @author caespinosam
 *
 */
public class LoginServiceJPA implements ILoginService{

	private ILoginDAO loginDao;
	/**
	 * (non-Javadoc)
	 * @see grupo6.login.service.view.ILoginService#login(java.lang.String, java.lang.String)
	 */
	public boolean login(String usuario, String clave) {
		
		Cliente c = loginDao.buscarCliente(usuario, clave);
		
		return c  != null;
	}

}
