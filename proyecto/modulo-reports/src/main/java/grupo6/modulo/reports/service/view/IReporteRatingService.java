package grupo6.modulo.reports.service.view;

import grupo6.modulo.reports.dao.impl.dto.ReporteVentasCiudadDTO;

/**
 * Servicios expuestos por el módulo de reporte rating
 * @author ralfalk
 *
 */
public interface IReporteRatingService {
	
	/**
	 * Retorna un reporte de rating por producto
	 * 
	 * @param idProducto
	 * @return un reporte de rating por producto
	 */
	ReporteVentasCiudadDTO getReporteRatingPorProducto(int idProducto);

}
