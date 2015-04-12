package grupo6.modulo.product.util;

import grupo6.modulo.product.dao.view.IBusquedaProducto;

import java.util.Map;

/**
 * Fabrica de objetos encargados de buscar productos.
 * 
 * @author caespinosam
 *
 */

public class BusquedaProductosFactory {

	/** Referencias de los buscadores disponibles. **/	
	private static Map<String, IBusquedaProducto> buscadores;

	/**
	 * Retorna el buscador apropiado para el tipo enviado.
	 * 
	 * @param tipo
	 *            el tipo de buscador.
	 * @return buscador asociado al tipo. Excepcion si no se encuentra alguno.
	 */
	public static IBusquedaProducto crearBuscador(ETipoBusqueda tipo) {
		
		IBusquedaProducto buscador = null;
		if (tipo == ETipoBusqueda.POR_FECHA) {
			buscador = buscadores.get("busquedaProductoFecha");
		} else if (tipo == ETipoBusqueda.POR_PRECIO) {
			buscador = buscadores.get("busquedaProductoPrecio");
		} else if (tipo == ETipoBusqueda.POR_UBICACION) {
			buscador = buscadores.get("busquedaProductoUbicacion");
		}

		if (buscador == null) {
			throw new IllegalArgumentException("Buscador no encontrado");
		}

		return buscador;
	}

	
	/**
	 * Establece el mapa de buscadores. 
	 * @param buscadores el mapa de buscadores. 
	 */
	public static void setBuscadores(Map<String, IBusquedaProducto> buscadores) {
		BusquedaProductosFactory.buscadores = buscadores;
	}
	
	
}
