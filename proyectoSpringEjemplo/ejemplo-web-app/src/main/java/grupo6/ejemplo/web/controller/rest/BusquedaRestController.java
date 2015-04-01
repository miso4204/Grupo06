package grupo6.ejemplo.web.controller.rest;

import grupo6.ejemplo.busquedas.service.view.IBusquedasService;
import grupo6.ejemplo.persistencia.entidades.Oferta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador web que procesa servicios REST para el m�dulo de b�squedas.
 * @author caespinosam
 *
 */
@Controller // indica a Spring que es un bean de tipo controlador web
@RequestMapping("/services/busquedas") // indica a Spring que el controlador intercepta url *services/busquedas/*
public class BusquedaRestController {
	
	/** El servicio de b�squedas. **/
	@Autowired // asigna a este atributo el bean de tipo IBusquedasService y que
			   // se llama 'busquedasService' en el contexto de Spring. 
	private IBusquedasService busquedasService;
	
	
	/**
	 * Servicio REST para listar todas las ofertas.
	 * El servicio est� mapeado a la URL /services/busquedas/listas_todas de tipo GET.
	 * 
	 * @return ls lista de todas las ofertas.
	 */
	@RequestMapping(value = "/listas_todas", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Oferta> buscarOfertasTodas() {
		
		return busquedasService.buscarOfertasTodas(); 
	}
	
	
	/**
	 * Servicio REST para buscar ofertas por ciudad.
	 * El servicio est� mapeado a la URL /services/busquedas/buscar_por_ciudad/{string} de tipo GET.
	 * 
	 * @param ciudad la ciudad a filtrar
	 * @return ls lista de todas las ofertas.
	 */
	@RequestMapping(value = "/buscar_por_ciudad/{ciudad}", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Oferta> crOferta(@PathVariable("ciudad") String ciudad) {
		
		return busquedasService.buscarOfertasPorCiudad(ciudad); 
	}


}
