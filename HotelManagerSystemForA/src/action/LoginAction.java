package action;

/*
 * ����Ա��¼����
 * ��¼Ҫ�󣺣��÷�ֹSQLע���������ʽ�����������룩
 * 		һ������û�иù���Ա
 * 		�������ù���Ա�Ƿ����
 * 		��������¼���������Ƿ�ƥ��
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
	
	//����ť��Ӿ��嶯���ļ�����
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
					MyOptionPanel.initWrongPanel("���벻�Ϸ���");
				else if(!OperaPojo.exit(adminName))
					MyOptionPanel.initWrongPanel("�ù���Ա�����ڣ�");
				else if(OperaPojo.adminEnable(adminName))
					MyOptionPanel.initWrongPanel("�ù���Ա�����ã��޷���¼��");
				else if(!OperaPojo.match(adminName, pd))
					MyOptionPanel.initWrongPanel("�������");
				else{
					log.close();
					new HotelAction(adminName);
				}
			}
			
		});
		
	}
}
