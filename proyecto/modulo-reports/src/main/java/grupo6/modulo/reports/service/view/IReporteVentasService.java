package grupo6.modulo.reports.service.view;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasCiudadDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasFechasDTO;

import java.util.Date;

/**
 * Servcios expuestos por el módulo de reporte ventasS
 *
 * @author Alejo
 *
 */
public interface IReporteVentasService {

	/**
	 * Retorna un reporte de ventas por ciudad
	 * 
	 * @param ciudad
	 * @return un reporte de ventas por ciudad
	 */
	ReporteVentasCiudadDTO getReporteVentasPorCiudad(String ciudad,TipoMoneda tipoMoneda);

	/**
	 * Retorna un reporte de ventas entre fechas
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return reporte de ventas entre fechas
	 */
	ReporteVentasFechasDTO getReporteVentasEntreFechas(Date fechaInicial,Date fechaFinal,TipoMoneda tipoMoneda);

}
