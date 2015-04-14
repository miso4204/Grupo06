package grupo6.modulo.shoppingcart.service.impl;

import java.util.List;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.modulo.shoppingcart.dao.impl.CarritoProductoResponseDTO;
import grupo6.modulo.shoppingcart.dao.view.ICarritoComprasDAO;
import grupo6.modulo.shoppingcart.service.view.ICarritoComprasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación de {@link ICarritoComprasService}.
 * 
 * @author Alejo
 *
 */
@Service(value = "carritoComprasService")
public class CarritoComprasService implements ICarritoComprasService {

	/** DAO del carrito de compras. */
	@Autowired
	private ICarritoComprasDAO carritoComprasDAO;

	/**
	 * @see grupo6.modulo.shoppingcart.service.view.ICarritoComprasService#agregar(String,
	 *      Long)
	 */
	@Override
	public Boolean agregar(String userName, Long idProducto) {
		return carritoComprasDAO.agregar(userName, idProducto);
	}

	/**
	 * @see grupo6.modulo.shoppingcart.service.view.ICarritoComprasService#remover(String,
	 *      Long)
	 */
	@Override
	public Boolean remover(String userName, Long idProducto) {
		return carritoComprasDAO.remover(userName, idProducto);
	}

	/**
	 * @see grupo6.modulo.shoppingcart.service.view.ICarritoComprasService#consultarCarritoCompras(String)
	 */
	@Override
	public List<CarritoProductoResponseDTO> consultarCarritoCompras(String userName,TipoMoneda tipoMoneda) {
		return carritoComprasDAO.consultarCarritoCompras(userName,tipoMoneda);
	}

	/**
	 * @see grupo6.modulo.shoppingcart.service.view.ICarritoComprasService#getTotalCarritoCompras(String)
	 */
	@Override
	public Double getTotalCarritoCompras(String userName,TipoMoneda tipoMoneda) {
		return carritoComprasDAO.getTotalCarritoCompras(userName,tipoMoneda);
	}

}
