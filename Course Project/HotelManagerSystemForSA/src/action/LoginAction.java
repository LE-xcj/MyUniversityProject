
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
		DataBase.setConnetion();		//�������ݿ�
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
				if(Check.isUnValid(name, pd))		//��������ĵ�¼���������Ƿ�Ϸ�����ֹSQLע�룩
					new MyOptionPanel("���벻�Ϸ���", 0);
				else if(OperaPojo.superAdminLogin(name, pd)){
					log.dispose();
					new HotelAction();		//��¼�ɹ��������������
				}else
					new MyOptionPanel("��¼�������벻ƥ�䣡",0);
			}
			
		});
		
		

	}

}
