package grupo6.modulo.reports.service.impl;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.product.factory.IBusquedaProducto;
import grupo6.modulo.product.service.view.IProductoService;
import grupo6.modulo.reports.dao.impl.dto.ReporteRatingPorCiudadProductoDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteRatingProductoPaqueteDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasCiudadDTO;
import grupo6.modulo.reports.dao.impl.dto.ReporteVentasFechasDTO;
import grupo6.modulo.reports.dao.view.IReportesVentasDAO;
import grupo6.modulo.reports.service.view.IReporteVentasService;
import grupo6.modulo.utilidades.anotaciones.Feature;
import grupo6.persistencia.entidades.ETipoRating;
import grupo6.persistencia.entidades.Producto;
import grupo6.persistencia.entidades.RatingProducto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de {@link IReporteVentasService}.
 */
@Service(value = "reporteVentasService") 
public class ReporteVentasService implements IReporteVentasService{

	/** DAO de reporte de ventas. */
	@Autowired 
	private IReportesVentasDAO reportesVentasDAO;
	
	@Autowired 
	private IProductoService productoService;
	
	@Autowired
	private IBusquedaProducto busquedaProductoUbicacion;
	@Autowired
	private IBusquedaProducto busquedaProductoPaquete;
	
	/**
	 * @see ReporteVentasService#getReporteVentasPorCiudad(String)
	 */
	@Transactional(readOnly = true)
	@Override
	@Feature(nombreNodo = "ReportByLocation")
	public ReporteVentasCiudadDTO getReporteVentasPorCiudad(String ciudad,TipoMoneda tipoMoneda) {
		return reportesVentasDAO.getReporteVentasPorCiudad(ciudad,tipoMoneda);
	}

	/**
	 * @see ReporteVentasService#getReporteVentasEntreFechas(Date, Date)
	 */
	
	@Feature(nombreNodo = "ReportByPeriod")
	@Transactional(readOnly = true)
	@Override
	public ReporteVentasFechasDTO getReporteVentasEntreFechas(Date fechaInicial, Date fechaFinal,TipoMoneda tipoMoneda) {
		return reportesVentasDAO.getReporteVentasEntreFechas(fechaInicial, fechaFinal,tipoMoneda);
	}

	@Feature(nombreNodo = "Location")
	@Override
	public List<ReporteRatingPorCiudadProductoDTO> getReporteRatingPorCiudad(
			String ciudad) {
		List<ReporteRatingPorCiudadProductoDTO> productosPorCiudad = new ArrayList<>(); 
		List<Producto> productos = busquedaProductoUbicacion.buscar(ciudad);
		String nombreProducto = "";
		double calificacionProducto = 0;
		int votantes =0;
		if (productos != null) {
			for (Producto p : productos) {
				nombreProducto = p.getNombre();
				List<RatingProducto> ratings = productoService
						.buscarRatingPorProductoId(p.getId());
				for (RatingProducto rating : ratings) {
					if (rating.getTipoServicio() == ETipoRating.GENERAL) {
						calificacionProducto = productoService
								.obtenerCalificacionDeServicio(rating.getId());
						votantes = 
							     productoService.obtenerNumeroVotantesDeServicio(rating.getId());
						productosPorCiudad.add(new ReporteRatingPorCiudadProductoDTO(ciudad, nombreProducto,
								calificacionProducto,votantes));
					}

				}
			}
		}
		return productosPorCiudad;
	}

	@Override
	@Feature(nombreNodo = "Package")
	public List<ReporteRatingProductoPaqueteDTO> getReporteRatingPorNombrePaquete(String nombrePaquete) {
		List<ReporteRatingProductoPaqueteDTO> productosPorPaquete = new ArrayList<>(); 
		List<Producto> productos = busquedaProductoPaquete.buscar(nombrePaquete);
		String nombreProducto = "";
		double calificacionProducto = 0;
		int votantes =0;
		if (productos != null) {
			for (Producto p : productos) {
				nombreProducto = p.getNombre();
				List<RatingProducto> ratings = productoService
						.buscarRatingPorProductoId(p.getId());
				for (RatingProducto rating : ratings) {
					if (rating.getTipoServicio() == ETipoRating.GENERAL) {
						calificacionProducto = productoService
								.obtenerCalificacionDeServicio(rating.getId());
						votantes = 
							     productoService.obtenerNumeroVotantesDeServicio(rating.getId());
						productosPorPaquete.add(new ReporteRatingProductoPaqueteDTO(nombrePaquete,
								calificacionProducto,votantes));
					}

				}
			}
		}
		return productosPorPaquete;
	}
	
public static void main(String...strings ) {
	ReporteVentasService s = new ReporteVentasService();
	System.out.print("a"); 
}
}
