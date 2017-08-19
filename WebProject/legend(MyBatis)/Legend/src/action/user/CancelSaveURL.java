package action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.myinterface.UserInterface;
import net.sf.json.JSONObject;
import utils.MyBatisUtil;

/**
 * Servlet implementation class CancelSaveURL
 */
@WebServlet("/CancelSaveURL")
public class CancelSaveURL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelSaveURL() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	UserInterface ui = MyBatisUtil.getSession().getMapper(UserInterface.class);
    	String uName=(String)request.getSession().getAttribute("uName");
    	String sIP=request.getParameter("sIP");
    	ui.cancelSave(uName, sIP);
    	JSONObject jo=new JSONObject();
    	jo.put("flag", 1);
    	
    	response.setContentType("text/json;charset=utf-8");
    	response.getWriter().print(jo.toString());
    }

}
