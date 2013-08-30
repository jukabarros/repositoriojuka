package autenticacao;

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
		boolean isUserLogged = session.getAttribute("usuario") != null;
		HttpServletResponse res = (HttpServletResponse) response;
		
		try{
			String requestURI = StringUtils.removeEnd(req.getRequestURI(), "/");
			
			boolean publicPages = requestURI.contains("/index.jsf");
			
			if(isUserLogged && (requestURI.endsWith("/index.jsf") || requestURI.equals(req.getContextPath()))){
				System.err.println("** Rederecionando para a evolucao do paciente");
				res.sendRedirect(req.getContextPath()+"/evolucao/listaEvolucao.jsf");
			}else if(isUserLogged){ 
				chain.doFilter(request, response);
			} else if (!isUserLogged && publicPages) {
				chain.doFilter(request, response);
			}else{
				res.sendRedirect(req.getContextPath()+"/index.jsf");
			}
		}catch (Exception e) {			
			e.printStackTrace();
			res.sendRedirect(req.getContextPath()+"/index.jsf");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
