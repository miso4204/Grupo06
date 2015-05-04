package grupo6.modulo.product.dao.view;

import grupo6.persistencia.entidades.Actividad;
import grupo6.persistencia.entidades.Alojamiento;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.Vuelo;

import java.util.List;

/**
 * Metodos de acceso a datos.
 * 
 * @author caespinosam
 * 
 */
public interface IProductoDAO {
	
	/**
	 * Metodo que permite realizar la busqueda de un alojamiento por  id
	 * @param Id
	 * @return alojamiento encontrado mediante el id o null si no lo encuentra
	 */
	Alojamiento buscarAlojamientoPorId(Long Id);
	
	/**
	 * Metodo que permite realizar la busqueda de un vuelo por  id
	 * @param Id
	 * @return alojamiento encontrado mediante el id o null si no lo encuentra
	 */
	Vuelo buscarVueloPorId(Long Id);
	
	/**
	 * Metodo que permite realizar la busqueda de una actividad por  id
	 * @param Id
	 * @return actividad encontrada mediante el id o null si no lo encuentra
	 */
	Actividad buscarActividadPorId(Long Id);
	
	/**
	 * Metodo que retorna todos los alojamientos
	 * @return todos los alojamientos de que esten en la BD
	 */
	List<Alojamiento> obtenerAlojamientos();

	
	/**
	 * Metodo que permite actualizar un alojamiento, permitiendo 
	 * actualizar el numero de noches de hospedaje y el precio total
	 * @param alojamiento
	 */
	boolean actualizarAlojamiento(Alojamiento alojamiento);
	
	/**
	 * Metodo que permite actualizar un vuelo, permitiendo 
	 * actualizar el numero de personas que viajan y el precio total
	 * @param vuelo
	 */
	boolean actualizarVuelo(Vuelo vuelo);
	
	/**
	 * Metodo que permite actualizar una actividad
	 */
	boolean actualizarActividad(Actividad actividad); 
	
	/**
	 * Metodo que permite crear una actividad
	 * @param actividad
	 * @return id de la actividad creada
	 */
	Long crearActividad(Actividad actividad); 
	
	/**
	 * Metodo que permite crear un vuelo
	 * @param vuelo
	 * @return id del vuelo creado
	 */
	Long crearVuelo(Vuelo vuelo); 
	
	
	/**
	 * Metodo que permite crear un Alojamiento
	 * @param alojamiento
	 * @return id del alojamiento creado
	 */
	Long crearAlojamiento(Alojamiento alojamiento);
	
	/**
	 * Crear un producto.
	 * @param producto el producto a crear.
	 * @return el id del producto creado.
	 */
	Long crear(Producto producto);

	/**
	 * Buscar un producto por llame primaria.
	 * @param id la llave a buscar.
	 * @return el producto, null en caso que no exista.
	 */
	Producto buscarPorId(Long id);

	
	/**
	 * Retorna todos los productos o paquetes del sistema.
	 * @return todos los productos o paquetes del sistema.
	 */
	List<Producto> listarTodos();


}
