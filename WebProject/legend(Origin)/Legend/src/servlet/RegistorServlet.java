package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OperateOnDB;
import net.sf.json.JSONObject;
import pojo.User;


/**
 * Servlet implementation class RegistorServlet
 */
@WebServlet("/RegistorServlet")
public class RegistorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String uName=request.getParameter("uName");
    	String upwd=request.getParameter("upwd");
    	User user=OperateOnDB.getUser(uName);
    	JSONObject jo=new JSONObject();
    	if(null!=user){
    		jo.put("flag", 1);
    	}else{
    		if(null==uName||"".equals(uName)){
    			jo.put("flag", 2);
    		}else{
    			if(null!=upwd&&upwd.length()>=6){
    				OperateOnDB.addUser(uName, upwd);
    				jo.put("flag", 0);
    			}
    		}
    		
    	}
    	
    	response.setContentType("text/json;charset=utf-8");
    	response.getWriter().print(jo.toString());
    }

}
