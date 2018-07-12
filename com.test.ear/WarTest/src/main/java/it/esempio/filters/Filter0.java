package it.esempio.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "Accipicchia", urlPatterns = {"/soppresso"})
public class Filter0 implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		//HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		if(request.getParameter("nome") != null && request.getParameter("cognome") != null ) {
		    String nome = request.getParameter("nome");
		    String cognome = request.getParameter("cognome");
		    
		    System.out.println(nome);
		    System.out.println(cognome);
		    
		    
		    
		}
		
	    System.out.println("Id sessione da Accipicchia: " + session.getId());
	    filterChain.doFilter(request, response);

		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
