package grupo6.modulo.reports.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasCiudadDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasFechasDTO;
import grupo6.modulo.reports.dao.view.IReportesVentasDAO;
import grupo6.modulo.reports.service.view.IReporteVentasService;

/**
 * Implementación de {@link IReporteVentasService}.
 */
@Service(value = "reporteVentasService") 
public class ReporteVentasService implements IReporteVentasService{

	/** DAO de reporte de ventas. */
	@Autowired 
	private IReportesVentasDAO reportesVentasDAO;
	
	/**
	 * @see ReporteVentasService#getReporteVentasPorCiudad(String)
	 */
	@Transactional(readOnly = true)
	@Override
	public ReporteVentasCiudadDTO getReporteVentasPorCiudad(String ciudad,TipoMoneda tipoMoneda) {
		return reportesVentasDAO.getReporteVentasPorCiudad(ciudad,tipoMoneda);
	}

	/**
	 * @see ReporteVentasService#getReporteVentasEntreFechas(Date, Date)
	 */
	
	@Transactional(readOnly = true)
	@Override
	public ReporteVentasFechasDTO getReporteVentasEntreFechas(Date fechaInicial, Date fechaFinal,TipoMoneda tipoMoneda) {
		return reportesVentasDAO.getReporteVentasEntreFechas(fechaInicial, fechaFinal,tipoMoneda);
	}

	@Override
	public ReporteVentasCiudadDTO getReporteRatingPorProducto(int idProducto) {
		return reportesVentasDAO.getReporteRatingPorProducto(idProducto);
	}

}
