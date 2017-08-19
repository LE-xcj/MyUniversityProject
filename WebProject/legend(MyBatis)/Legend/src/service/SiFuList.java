package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.myinterface.UserInterface;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.TableImformation;
import utils.MyBatisUtil;

/**
 * Servlet implementation class SiFuList
 */
@WebServlet("/SiFuList")
public class SiFuList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiFuList() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	UserInterface ui = MyBatisUtil.getSession().getMapper(UserInterface.class);
    	List<TableImformation> list=ui.getTableList();
    	JSONArray ja = new JSONArray();
    	if( null != list ) for (TableImformation item : list) {
    		JSONObject jo=new JSONObject();    	
			jo.put("sName", item.getsName());
			jo.put("sIP", item.getsIP());
			jo.put("onTime", item.getOnTime());
			jo.put("roadType", item.getRoadType());
			jo.put("detail", item.getDetail());
			jo.put("qq", item.getQq());
			jo.put("gameAddress", item.getGameAddress());
			ja.add(jo);
		}
    	response.setContentType("text/json;charset=utf-8");
    	response.getWriter().print(ja.toString());
    }

}
