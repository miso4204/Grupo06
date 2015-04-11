package grupo6.modulo.reports.dao.impl;

import java.util.Date;
import java.util.List;

import grupo6.modulo.reports.dao.impl.dto.ReporteVentasCiudadDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasFechasDTO;
import grupo6.modulo.reports.dao.view.IReportesVentasDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.FacturaCompra;
import grupo6.persistencia.entidades.Producto;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link IReportesVentasDAO}
 * 
 * @author Alejo
 *
 */
@SuppressWarnings("unchecked")
@Repository(value = "reporteVentasDAO")
public class ReporteVentasDAO extends BaseDAO implements IReportesVentasDAO {

	private static final String FECHA_PAGO = "fechaPago";

	/**
	 * @see ReporteVentasDAO#getReporteVentasPorCiudad(String)
	 */
	@Transactional(readOnly = true)
	@Override
	public ReporteVentasCiudadDTO getReporteVentasPorCiudad(String ciudad) {
		Criteria criteria = getCurrentSession().createCriteria(
				FacturaCompra.class);

		long totalVentas = 0;
		double totalDineroEnVentas = 0;

		if (criteria.list() != null) {
			List<FacturaCompra> facturas = criteria.list();
			for (FacturaCompra f : facturas) {
				if (f.getProductosComprados() != null) {
					for (Producto p : f.getProductosComprados()) {
						if (p.getCiudad().equalsIgnoreCase(ciudad) 
								|| p.getLugar().equalsIgnoreCase(ciudad)) {
							totalDineroEnVentas += p.getPrecio();
							totalVentas++;
						}
					}
				}

			}
		}

		return new ReporteVentasCiudadDTO(ciudad, totalVentas,
				totalDineroEnVentas);
	}

	/**
	 * @see ReporteVentasDAO#getReporteVentasEntreFechas(Date, Date)
	 */
	@Transactional(readOnly = true)
	@Override
	public ReporteVentasFechasDTO getReporteVentasEntreFechas(
			Date fechaInicial, Date fechaFinal) {

		Criteria criteria = getCurrentSession().createCriteria(FacturaCompra.class);
		criteria.add(Restrictions.ge(FECHA_PAGO, fechaInicial));
		criteria.add(Restrictions.le(FECHA_PAGO, fechaFinal));

		long totalVentas = 0;
		double totalDineroEnVentas = 0;

		if (criteria.list() != null) {
			List<FacturaCompra> facturas = criteria.list();
			for (FacturaCompra f : facturas) {
				if (f.getProductosComprados() != null) {
					for (Producto p : f.getProductosComprados()) {
						totalDineroEnVentas += p.getPrecio();
						totalVentas++;
					}
				}

			}
		}

		return new ReporteVentasFechasDTO(fechaInicial, fechaFinal,
				totalVentas, totalDineroEnVentas);

	}

}
