package action;

/*
 * 管理员登录控制
 * 登录要求：（用防止SQL注入的正则表达式检验管理的输入）
 * 		一）、有没有该管理员
 * 		二）、该管理员是否可用
 * 		三）、登录名与密码是否匹配
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dao.DataBase;
import dao.OperaPojo;
import service.Check;
import utils.MyOptionPanel;
import view.Login;

public class LoginAction {
	public static Login log;
	public static void main(String[] args) {
		DataBase.setConnetion();
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
		
		log.ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				String adminName=log.jtf.getText();
				char[] temp=log.jpf.getPassword();
				String pd=new String(temp);
				if(Check.isUnValid(adminName, pd))
					MyOptionPanel.initWrongPanel("输入不合法！");
				else if(!OperaPojo.exit(adminName))
					MyOptionPanel.initWrongPanel("该管理员不存在！");
				else if(OperaPojo.adminEnable(adminName))
					MyOptionPanel.initWrongPanel("该管理员不可用！无法登录！");
				else if(!OperaPojo.match(adminName, pd))
					MyOptionPanel.initWrongPanel("密码错误！");
				else{
					log.close();
					new HotelAction(adminName);
				}
			}
			
		});
		
	}
}
