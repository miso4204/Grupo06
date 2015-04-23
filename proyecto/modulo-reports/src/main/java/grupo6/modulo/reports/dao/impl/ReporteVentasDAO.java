package grupo6.modulo.reports.dao.impl;

import java.util.Date;
import java.util.List;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.product.dao.impl.RatingProductoDAO;
import grupo6.modulo.reports.dao.impl.dto.ReporteRatingProductoDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasCiudadDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasFechasDTO;
import grupo6.modulo.reports.dao.view.IReportesVentasDAO;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.persistencia.entidades.FacturaCompra;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;
import grupo6.utilidades.Currency;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	RatingProductoDAO ratingProductoDAO;
	private static final String FECHA_PAGO = "fechaPago";

	/**
	 * @see ReporteVentasDAO#getReporteVentasPorCiudad(String)
	 */
	@Transactional(readOnly = true)
	@Override
	public ReporteVentasCiudadDTO getReporteVentasPorCiudad(String ciudad,TipoMoneda tipoMonedaUsuario) {
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
							
							double valor = Currency.getConversion(p.getTipoMoneda(), tipoMonedaUsuario, p.getPrecio());
							totalDineroEnVentas += valor;
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
			Date fechaInicial, Date fechaFinal,TipoMoneda tipoMonedaUsuario) {

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
						double valor = Currency.getConversion(p.getTipoMoneda(), tipoMonedaUsuario, p.getPrecio());
						totalDineroEnVentas += valor;
						totalVentas++;
					}
				}

			}
		}

		return new ReporteVentasFechasDTO(fechaInicial, fechaFinal,
				totalVentas, totalDineroEnVentas);

	}

	@Override
	public ReporteRatingProductoDTO getReporteRatingPorProducto(int idProducto) {
		Criteria criteria = getCurrentSession().createCriteria(
				RatingProducto.class);
		
		long rating = 0;
		
		if (criteria.list() != null) {
			List<RatingProducto> producto = criteria.list();
			for (RatingProducto f : producto) {
				if (f.getProductoId() != null) {
					ratingProductoDAO.buscarCalificacionesDeServicio(f.getProductoId());
					
//					for (Producto p : f.getProductosComprados()) {
//						if (p.getCiudad().equalsIgnoreCase(ciudad) 
//								|| p.getLugar().equalsIgnoreCase(ciudad)) {
//							
//							double valor = Currency.getConversion(p.getTipoMoneda(), tipoMonedaUsuario, p.getPrecio());
//							totalDineroEnVentas += valor;
//							totalVentas++;
//						}
//					}
				}

			}
		}

		return new ReporteRatingProductoDTO(idProducto, rating);
	}

}
