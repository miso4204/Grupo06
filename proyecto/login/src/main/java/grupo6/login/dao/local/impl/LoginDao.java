package grupo6.login.dao.local.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import grupo6.login.dao.local.view.ILoginDAO;
import grupo6.persistencia.entidades.Cliente;

public class LoginDao implements ILoginDAO {

	/** Manejador de persistencia.*/
	@PersistenceContext
	EntityManager em;
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.login.dao.local.view.ILoginDAO#buscarCliente(java.lang.String, java.lang.String)
	 */
	public Cliente buscarCliente(String username, String clave) {
		/// em.find(Cliente.class, arg1);
		return null;
	}

}
