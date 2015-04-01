package grupo6.persistencia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase base con métodos comunes para todos los DAOs.
 */
public class BaseDAO {
	
	/**
	 * SessionFactory de Hibernate.
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
