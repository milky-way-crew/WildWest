package com.web.app.worldgames.filter;

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

import org.apache.log4j.Logger;


public class UserFilter implements Filter {
	private final static Logger log = Logger.getLogger(UserFilter.class);
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		if(session.getAttribute("user")==null){
			String requestAdrr = ((HttpServletRequest)request).getServletPath();
			if(requestAdrr.equals("/login") || requestAdrr.endsWith(".css") || requestAdrr.endsWith(".img")
					|| requestAdrr.equals("/register") || requestAdrr.endsWith(".png")||
					requestAdrr.endsWith(".js") || requestAdrr.endsWith(".ttc") || requestAdrr.endsWith(".ttf")
					|| requestAdrr.endsWith(".fon")){
//				log.info("Filter");
				chain.doFilter(request, response);
			}else{ 
				((HttpServletResponse) response).sendRedirect("login");
//				log.info("Filter");
				}
		}else{
			chain.doFilter(request, response);
//			log.info("Filter");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
