package grupo6.modulo.reports.dao.impl;

import java.util.Date;

import grupo6.modulo.reports.dao.impl.dto.ReporteVentasCiudadDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasFechasDTO;
import grupo6.modulo.reports.dao.view.IReportesVentasDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.FacturaCompra;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link IReportesVentasDAO}
 * 
 * @author Alejo
 *
 */
@Repository(value = "reporteVentasDAO")
public class ReporteVentasDAO extends BaseDAO implements IReportesVentasDAO {

	/**
	 * @see ReporteVentasDAO#getReporteVentasPorCiudad(String)
	 */
	@Transactional(readOnly = true)
	@Override
	public ReporteVentasCiudadDTO getReporteVentasPorCiudad(String ciudad) {
		Criteria criteriaTotalVentas = getCurrentSession().createCriteria(FacturaCompra.class);
		criteriaTotalVentas.setProjection(Projections.rowCount());
		Criteria productosCriteria = criteriaTotalVentas.createCriteria("productosComprados");
		productosCriteria.add(Restrictions.eq("ciudad", ciudad));
		
		long totalVentas = (long) criteriaTotalVentas.list().get(0);

		Criteria criteriaTotalDineroVentas = getCurrentSession().createCriteria(FacturaCompra.class);
		criteriaTotalDineroVentas.setProjection(Projections.sum("precio"));
		Criteria productosCriteriaDinero = criteriaTotalDineroVentas.createCriteria("productosComprados");
		productosCriteriaDinero.add(Restrictions.eq("ciudad", ciudad));
		
		double totalDineroEnVentas = (double) criteriaTotalDineroVentas.list().get(0);
		
		return new ReporteVentasCiudadDTO(ciudad,totalVentas, totalDineroEnVentas);
	}

	/**
	 * @see ReporteVentasDAO#getReporteVentasEntreFechas(Date, Date)
	 */
	@Transactional(readOnly = true)
	@Override
	public ReporteVentasFechasDTO getReporteVentasEntreFechas(
			Date fechaInicial, Date fechaFinal) {

		Criteria criteriaTotalVentas = getCurrentSession().createCriteria(FacturaCompra.class);
		criteriaTotalVentas.setProjection(Projections.rowCount());
		criteriaTotalVentas.add(Restrictions.ge("fechaPago", fechaInicial)); 
		criteriaTotalVentas.add(Restrictions.le("fechaPago", fechaFinal));
		criteriaTotalVentas.createCriteria("productosComprados");
		
		
		int totalVentas = (int) criteriaTotalVentas.list().get(0);

		Criteria criteriaTotalDineroVentas = getCurrentSession().createCriteria(FacturaCompra.class);
		criteriaTotalDineroVentas.add(Restrictions.ge("fechaPago", fechaInicial)); 
		criteriaTotalDineroVentas.add(Restrictions.le("fechaPago", fechaFinal));
		criteriaTotalDineroVentas.setProjection(Projections.sum("precio"));
		criteriaTotalDineroVentas.createCriteria("productosComprados");
		
		double totalDineroEnVentas = (double) criteriaTotalDineroVentas.list().get(0);
		
		return new ReporteVentasFechasDTO(fechaInicial, fechaFinal, totalVentas, totalDineroEnVentas);
		
	}

}
