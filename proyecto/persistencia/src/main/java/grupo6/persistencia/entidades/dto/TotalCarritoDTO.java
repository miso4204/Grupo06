package grupo6.persistencia.entidades.dto;

/**
 * Total del carrito para los tres medios de pago.
 * @author caespinosam
 *
 */
public class TotalCarritoDTO {
	
	private String medioPago;
	private Double valor;
	
	
	
	public TotalCarritoDTO(String medioPago, Double valor) {
		super();
		this.medioPago = medioPago;
		this.valor = valor;
	}
	public String getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	

}
