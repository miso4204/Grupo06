package grupo6.web.dto.shoppingcart;

import java.io.Serializable;

/**
 * Clase que representa el JSON que llega al agregar un producto al carrito
 * 
 * @author Alejo
 *
 */
public class CarritoProductoRequestDTO implements Serializable{

	/**
	 * UID generado. 
	 */
	private static final long serialVersionUID = -7487044997893673039L;
	
	private String userName;
	private Long idProducto;

	public CarritoProductoRequestDTO() {
	}

	public CarritoProductoRequestDTO(String userName, Long idProducto) {
		this.userName = userName;
		this.idProducto = idProducto;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

}
