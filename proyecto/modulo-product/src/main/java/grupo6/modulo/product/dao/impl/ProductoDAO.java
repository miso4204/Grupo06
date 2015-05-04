package grupo6.modulo.product.dao.impl;

import grupo6.modulo.product.dao.view.IProductoDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.Actividad;
import grupo6.persistencia.entidades.Alojamiento;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.Vuelo;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link IProductoDAO}.
 */
@Repository(value = "productoDAO") 
public class ProductoDAO extends BaseDAO implements IProductoDAO {


	/**
	 * Metodo que permite buscar un alojamiento por ID returna null si no lo encuentra
	 */
	@Override
	@Transactional(readOnly = true)
	public Alojamiento buscarAlojamientoPorId(Long Id) {

		try {
			return (Alojamiento) getCurrentSession().get(Alojamiento.class, Id);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Metodo que permite buscar vuelo por id
	 */
	@Override
	@Transactional(readOnly = true)
	public Vuelo buscarVueloPorId(Long Id) {
		try {
			System.out.println("______________ " + Id);
			return (Vuelo) getCurrentSession().get(Vuelo.class, Id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Buscar actividad pro id devuelve null si hay error
	 */
	@Override
	@Transactional(readOnly = true)
	public Actividad buscarActividadPorId(Long Id) {

		try {
			return (Actividad) getCurrentSession().get(Actividad.class, Id);
		} catch (Exception e) {
			return null;
		}
	}

	
	/**
	 * Metodo que permite actualizar una actividad
	 */
	@Override
	public boolean actualizarActividad(Actividad actividad) {
		
		try {
			Actividad activi = (Actividad) getCurrentSession().get(Actividad.class, actividad.getId());
			
			if (activi != null) {
				
				activi.setCostoActividad(actividad.getCostoActividad());
				activi.setCostoTotal(actividad.getCostoTotal());
				activi.setDescripcion(actividad.getDescripcion());
				activi.setFechaActividad(actividad.getFechaActividad());
				activi.setNombreActividad(actividad.getNombreActividad());
				activi.setNumPersonas(actividad.getNumPersonas());
				getCurrentSession().update(activi);
				return true;
			} else  {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	/**
	 * Metodo que permite crear una actividad
	 * @param actividad
	 * @return id de la actividad creada
	 */
	@Override
	public Long crearActividad(Actividad actividad) {
		
		return (Long) getCurrentSession().save(actividad);
	}

	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IProductoDAO#crear(grupo6.persistencia.entidades.Producto)
	 */
	@Transactional
	public Long crear(Producto producto) {
		producto.setFechaCreacion(new Date());		
		return (Long)getCurrentSession().save(producto);
	}
	
	/**
	 * Metodo que permite actualizar un alojamiento, permitiendo 
	 * actualizar el numero de noches de hospedaje y el precio total
	 * @param alojamiento
	 */
	public boolean actualizarAlojamiento(Alojamiento alojamiento){
		
		try {
			Alojamiento aloja = (Alojamiento) getCurrentSession().get(Alojamiento.class, alojamiento.getId());
			
			if (aloja != null) {

				aloja.setAireAcondicionado(alojamiento.isAireAcondicionado());
				aloja.setNumeroNoches(alojamiento.getNumeroNoches());
				aloja.setNumMaxPersonas(alojamiento.getNumMaxPersonas());
				aloja.setPiscina(alojamiento.isPiscina());
				aloja.setPrecioPorDia(alojamiento.getPrecioPorDia());
				aloja.setPrecioTotal(alojamiento.getPrecioTotal());
				aloja.setTipo(alojamiento.getTipo());
				aloja.setVigilancia(alojamiento.isVigilancia());
				aloja.setZonasVerdes(alojamiento.isZonasVerdes());
				
				getCurrentSession().update(aloja);
				return true;
			} else  {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * Metodo que permite actualizar un vuelo, permitiendo 
	 * actualizar el numero de personas que viajan y el precio total
	 * @param vuelo
	 */
	@Transactional
	public boolean actualizarVuelo(Vuelo vuelo) {
		
		try {
			Vuelo vue = (Vuelo) getCurrentSession().get(Vuelo.class, vuelo.getId());
			
			if (vue != null) {

				vue.setAerolinea(vuelo.getAerolinea());
				vue.setDestino(vuelo.getDestino());
				vue.setFechaLlegada(vuelo.getFechaLlegada());
				vue.setFechaSalida(vuelo.getFechaSalida());
				vue.setNumPersonas(vuelo.getNumPersonas());
				vue.setOrigen(vuelo.getOrigen());
				vue.setPrecioTotal(vuelo.getPrecioTotal());
				vue.setPrecioVuelo(vuelo.getPrecioVuelo());
				
				getCurrentSession().update(vue);
				return true;
			} else  {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo que permite crear un vuelo
	 * @param vuelo
	 * @return id del vuelo creado
	 */
	@Transactional
	public Long crearVuelo(Vuelo vuelo) {
		return (Long) getCurrentSession().save(vuelo);
	}

	/**
	 * Metodo que permite crear un Alojamiento
	 * @param alojamiento
	 * @return id del alojamiento creado
	 */
	@Transactional
	public Long crearAlojamiento(Alojamiento alojamiento) {
		return (Long) getCurrentSession().save(alojamiento);
	}
	
	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IProductoDAO#buscarPorId(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public Producto buscarPorId(Long id) {
		return (Producto) getCurrentSession().get(Producto.class, id);
	}

	
	/**
	 * (non-Javadoc)
	 * @see grupo6.modulo.product.dao.view.IProductoDAO#listarTodos()
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Producto> listarTodos() {
		Query query = getCurrentSession()
				.createQuery("from Producto");
		query.setCacheable(true);
		return query.list();
	}

	/**
	 * Metodo que nos permite obtener todos los alojamientos
	 * que estan en la BD
	 */
	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public List<Alojamiento> obtenerAlojamientos() {
		
		Query query = getCurrentSession()
				.createQuery("from Alojamiento");
		query.setCacheable(true);
		return query.list();
	}


}
