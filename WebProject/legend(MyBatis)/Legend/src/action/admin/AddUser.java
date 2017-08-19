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
import pojo.User;
import utils.MyBatisUtil;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserInterface ui = MyBatisUtil.getSession().getMapper(UserInterface.class);
		AdminInterface ai = MyBatisUtil.getSession().getMapper(AdminInterface.class);
		String uName=request.getParameter("uName");
		String pwd=request.getParameter("pwd");
		String status=request.getParameter("status");
		System.out.println(status);
		JSONObject jo=new JSONObject();
		User user=ui.getUser(uName);
		if(null!=user){
			jo.put("flag", 0);
		}else{
			ai.addUser(uName, pwd, status);
			jo.put("flag", 1);
		}
		
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(jo.toString());
	}

}
