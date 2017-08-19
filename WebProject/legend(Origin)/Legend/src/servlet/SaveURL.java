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
 * Servlet implementation class SaveURL
 */
@WebServlet("/SaveURL")
public class SaveURL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveURL() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String sIP=request.getParameter("sIP");
    	String uName=(String) request.getSession().getAttribute("uName");
    	System.out.println(uName);
    	JSONObject jo=new JSONObject();
    	if(OperateOnDB.hasSave(sIP, uName)){
    		jo.put("flag", 0);
    	}else{
    		OperateOnDB.saveSiFu(sIP, uName);
    		jo.put("flag", 1);
    	}
    	
    	response.setContentType("text/json;charset:utf-8");
    	response.getWriter().print(jo.toString());
    }

}
