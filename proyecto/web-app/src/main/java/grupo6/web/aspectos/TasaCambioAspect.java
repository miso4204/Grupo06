package grupo6.web.aspectos;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.web.dto.ProductoResponseDTO;
import grupo6.web.dto.ResponseDTO;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;

@Aspect
public class TasaCambioAspect{

	private static final double USD_TO_COP = 2518.89169;
	private static final double USD_TO_EUR = 0.944643869;
	
	private static final double EUR_TO_COP = 2666.49874;
	private static final double EUR_TO_USD = 1.0586;
	
	private static final double COP_TO_EUR = 0.000375023616;
	private static final double COP_TO_USD = 0.000397;
	
      
	@AfterReturning(
		pointcut = "execution(* grupo6.web.controller.rest.ProductoRestController.listarProductos(..)) && args (tipoMonedaUsuario)", 
		returning= "productos")  
    public List<ProductoResponseDTO> adviceTasaCambioTodos(JoinPoint joinPoint, TipoMoneda tipoMonedaUsuario, 
    							List<ProductoResponseDTO> productos) throws Throwable   
    {  
		return aplicarConversion(productos, tipoMonedaUsuario);  
    }
	
	@AfterReturning(
		pointcut = "execution(* grupo6.web.controller.rest.ProductoRestController.listarPorPrecio(..)) && args (precioInicial,precioFinal,tipoMonedaUsuario)",
		returning= "productos")  
    public List<ProductoResponseDTO> adviceTasaCambioPrecio(JoinPoint joinPoint, Double precioInicial, Double  precioFinal, TipoMoneda tipoMonedaUsuario, 
    							List<ProductoResponseDTO> productos) throws Throwable   
    {  
		return aplicarConversion(productos, tipoMonedaUsuario);   
    }
	
	@AfterReturning(
		pointcut = "execution(* grupo6.web.controller.rest.ProductoRestController.listarPorFecha(..)) && args (fechaInicial, fechaFinal, tipoMonedaUsuario)",
		returning= "productos")  
    public List<ProductoResponseDTO> adviceTasaCambioFecha(JoinPoint joinPoint, Date fechaInicial,
    							Date fechaFinal, TipoMoneda tipoMonedaUsuario, 
    							List<ProductoResponseDTO> productos) throws Throwable   
    {  
		return aplicarConversion(productos, tipoMonedaUsuario);  
    }
	
	@SuppressWarnings("unchecked")
	@AfterReturning(
		pointcut = "execution(* grupo6.web.controller.rest.ProductoRestController.listarPorLugar(..)) && args (lugar, tipoMonedaUsuario)", 
		returning= "productos")  
    public ResponseEntity<ResponseDTO> adviceTasaCambioLugar(JoinPoint joinPoint, String lugar, TipoMoneda tipoMonedaUsuario, 
    		ResponseEntity<ResponseDTO> productos) throws Throwable   
    {  
		productos.getBody().setRespuesta(aplicarConversion((List<ProductoResponseDTO>)productos.getBody().getRespuesta(), tipoMonedaUsuario));
        return productos;  
    }
	
	@AfterReturning(
		pointcut = "execution(* grupo6.web.controller.rest.ProductoRestController.obtenerProductoPorId(..)) && args (id, clientId, tipoMonedaUsuario)",
		returning= "producto")  
    public ProductoResponseDTO adviceTasaCambioId(JoinPoint joinPoint,  
    							Long id, Long clientId,  TipoMoneda tipoMonedaUsuario, 
    							ProductoResponseDTO producto) throws Throwable   
    {  
		if (producto != null) {
			TipoMoneda tipoMonedaProducto = producto.getTipoMoneda();
		
			double valorFinal = getConversion(tipoMonedaProducto,tipoMonedaUsuario,producto.getPrecio());
			producto.setPrecio(valorFinal);
		}
    	    	
        return producto;  
    }
	
    
	private List<ProductoResponseDTO> aplicarConversion(List<ProductoResponseDTO> productos, 
			TipoMoneda tipoMonedaUsuario) {
		
		if (tipoMonedaUsuario != null) {       
	    	for(int i = 0;i<productos.size();i++){
	    		TipoMoneda tipoMonedaProducto = productos.get(i).getTipoMoneda();
	    		
	    		double valorFinal = getConversion(tipoMonedaProducto,tipoMonedaUsuario,productos.get(i).getPrecio());
	    		productos.get(i).setPrecio(valorFinal);
	    	}
    	}
    	
        return productos;  
	}
    
    private double getConversion(TipoMoneda tipoMonedaProducto,TipoMoneda tipoMonedaUsuario,double valor) {
    	double valorFinal = valor;
    	if(tipoMonedaUsuario != tipoMonedaProducto){
			if(tipoMonedaProducto == TipoMoneda.DOLAR && tipoMonedaUsuario == TipoMoneda.EURO ){
				valorFinal = valorFinal * USD_TO_EUR;
			}
			if(tipoMonedaProducto == TipoMoneda.DOLAR && tipoMonedaUsuario == TipoMoneda.COLOMBIAN_PESOS ){
				valorFinal = valorFinal * USD_TO_COP;
			}
			if(tipoMonedaProducto == TipoMoneda.EURO && tipoMonedaUsuario == TipoMoneda.DOLAR ){
				valorFinal = valorFinal * EUR_TO_USD;
			}
			if(tipoMonedaProducto == TipoMoneda.EURO && tipoMonedaUsuario == TipoMoneda.COLOMBIAN_PESOS ){
				valorFinal = valorFinal * EUR_TO_COP;
			}
			if(tipoMonedaProducto == TipoMoneda.COLOMBIAN_PESOS && tipoMonedaUsuario == TipoMoneda.DOLAR ){
				valorFinal = valorFinal * COP_TO_USD;
			}
			if(tipoMonedaProducto == TipoMoneda.COLOMBIAN_PESOS && tipoMonedaUsuario == TipoMoneda.EURO ){
				valorFinal = valorFinal * COP_TO_EUR;
			}
		}
    	return valorFinal;
	}
}
