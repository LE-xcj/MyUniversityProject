package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.myinterface.AdminInterface;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.User;
import utils.MyBatisUtil;

/**
 * Servlet implementation class UserList
 */
@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	AdminInterface ai = MyBatisUtil.getSession().getMapper(AdminInterface.class);
    	
    	List<User> list=ai.getUserList();
    	JSONArray ja=new JSONArray();
    	if(null!=list){
    		for(User user:list){
    			JSONObject jo=new JSONObject();
    			jo.put("uName", user.getuName());
    			jo.put("pwd", user.getUpwd());
    			jo.put("status", user.getStatus());
    			ja.add(jo);
    		}
    	}
    	response.setContentType("text/json;charset=utf-8");
    	response.getWriter().print(ja.toString());
    }

}
