package grupo6.ejemplo.web.controller.rest;

import java.util.Date;

import grupo6.ejemplo.ofertas.service.view.IOfertaService;
import grupo6.ejemplo.persistencia.entidades.Oferta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador web que procesa servicios REST para el m�dulo de ofertas.
 * @author caespinosam
 *
 */
@Controller // indica a Spring que es un bean de tipo controlador web
@RequestMapping("/services/ofertas") // indica a Spring que el controlador intercepta url *services/ofertas/*
public class OfertasRestController {

	/** El servicio de ofertas. **/
	@Autowired // asigna a este atributo el bean de tipo IOfertaService y que
			   // se llama 'ofertaService' en el contexto de Spring. 
	private IOfertaService ofertaService;
	
	
	/**
	 * Servicio REST para crear una oferta.
	 * El servicio est� mapeado a la URL services/ofertas/crear de tipo POST.
	 * La peticion POST debe enviar como par�metro un string JSON
	 * con los datos de la oferta a crear.
	 * @param oferta la objeto cerado a partir del JSON de la petici�n.
	 * @return el id generado para la nueva oferta.
	 */
	@RequestMapping(value = "/crear", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long crearOferta(@RequestBody Oferta oferta) {
		
		oferta.setFechaFin(new Date());
		return ofertaService.crearOferta(oferta);
 
	}


}
