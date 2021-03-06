package action.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.myinterface.AdminInterface;
import dao.myinterface.UserInterface;
import net.sf.json.JSONObject;
import utils.MyBatisUtil;

/**
 * Servlet implementation class UpdateU2
 */
@WebServlet("/UpdateU2")
public class UpdateU2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateU2() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
   	    UserInterface ui = MyBatisUtil.getSession().getMapper(UserInterface.class);
   	    AdminInterface ai = MyBatisUtil.getSession().getMapper(AdminInterface.class);
    	String uName=request.getParameter("uName");
    	String newName=request.getParameter("newName");
    	String newpwd=request.getParameter("newpwd");
    	String newStatus=request.getParameter("newstatus");

    	System.out.println(uName);
    	System.out.println(newName);
    	System.out.println(newpwd);
    	System.out.println(newStatus);
    	JSONObject jo=new JSONObject();
    	if(!uName.equals(newName)){
    		if(0==ui.canUpdateU(newName)){
    			ai.updateU(uName, newName, newpwd, Integer.parseInt(newStatus));
    			jo.put("flag", 1);
    		}else{
    			jo.put("flag", 0);
    		}
    	}else{
    		//OperateOnDB.updateU(uName, newpwd);
    		ai.updateU(uName, newName, newpwd, Integer.parseInt(newStatus));
    		jo.put("flag", 1);
    	}
    	
    	
    	response.setContentType("text/json;charset=utf-8");
    	response.getWriter().print(jo.toString());
    }
    
}
