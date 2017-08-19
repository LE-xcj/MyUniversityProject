package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OperateOnDB;
import net.sf.json.JSONObject;
import pojo.Admin;

/**
 * Servlet implementation class ALoginServlet
 */
@WebServlet("/ALoginServlet")
public class ALoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ALoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String aName=request.getParameter("aName");
    	String apwd=request.getParameter("apwd");
    	JSONObject jo=new JSONObject();
    	if(null==aName||"".equals(aName)){	//输入的登录名是否为空
    		jo.put("flag", 2);
    	}else if(null==apwd||"".equals(apwd)){	//输入的密码为空
    		jo.put("flag", 3);
    	}else{
    		Admin admin=OperateOnDB.getAdmin(aName);
    		if(null!=admin){	    		
    			if(admin.getApwd().equals(apwd)){	//管理员名称与密码匹配
    				request.getSession().setAttribute("admin", admin);
    				jo.put("flag", 1);
    			}
    			else	//密码错误
    				jo.put("flag", 0);
    		}else{		//管理员不存在
    			jo.put("flag", -1);
    		}   		
    	}
    	response.setContentType("text/json;charset=utf-8;");	//设置编码格式
    	response.getWriter().print(jo.toString());	//返回到前端
    }

}
