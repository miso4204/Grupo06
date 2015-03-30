package grupo6.ejemplo.persistencia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase base con métodos comunes para todos los DAOs.
 */
public class BaseDAO {
	
	/**
	 * Se inyecta el sessionFactory de Hibernate y que es llamado "sessionFactory"
	 * en el contexto de Spring.
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Se crea una sesión de Hibernate.
	 * 
	 * @return uan sesión de Hibernate.
	 */
	protected  Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
