package br.com.correntista.seguranca;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.correntista.controle.LoginControle;


@WebFilter(urlPatterns = "/private/*")
public class FiltroSeguranca implements Filter{
	
	@Override
	public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//HttpSession sessao = httpRequest.getSession();
		String contextPath = httpRequest.getContextPath();
		//LoginControle loginControle = (LoginControle) sessao.getAttribute("loginC");
		
		String url = httpRequest.getRequestURL().toString();
		
		if (url.contains("/private") && LoginControle.usuarioSessao() == null) {
			httpResponse.sendRedirect(contextPath + "/login.xhtml");
		} else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}