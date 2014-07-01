package config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;


public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		boolean isUserLogged = session.getAttribute("player") != null;
		HttpServletResponse res = (HttpServletResponse) response;
		
		try{
			String requestURI = StringUtils.removeEnd(req.getRequestURI(), "/");
			
			boolean publicPages = requestURI.contains("index.xhtml") || requestURI.endsWith("/create.xhtml") ;
			
			if(isUserLogged && (requestURI.contains("index.xhtml") || requestURI.equals(req.getContextPath()))){
				res.sendRedirect(req.getContextPath()+"/faces/game.xhtml");
			}else if(isUserLogged){ 
				chain.doFilter(request, response);
			} else if (!isUserLogged && publicPages) {
				chain.doFilter(request, response);
			}else{
				System.err.println("**** Redirecionando... *****");
				res.sendRedirect(req.getContextPath()+"/faces/index.xhtml");
			}
		}catch (Exception e) {			
			e.printStackTrace();
			res.sendRedirect(req.getContextPath()+"/faces/index.xhtml");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
