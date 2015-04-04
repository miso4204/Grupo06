package grupo6.web.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filtro de autorización.
 * 
 * @author caespinosam
 *
 */
public class SecurityFilter implements Filter {

	private static final String LOGIN_PAGE_URI = "pages/nonsecure/login.html";

	/** recursos no restringidos */
	private Set<String> nonRestrictedResources;
	/** recursos restringidos del modulo cliente. */
	private Set<String> restrictedResourcesCient;
	/** recursos restringidos del modulo proveedor. */
	private Set<String> restrictedResourcesProvider;
	/** recursos restringidos del modulo administrador. */
	private Set<String> restrictedResourcesAdmin;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		nonRestrictedResources = new HashSet<String>();
		nonRestrictedResources.add("/pages/nonsecure/login.html");

		restrictedResourcesCient = new HashSet<String>();
		restrictedResourcesCient.add("/pages/client/destinationDetails.html");
		restrictedResourcesCient.add("/pages/client/home_client.html");
		restrictedResourcesCient.add("/pages/client/indexUser.html");
		restrictedResourcesCient.add("/pages/client/payment.html");
		restrictedResourcesCient.add("/pages/client/searchResult.html");

		restrictedResourcesProvider = new HashSet<String>();
		restrictedResourcesProvider.add("/pages/provider/home_provider.html");
		restrictedResourcesProvider.add("/pages/provider/indexProvider.html");

		restrictedResourcesAdmin = new HashSet<String>();
		restrictedResourcesAdmin.add("/pages/admin/home_admin.html");
		restrictedResourcesAdmin.add("/pages/admin/indexAdmin.html");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		String contextPath = ((HttpServletRequest) req).getContextPath();
		String requestUri = ((HttpServletRequest) req).getRequestURI();

		if (esRecursoRestringido(requestUri, contextPath)
				&& !esUsuarioAutorizado((HttpServletRequest) req, requestUri,
						contextPath)) {
			((HttpServletRequest) req).getRequestDispatcher(LOGIN_PAGE_URI)
					.forward(req, res);
		} else {
			chain.doFilter(req, res);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * Verifica si un recurso es restringido.
	 */
	private boolean esRecursoRestringido(String uri, String contextPath) {
		Iterator<String> ite = nonRestrictedResources.iterator();

		while (ite.hasNext()) {
			String nonRrestrictedResource = ite.next();
			if ((contextPath + nonRrestrictedResource).equalsIgnoreCase(uri)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Verifica si el usurio está autrizado para aceder al recurso.
	 * 
	 * @param req
	 *            el request http.
	 * @return true si el usuario está autorizado.
	 */
	private boolean esUsuarioAutorizado(HttpServletRequest req, String uri,
			String contextPath) {

		String rol = (String) req.getSession().getAttribute("rol");
		Iterator<String> ite = null;
		if ("ADMIN".equals(rol)) {
			ite = restrictedResourcesAdmin.iterator();
		} else if ("CLIENT".equals(rol)) {
			ite = restrictedResourcesCient.iterator();
		}
		if ("PROVIDER".equals(rol)) {
			ite = restrictedResourcesProvider.iterator();
		} else {
			return false;
		}

		while (ite.hasNext()) {
			String nonRrestrictedResource = ite.next();
			if ((contextPath + nonRrestrictedResource).equalsIgnoreCase(uri)) {
				return true;
			}
		}

		return false;
	}

}
