package grupo6.modulo.user.dao.view;

import grupo6.persistencia.entidades.Usuario;


/**
 * Interface que define el contrato de los metodos
 * que van a ser implementados por el DAO de usuairo
 * @author jhon
 *
 */
public interface IUsuarioDAO {

	
	/**
	 * Metodo que permite buscar un usuario mediante su id
	 * @param id
	 * @return
	 */
	public Usuario buscarPorId(long id);
	
	/**
	 * Metodo que permite crear un usuario en la bd
	 * @param user
	 * @return id del usuario creado
	 */
	public long crearUsuario(Usuario user);
	
	
	/**
	 * Metodo que permite validar el nombre de usuario y 
	 * contrasennia de un usuario contra la BD
	 * @param usuario
	 * @param password
	 * @return true si existe un usuario con estas caracteristicas y false si no
	 */
	public Usuario ingresar(String usuario, String password);
	
	/**
	 * Metodo que nos permite actualizar la informacion de un usuario en BD
	 * @param user
	 * @return true si la informacion se actualizo con exito y false si no
	 */
	public boolean actualizarUsuario(Usuario user);
	
	/**
	 * Metodo que permite cambiar a un usuario su contrasennia
	 * @param passAnterior
	 * @param passActual
	 * @param passActualValidate repetición del password para actualizar
	 * @return true si el cambio de contrasennia fue exitoso y false si no
	 */
	public boolean cambiarPassword(String usuario, String passAnterior, String passActual, String passActualValidate) throws Exception;
	
	/**
	 * Metodo que nos pemrite buscar un usuario por su nombre de usuario
	 * @param username
	 * @return Usuario usuario encontrado mediante su nombre d eusuario
	 */
	public Usuario buscarUsuarioPorUsername (String username);
	
}
