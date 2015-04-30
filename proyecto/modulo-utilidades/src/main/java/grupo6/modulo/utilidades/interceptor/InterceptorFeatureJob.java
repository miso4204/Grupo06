package grupo6.modulo.utilidades.interceptor;

import grupo6.modulo.utilidades.Variability;
import grupo6.modulo.utilidades.anotaciones.Feature;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;
/**
 * Logica del interceptor de metodos anotados con @Feature. 
 * @author caespinosam
 *
 */
@Component
public class InterceptorFeatureJob implements  MethodInterceptor  {

	/**
	 * (non-Javadoc)
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation metodo) throws Throwable {
		System.out.println("-------Interceptando metodo-----");
		System.out.println(metodo.getMethod().toGenericString());		
		Feature anotacion = metodo.getMethod().getAnnotation(Feature.class);
		if (anotacion != null) {
			String nodoFeature = anotacion.nombreNodo();
			boolean featureActivo = Variability.isEnable(nodoFeature);
			if (featureActivo) {
				return metodo.proceed();
			}
			throw new UnsupportedOperationException("La característica no está activa");
		}
		
		return metodo.proceed();
	}

}
