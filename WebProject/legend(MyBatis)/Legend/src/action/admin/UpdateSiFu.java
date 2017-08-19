package action.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.myinterface.AdminInterface;
import net.sf.json.JSONObject;
import utils.MyBatisUtil;

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
    	AdminInterface ai = MyBatisUtil.getSession().getMapper(AdminInterface.class);
    	
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
    	if(1==ai.hasExit(oldName,newName)){
    		jo.put("flag", 0);
    	}else{
    		ai.updateSiFu(sIP, newName, newRoad, newDetail);
    		jo.put("flag", 1);
    	}
    	
    	
    	response.setContentType("text/json;charset=utf-8");
    	response.getWriter().print(jo.toString());
    }

}
