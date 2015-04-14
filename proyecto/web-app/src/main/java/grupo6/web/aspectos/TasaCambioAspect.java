package grupo6.web.aspectos;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.utilidades.Currency;
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
		
			double valorFinal = Currency.getConversion(tipoMonedaProducto,tipoMonedaUsuario,producto.getPrecio());
			producto.setPrecio(valorFinal);
		}
    	    	
        return producto;  
    }
	
    
	private List<ProductoResponseDTO> aplicarConversion(List<ProductoResponseDTO> productos, 
			TipoMoneda tipoMonedaUsuario) {
		
		if (tipoMonedaUsuario != null) {       
	    	for(int i = 0;i<productos.size();i++){
	    		TipoMoneda tipoMonedaProducto = productos.get(i).getTipoMoneda();
	    		
	    		double valorFinal = Currency.getConversion(tipoMonedaProducto,tipoMonedaUsuario,productos.get(i).getPrecio());
	    		productos.get(i).setPrecio(valorFinal);
	    	}
    	}
    	
        return productos;  
	}
}
