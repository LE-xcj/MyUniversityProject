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
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	AdminInterface ai = MyBatisUtil.getSession().getMapper(AdminInterface.class);
    	String key=request.getParameter("key");
    	String flag=request.getParameter("flag");
    	System.out.println(key);
    	System.out.println(flag);
    	JSONObject jo=new JSONObject();
    	if("user".equals(flag)){
    		ai.deleteUser(key); 		
    	}else if("sifu".equals(flag)){
    		ai.deleteSave(key);
    		ai.deleteSiFu(key);
    	}
    	
    	jo.put("flag", 1);
    	response.setContentType("text/json;charset=utf-8");
    	response.getWriter().print(jo.toString());
    }

}
