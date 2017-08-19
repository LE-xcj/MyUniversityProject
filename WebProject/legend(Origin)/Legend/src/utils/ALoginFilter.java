package utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.Admin;

/**
 * Servlet Filter implementation class ALoginFilter
 */
@WebFilter({"/webpage/backStage.jsp"})
public class ALoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ALoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		Admin admin=(Admin)httpRequest.getSession().getAttribute("admin");
		System.out.println((admin==null));
		if(null==admin){
			httpResponse.sendRedirect(request.getServletContext().getContextPath()+"/webpage/aLogin.jsp");
			System.out.println(request.getServletContext().getContextPath()+"/webpage/aLogin.jsp");
		}else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
