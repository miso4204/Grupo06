package grupo6.modulo.utilidades.interceptor;

import grupo6.modulo.utilidades.anotaciones.Feature;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Interceptor de metodos anotados con @Feature. 
 * @author caespinosam
 *
 */
@Component
public class InterceptorFeature extends AbstractPointcutAdvisor {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Bean que contiene la logica del interceptor.
	 */
	@Autowired
	private InterceptorFeatureJob interceptorFeatureJob;
	
	/**
	 * Especifica los metodos a interceptar.
	 */
	private final StaticMethodMatcherPointcut matcher = new StaticMethodMatcherPointcut() {
		
		/**
		 * (non-Javadoc)
		 * @see org.springframework.aop.MethodMatcher#matches(java.lang.reflect.Method, java.lang.Class)
		 */
		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			return method.isAnnotationPresent(Feature.class);
		}
	};
	
	
	
	
	

	/**
	 * (non-Javadoc)
	 * @see org.springframework.aop.PointcutAdvisor#getPointcut()
	 */
	@Override
	public Pointcut getPointcut() {
		return matcher;
	}

	/**
	 * (non-Javadoc)
	 * @see org.springframework.aop.Advisor#getAdvice()
	 */
	@Override
	public Advice getAdvice() {
		return interceptorFeatureJob;
	}

}
