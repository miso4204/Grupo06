package grupo6.modulo.reports.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grupo6.modulo.reports.dao.impl.dto.ReporteVentasCiudadDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasFechasDTO;
import grupo6.modulo.reports.dao.view.IReportesVentasDAO;
import grupo6.modulo.reports.service.view.IReporteRatingService;
import grupo6.modulo.reports.service.view.IReporteVentasService;

/**
 * Implementación de {@link IReporteRatingService}.
 */
@Service(value = "reporteRatingService") 
public class ReporteRatingService implements IReporteRatingService{

	/** DAO de reporte de ventas. */
	@Autowired 
	private IReportesVentasDAO reportesVentasDAO;
	
	/**
	 * @see ReporteRatingService#getReporteRatingPorProducto(String)
	 */
	@Transactional(readOnly = true)
	@Override
	public ReporteVentasCiudadDTO getReporteRatingPorProducto(int idProducto) {
		return reportesVentasDAO.getReporteRatingPorProducto(idProducto);
	}

	

}
