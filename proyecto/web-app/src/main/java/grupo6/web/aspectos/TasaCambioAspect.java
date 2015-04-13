package grupo6.web.aspectos;

import java.util.List;

import grupo6.modulo.payment.dao.enums.TipoMoneda;
import grupo6.persistencia.dao.BaseDAO;
import grupo6.web.dto.ProductoResponseDTO;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Repository(value = "TasaCambioAspectDAO")
public class TasaCambioAspect extends BaseDAO{

	private static final double USD_TO_COP = 2518.89169;
	private static final double USD_TO_EUR = 0.944643869;
	
	private static final double EUR_TO_COP = 2666.49874;
	private static final double EUR_TO_USD = 1.0586;
	
	private static final double COP_TO_EUR = 0.000375023616;
	private static final double COP_TO_USD = 0.000397;
	
	@Pointcut("execution(* grupo6.web.controller.rest.ProductoRestController.listarProductos(Long))")  
    public void convertirMoneda(){}  
      
	@Transactional
    @Around("convertirMoneda()")  
    public List<ProductoResponseDTO> adviceTasaCambio(ProceedingJoinPoint proceedingJoinPoint) throws Throwable   
    {  
    	TipoMoneda tipoMonedaUsuario = (TipoMoneda) proceedingJoinPoint.getArgs()[0];
    	
    	@SuppressWarnings("unchecked")
		List<ProductoResponseDTO> productos = (List<ProductoResponseDTO>) proceedingJoinPoint.proceed();  
        
    	for(int i = 0;i<productos.size();i++){
    		TipoMoneda tipoMonedaProducto = productos.get(i).getTipoMoneda();
    		
    		double valorFinal = getConversion(tipoMonedaProducto,tipoMonedaUsuario,productos.get(i).getPrecio());
    		productos.get(i).setPrecio(valorFinal);
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
