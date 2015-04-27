package grupo6.modulo.reports.dao.view;

import java.util.Date;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.reports.dao.impl.dto.ReporteRatingPorCiudadProductoDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteRatingProductoFechasDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasCiudadDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasFechasDTO;


/**
 * Metodo de acceso a los datos de reportes de ventas.
 * 
 * @author Alejo
 *
 */
public interface IReportesVentasDAO {

	/**
	 * Retorna un reporte de ventas por ciudad
	 * @param ciudad
	 * @return un reporte de ventas por ciudad
	 */
	ReporteVentasCiudadDTO getReporteVentasPorCiudad(String ciudad,TipoMoneda tipoMoneda);
	
	/**
	 * Retorna un reporte de ventas entre fechas
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return reporte de ventas entre fechas
	 */
	ReporteVentasFechasDTO getReporteVentasEntreFechas(Date fechaInicial,Date fechaFinal,TipoMoneda tipoMoneda);
	
	
	
}
