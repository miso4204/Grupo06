package grupo6.web.dto;

import grupo6.modulo.payment.dao.enums.TiposPago;
import grupo6.persistencia.entidades.FacturaCompra;
import grupo6.web.json.serializer.JsonDateDeserializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class FacturaCompraDTO implements Serializable {

	private static final long serialVersionUID = 6841382906797112304L;
	private Date fechaPago;
	private double totalPagado;
	private TiposPago tipoPago;
	
	public FacturaCompraDTO(Date fechaPago, double totalPagado, TiposPago tipoPago) {
		super();
		this.fechaPago = fechaPago;
		this.totalPagado = totalPagado;
		this.tipoPago = tipoPago;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public Date getFechaPago() {
		return fechaPago;
	}

	public double getTotalPagado() {
		return totalPagado;
	}
	
	public TiposPago getTipoPago() {
		return tipoPago;
	}
	
	public static List<FacturaCompraDTO> facturasToJson(List<FacturaCompra> facturas){
		List<FacturaCompraDTO> resultado = new ArrayList<FacturaCompraDTO>();
		for(FacturaCompra f: facturas){
			resultado.add(new FacturaCompraDTO(f.getFechaPago(),f.getTotalPagado(),f.getTipoPago()));
		}
		return resultado;
	}
}
