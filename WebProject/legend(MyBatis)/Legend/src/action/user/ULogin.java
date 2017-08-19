package action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.myinterface.UserInterface;
import net.sf.json.JSONObject;
import pojo.User;
import utils.MyBatisUtil;

/**
 * Servlet implementation class ULogin
 */
@WebServlet("/ULogin")
public class ULogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ULogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	UserInterface ui = MyBatisUtil.getSession().getMapper(UserInterface.class);
    	request.setCharacterEncoding("utf-8");
    	String uName=request.getParameter("uName");
    	String pwd=request.getParameter("pwd");
    	String code=request.getParameter("code");
    	
    	User user=ui.getUser(uName);
    	HttpSession session=request.getSession();
    	String sCode=(String)session.getAttribute("checkCode");
    	System.out.println(sCode);
    	JSONObject jo=new JSONObject();
    	if(null==user){
    		jo.put("flag", -1);
    	}else if(0==user.getStatus()){
    		jo.put("flag", -2);
    	}else if(!user.getUpwd().equals(pwd)){
    		jo.put("flag", 0);
    	}else if(!sCode.equals(code)){
    		jo.put("flag", 2);
    	}else{
    		request.getSession().setAttribute("uName", uName);
    		request.getSession().setAttribute("pwd", pwd);
    		jo.put("flag", 1);
    	}
    	response.setContentType("text/json;charset:utf-8");
    	response.getWriter().print(jo.toString());
    }

}
