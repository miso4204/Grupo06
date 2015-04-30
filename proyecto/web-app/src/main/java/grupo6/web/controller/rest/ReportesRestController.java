package grupo6.web.controller.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.web.dto.ProductoResponseDTO;
import grupo6.web.dto.reportes.ReporteRatingPorCiudadProductoDTO;
import grupo6.modulo.reports.service.view.IReporteVentasService;
import grupo6.persistencia.entidades.Producto;
import grupo6.web.dto.reportes.ReporteRatingProductoPaqueteDTO;
import grupo6.web.dto.reportes.ReporteVentasCiudadDTO;
import grupo6.web.dto.reportes.ReporteVentasFechasDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador REST de los servicios de reportes.
 * 
 * @author Alejo
 *
 */
@Controller
@RequestMapping("/reportes")
public class ReportesRestController extends BaseRestController {

	/** Servicios de reportes de ventas. */
	@Autowired
	@Qualifier("reporteVentasServiceProxy")
	private IReporteVentasService reporteVentasService;
	
	

	/**
	 * Servicio REST para ver el reporte de ventas por ciudad.
	 * 
	 * @return reporte de ventas por ciudad.
	 */
	@RequestMapping(value = "/ventas/{ciudad}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReporteVentasCiudadDTO getReporteVentaPorCiudad(
			@PathVariable("ciudad") String ciudad,
			@RequestHeader(value = "tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		return ReporteVentasCiudadDTO.getReporteVentas(reporteVentasService
				.getReporteVentasPorCiudad(ciudad, tipoMoneda));
	}

	/**
	 * Servicio REST para ver el reporte de ventas entre fechas.
	 * 
	 * @return reporte de ventas entre fechas.
	 */
	@RequestMapping(value = "/ventas/{fechaInicial}/{fechaFinal}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReporteVentasFechasDTO getReporteVentaEntreFechas(
			@PathVariable("fechaInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicial,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("fechaFinal") Date fechaFinal,
			@RequestHeader(value = "tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		return ReporteVentasFechasDTO.getReporteVentas(reporteVentasService
				.getReporteVentasEntreFechas(fechaInicial, fechaFinal,
						tipoMoneda)); 
	}

	/**
	 * Servicio REST para ver el reporte de ventas entre fechas.
	 * 
	 * @return reporte de ratings de productos.
	 */
	@RequestMapping(value = "/rating/{ciudadProducto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<grupo6.modulo.reports.dao.impl.dto.ReporteRatingPorCiudadProductoDTO> getReporteRatingPorProducto(@PathVariable("ciudadProducto") String ciudadProducto) {	
			List<grupo6.modulo.reports.dao.impl.dto.ReporteRatingPorCiudadProductoDTO> productos = reporteVentasService.getReporteRatingPorCiudad(ciudadProducto);
			return productos;
	}
	
	/**
	 * Servicio REST para ver el reporte de ventas entre fechas.
	 * 
	 * @return reporte de ratings de productos.
	 */
	@RequestMapping(value = "/ratingPaquete/{nombrePaquete}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<grupo6.modulo.reports.dao.impl.dto.ReporteRatingProductoPaqueteDTO> getReporteRatingPorNombrePaquete(@PathVariable("nombrePaquete") String nombrePaquete) {	
			List<grupo6.modulo.reports.dao.impl.dto.ReporteRatingProductoPaqueteDTO> productos = reporteVentasService.getReporteRatingPorNombrePaquete(nombrePaquete);
			return productos;
	}



}
