package grupo6.login.dao.local.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		Query loginQuery = em.createQuery("SELECT c FROM Cliente c WHERE c.usuario = :usuario and c.clave = :clave",Cliente.class);
		loginQuery.setParameter("usuario", username);
		loginQuery.setParameter("clave", clave);
		
		Cliente cliente = (Cliente) loginQuery.getSingleResult();
		
		return cliente;
	}

}
