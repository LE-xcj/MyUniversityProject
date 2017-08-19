package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OperateOnDB;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class UpdateU extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateU() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
        String uName=(String)request.getSession().getAttribute("uName");
    	String pwd=(String)request.getSession().getAttribute("pwd"); 
   	    	
    	String newName=request.getParameter("newName");
    	String newpwd=request.getParameter("newpwd");

    	System.out.println(uName);
    	System.out.println(pwd);
    	System.out.println(newName);
    	System.out.println(newpwd);
    	JSONObject jo=new JSONObject();
    	if(!uName.equals(newName)){
    		if(OperateOnDB.canUpdateU(newName)){
    			OperateOnDB.updateU(uName, newName, newpwd); 
    			request.getSession().setAttribute("uName", newName);
    			request.getSession().setAttribute("pwd", newpwd);
    			jo.put("flag", 1);
    		}else{
    			jo.put("flag", 0);
    		}
    	}else{
    		OperateOnDB.updateU(uName, newpwd);
			request.getSession().setAttribute("uName", newName);
			request.getSession().setAttribute("pwd", newpwd);
    		jo.put("flag", 1);
    	}
    	
    	
    	response.setContentType("text/json;charset=utf-8");
    	response.getWriter().print(jo.toString());
    }

}
