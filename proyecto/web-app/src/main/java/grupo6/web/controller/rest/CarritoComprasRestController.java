package grupo6.web.controller.rest;

import java.util.List;

import grupo6.modulo.shoppingcart.dao.impl.CarritoProductoResponseDTO;
import grupo6.modulo.shoppingcart.service.view.ICarritoComprasService;
import grupo6.web.dto.shoppingcart.CarritoProductoRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador REST de los servicios del carrito de compras.
 * 
 * @author Alejo
 *
 */
@Controller
@RequestMapping("/carrito")
public class CarritoComprasRestController {

	/** Servicios del carrito de compras. */
	@Autowired
	private ICarritoComprasService carritoComprasService;

	/**
	 * Servicio REST que agrega un producto al carrito de compras
	 * 
	 * @param carritoProductoRequestDTO es el
	 *            JSON de entrada con la información del producto a agregar
	 * @return true si se agrega, false en caso contrario
	 */
	@RequestMapping(value = "/agregar", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean agregar(
			@RequestBody CarritoProductoRequestDTO carritoProductoRequestDTO) {
		String userName = carritoProductoRequestDTO.getUserName();
		Long idProducto = carritoProductoRequestDTO.getIdProducto();
		return carritoComprasService.agregar(userName, idProducto);
	}

	/**
	 * Servicio REST que remueve un producto del carrito de compras
	 * 
	 * @param carritoProductoRequestDTO
	 *            JSON de entrada con la información del producto a remover
	 * @return true si se remueve, false en caso contrario
	 */
	@RequestMapping(value = "/remover", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean remover(
			@RequestBody CarritoProductoRequestDTO carritoProductoRequestDTO) {
		String userName = carritoProductoRequestDTO.getUserName();
		Long idProducto = carritoProductoRequestDTO.getIdProducto();
		return carritoComprasService.remover(userName, idProducto);
	}

	/**
	 * Servicio REST que retorna el carrito de compras de un usuario
	 * 
	 * @param userName es el nombre del usuario
	 * @return carrito de compras de un usuario
	 */
	@RequestMapping(value = "/listar/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CarritoProductoResponseDTO> consultarCarritoCompras(
			@PathVariable("userName") String userName) {
		return carritoComprasService.consultarCarritoCompras(userName);
	}

	/**
	 * Servicio REST que retorna el total de un carrito de compras de un usuario
	 * @param userName es el nombre del usuario
	 * @return total del carrito de compras de un usuario
	 */
	@RequestMapping(value = "/total/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Double totalCarritoCompras(
			@PathVariable("userName") String userName) {
		return carritoComprasService.getTotalCarritoCompras(userName);
	}
	
}
