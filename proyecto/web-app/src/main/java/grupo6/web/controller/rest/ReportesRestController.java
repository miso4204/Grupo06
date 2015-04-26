package grupo6.web.controller.rest;

import java.util.Date;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.web.dto.reportes.ReporteRatingProductoDTO;
import grupo6.modulo.reports.service.view.IReporteVentasService;
import grupo6.web.dto.reportes.ReporteVentasCiudadDTO;
import grupo6.web.dto.reportes.ReporteVentasFechasDTO;

import org.springframework.beans.factory.annotation.Autowired;
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
 * @author Alejo
 *
 */
@Controller 
@RequestMapping("/reportes")
public class ReportesRestController extends BaseRestController {

	/** Servicios de reportes de ventas.*/
	@Autowired 
	private IReporteVentasService reporteVentasService;
	
	/**
	 * Servicio REST para ver el reporte de ventas por ciudad.
	 * 
	 * @return reporte de ventas por ciudad.
	 */
	@RequestMapping(value = "/ventas/{ciudad}", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReporteVentasCiudadDTO getReporteVentaPorCiudad(@PathVariable("ciudad") String ciudad,
			                         @RequestHeader(value="tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		return ReporteVentasCiudadDTO.getReporteVentas(reporteVentasService.getReporteVentasPorCiudad(ciudad,tipoMoneda));
	}
	
	/**
	 * Servicio REST para ver el reporte de ventas entre fechas.
	 * 
	 * @return reporte de ventas entre fechas.
	 */
	@RequestMapping(value = "/ventas/{fechaInicial}/{fechaFinal}", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReporteVentasFechasDTO getReporteVentaEntreFechas(
			@PathVariable("fechaInicial") 
			@DateTimeFormat(pattern="yyyy-MM-dd")
			Date fechaInicial,
			@DateTimeFormat(pattern="yyyy-MM-dd")
			@PathVariable("fechaFinal") 
			Date fechaFinal,
			@RequestHeader(value="tipoMoneda", required = false) TipoMoneda tipoMoneda) {
		return ReporteVentasFechasDTO.getReporteVentas(reporteVentasService.getReporteVentasEntreFechas(fechaInicial, fechaFinal,tipoMoneda));
	}
	
	/**
	 * Servicio REST para ver el reporte de ventas entre fechas.
	 * 
	 * @return reporte de ratings de productos.
	 */
	@RequestMapping(value = "/ventas/{idProducto}", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ReporteRatingProductoDTO getReporteRatingPorProducto(@PathVariable("idProducto")  int idProducto)
          {
		return ReporteRatingProductoDTO.getReporteRating(reporteVentasService.getReporteRatingPorProducto(idProducto));
	}
}
