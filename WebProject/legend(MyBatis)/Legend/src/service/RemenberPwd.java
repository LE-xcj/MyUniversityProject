package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemenberPwd
 */
@WebServlet("/RemenberPwd")
public class RemenberPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemenberPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String aName =  request.getParameter("aName");
    	String apwd = request.getParameter("apwd");
    	String status = request.getParameter("status");
    	String auto = request.getParameter("auto");
    	System.out.println(status+" "+ auto);
    	if("1".equals(status)){
    		System.out.println("addcookie");
    		addCookie("status", status, response);
    		addCookie("aName", aName, response);
    		addCookie("apwd", apwd, response);
    		addCookie("auto", auto, response);
    		
    	}else {
    		Cookie[] cookies = request.getCookies();
    		if(null!=cookies){
    			for(Cookie cookie : cookies){
    				
    				if("status".equals(cookie.getName())){
    					String zero ="0";
    					addCookie("status", zero, response);
    					break;
    					
    				}   				
    			}
    		}
    	}
    	
    }
    
    private void addCookie(String name, String value,HttpServletResponse response){
		Cookie c = new Cookie(name,value);
		c.setMaxAge(1*60*60);
		c.setPath("/Legend");
		response.addCookie(c);
    }

}
