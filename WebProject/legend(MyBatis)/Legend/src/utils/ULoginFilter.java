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

/**
 * Servlet Filter implementation class ULoginFilter
 */
@WebFilter("/webpage/user/userOperate.jsp")
public class ULoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ULoginFilter() {
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
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String uName = (String)httpRequest.getSession().getAttribute("uName");
		String pwd = (String)httpRequest.getSession().getAttribute("pwd");
		System.out.println(uName);
		System.out.println(pwd);
		
		if(null==uName||"".equals(uName)||null==pwd||"".equals(pwd)){
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath()+"/webpage/user/uLogin.jsp");
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
