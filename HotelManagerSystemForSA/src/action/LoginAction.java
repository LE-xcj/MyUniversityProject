
package action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dao.DataBase;
import dao.OperaPojo;
import service.Check;
import utils.MyOptionPanel;
import view.Login;

public class LoginAction {
	public static Login log;
	public static void main(String[] args){
		DataBase.setConnetion();		//连接数据库
		log=new Login();
		new LoginAction().init();
		
	}
	
	//给按钮添加具体动作的监听器
	private void init(){
		log.close.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				log.setImg2();
			}
			
			public void mouseExited(MouseEvent e){
				
				log.setImg1();
			}
			
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
		});
		
		log.login.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				log.setLog2();
			}
			
			public void mouseExited(MouseEvent e){						
				log.setLog1();
			}
			
			public void mouseClicked(MouseEvent e){
				String name=log.jt.getText();
				char[] temp=log.jpd.getPassword();
				String pd=new String(temp);
				if(Check.isUnValid(name, pd))		//检验输入的登录名与密码是否合法（防止SQL注入）
					new MyOptionPanel("输入不合法！", 0);
				else if(OperaPojo.superAdminLogin(name, pd)){
					log.dispose();
					new HotelAction();		//登录成功，进入操作界面
				}else
					new MyOptionPanel("登录名与密码不匹配！",0);
			}
			
		});
		
		

	}

}
