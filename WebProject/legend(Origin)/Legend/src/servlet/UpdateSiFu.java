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
 * Servlet implementation class UpdateSiFu
 */
@WebServlet("/UpdateSiFu")
public class UpdateSiFu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSiFu() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String sIP=request.getParameter("sIP");
    	String oldName=request.getParameter("oldName");
    	String newName=request.getParameter("newname");
    	String newRoad=request.getParameter("newroad");
    	String newDetail=request.getParameter("newDetail");
    	System.out.println(sIP);
    	System.out.println(newName);
    	System.out.println(newRoad);
    	System.out.println(newDetail);
    	JSONObject jo=new JSONObject();
    	System.out.println("----------------"); 	
    	if(OperateOnDB.hasExit(oldName,newName)){
    		jo.put("flag", 0);
    	}else{
    		OperateOnDB.updateSiFu(sIP, newName, newRoad, newDetail);
    		jo.put("flag", 1);
    	}
    	
    	
    	response.setContentType("text/json;charset=utf-8");
    	response.getWriter().print(jo.toString());
    }

}
