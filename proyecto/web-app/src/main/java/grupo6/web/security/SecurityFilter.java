package grupo6.web.security;

import grupo6.web.dto.UsuarioDTO;

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

	private static final String LOGIN_PAGE_URI = "/pages/nonsecure/login.jsp";

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
		nonRestrictedResources.add("/pages/nonsecure/login.jsp");

		restrictedResourcesCient = new HashSet<String>();
		restrictedResourcesCient.add("/pages/client/destinationDetails.jsp");
		restrictedResourcesCient.add("/pages/client/home_client.jsp");
		restrictedResourcesCient.add("/pages/client/indexUser.jsp");
		restrictedResourcesCient.add("/pages/client/payment.jsp");
		restrictedResourcesCient.add("/pages/client/searchResult.jsp");

		restrictedResourcesProvider = new HashSet<String>();
		restrictedResourcesProvider.add("/pages/provider/home_provider.jsp");
		restrictedResourcesProvider.add("/pages/provider/indexProvider.jsp");

		restrictedResourcesAdmin = new HashSet<String>();
		restrictedResourcesAdmin.add("/pages/admin/home_admin.jsp");
		restrictedResourcesAdmin.add("/pages/admin/indexAdmin.jsp");
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

		UsuarioDTO usuario = (UsuarioDTO) req.getSession().getAttribute("usuarioSesion");
		if (usuario == null) {
			return false;
		}
		Iterator<String> ite = null;
		if ("ADMIN".equals(usuario.getRol())) {
			ite = restrictedResourcesAdmin.iterator();
		} else if ("CLIENT".equals(usuario.getRol())) {
			ite = restrictedResourcesCient.iterator();
		}
		else if ("PROVIDER".equals(usuario.getRol())) {
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
