package grupo6.login.dao.local.view;

import grupo6.persistencia.entidades.Cliente;

/**
 * Define los metodos de acceso a datos.
 * @author caespinosam
 *
 */
public interface ILoginDAO {

	/**
	 * Busca cliente por usuario y clave.
	 * 
	 * @param username el usuario.
	 * @param clave la clave.
	 * @return Objeto cliente econtrado, null si no existe.
	 */
	Cliente buscarCliente(String username, String clave) ;
}
