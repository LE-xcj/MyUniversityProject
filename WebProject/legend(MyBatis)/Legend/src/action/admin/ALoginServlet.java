package action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.myinterface.AdminInterface;
import net.sf.json.JSONObject;
import pojo.Admin;
import utils.MyBatisUtil;

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
    	AdminInterface ai = MyBatisUtil.getSession().getMapper(AdminInterface.class);

    	String aName=request.getParameter("aName");
    	String apwd=request.getParameter("apwd");
    	JSONObject jo=new JSONObject();
    	if(null==aName||"".equals(aName)){	//����ĵ�¼���Ƿ�Ϊ��
    		jo.put("flag", 2);
    	}else if(null==apwd||"".equals(apwd)){	//���������Ϊ��
    		jo.put("flag", 3);
    	}else{
    		Admin admin=ai.getAdmin(aName);
    		if(null!=admin){	    		
    			if(admin.getApwd().equals(apwd)){	//����Ա����������ƥ��
    				request.getSession().setAttribute("admin", admin);
    				jo.put("flag", 1);
    			}
    			else	//�������
    				jo.put("flag", 0);
    		}else{		//����Ա������
    			jo.put("flag", -1);
    		}   		
    	}
    	response.setContentType("text/json;charset=utf-8;");	//���ñ����ʽ
    	response.getWriter().print(jo.toString());	//���ص�ǰ��
    }

}
