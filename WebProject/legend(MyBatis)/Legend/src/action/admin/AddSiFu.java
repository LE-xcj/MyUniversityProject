package action.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.myinterface.AdminInterface;
import net.sf.json.JSONObject;
import pojo.TableImformation;
import utils.MyBatisUtil;

/**
 * Servlet implementation class AddSiFu
 */
@WebServlet("/AddSiFu")
public class AddSiFu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSiFu() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sIP = request.getParameter("sIP");
		String sName = request.getParameter("sName");
		String roadType =  request.getParameter("roadType");
		String detail = request.getParameter("detail");
		String qq = request.getParameter("qq");
		String gameAddress = request.getParameter("address");
		System.out.println(sIP);
		System.out.println(sName);
		System.out.println(roadType);
		System.out.println(detail);
		System.out.println(qq);
		System.out.println(gameAddress);
		
		AdminInterface ai = MyBatisUtil.getSession().getMapper(AdminInterface.class);
		JSONObject jo = new JSONObject();
		if(1==ai.exit(sIP)){
			jo.put("flag", 1);
		}else{
			TableImformation tb = new TableImformation(sIP, sName, roadType, detail, qq, gameAddress);
			ai.addSiFu(tb);
			jo.put("flag", 0);
		}
		
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(jo.toString());
	}

}
