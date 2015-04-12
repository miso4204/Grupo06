package grupo6.web.controller.rest;

import grupo6.modulo.moneda.service.view.IMonedaService;
import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.web.dto.MonedaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/moneda")
public class MonedaRestController extends BaseRestController {

	/** Servicio de moneda. */
	@Autowired
	private IMonedaService monedaService;
	
	
	/**
	 * Servicio REST que retorna el tipo de moneda
	 * @param userName usuario
	 * 
	 * @return tipoModena
	 */
	@RequestMapping(value = "/get_tipo_moneda/{userName}", method = RequestMethod.GET, 
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TipoMoneda getTipoMoneda(@PathVariable("userName") String userName) {
		return monedaService.getTipoMoneda(userName);
	}
	
	/**
	 * Servicio REST que cambia el tipo de moneda
	 * @param userName usuario
	 * 
	 * @return success or fail
	 */
	@RequestMapping(value = "/set_tipo_moneda", 
			            method = RequestMethod.POST,
			            consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean setTipoMoneda(@RequestBody MonedaDTO monedaRequestDTO) {
		return monedaService.setTipoMoneda(monedaRequestDTO.getUserName(),monedaRequestDTO.getTipoMoneda());
	}
	
}
