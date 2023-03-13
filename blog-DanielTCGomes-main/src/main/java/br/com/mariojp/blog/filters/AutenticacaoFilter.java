package br.com.mariojp.blog.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AutenticacaoFIlter
 */
@WebFilter("/autenticado/*")
public class AutenticacaoFilter extends HttpFilter {
       
    private static final long serialVersionUID = 1L;


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request; 
		HttpServletResponse httpResponse = (HttpServletResponse) response; 

		
		Object usuario = httpRequest.getSession().getAttribute("usuario");
		if(usuario == null ) {
			httpResponse.sendRedirect("/blog/login.jsp");
		}else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		
	}


}