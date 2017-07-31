package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import dao.OperaPojo;
import service.Check;
import utils.ContainerForPojo;
import utils.MyFocusListener;
import utils.MyOptionPanel;
import view.AddOrUpdateAdmin;
import view.AddOrUpdateClient;
import view.AddOrUpdateRoom;
import view.AddRoomType;


	/*
	 * ����Ա���ͻ��������������µĿ�������
	 * 1��������һ�����ص�©�������ǲ��ܸ�����������޸��ˣ���ô����Ҳ��Ҫ�޸ģ�����Ϊ����ʵ��Ҳ��Ӧ���޸������Ŷ�
	 * �������������Ϊ����Ҫ������
	 */

public class OperaAction implements ActionListener{
	//�������½���
	public static AddOrUpdateAdmin aOrUadmin=new AddOrUpdateAdmin();		//����Ա	
	public static AddOrUpdateClient  aOrUclient=new AddOrUpdateClient();		//�ͻ�
	public static AddOrUpdateRoom aOrUroom=new AddOrUpdateRoom();				//����
	
	//��־
	private final static int adminBF=0;
	private final static int clientBF=1;
	private final static int roomBF=2;
	private static int sFlag=adminBF;		//��ǰ��־
	
	private static String info1,info2,info3,info4,info5,info6;		//��������ʾ��
	private static String ENABLE,self;		//���á��Զ���
	private String pk=null;					//�޸ĵ�ʱ����Ҫ�����������¼�޸�ǰ����������ֹ��ʾ�����ظ��Ĵ���
	private String preStatus;
	private char aOru='a';					//��¼��ǰ�Ĳ�������ӻ����޸�
	
	{
		preStatus="����";
		
		info1="����Ա����";
		info2="����";
		
		info3="���֤";
		info4="����";
		info5="����";
		info6="�绰����";
		
		self="�Զ���";
		
		ENABLE="����";
	}
	
	public OperaAction(){
		initButton();
	}
	
	private void initButton(){
		//��ӡ����¹���Ա��Ϣ����İ�ť������
		AddOrUpdateAdmin.bt1.addActionListener(this);
		AddOrUpdateAdmin.bt2.addActionListener(this);
		AddOrUpdateAdmin.bt3.addActionListener(this);
		AddOrUpdateAdmin.comboBox.addActionListener(this);
		AddOrUpdateAdmin.nameTextField.addFocusListener(new MyFocusListener(info1,AddOrUpdateAdmin.nameTextField));
		AddOrUpdateAdmin.pwdTextField.addFocusListener(new MyFocusListener(info2,AddOrUpdateAdmin.pwdTextField));
		
		AddOrUpdateClient.bt1.addActionListener(this);
		AddOrUpdateClient.bt2.addActionListener(this);
		AddOrUpdateClient.bt3.addActionListener(this);
		AddOrUpdateClient.box1.addActionListener(this);
		AddOrUpdateClient.box2.addActionListener(this);
		AddOrUpdateClient.jt1.addFocusListener(new MyFocusListener(info3,AddOrUpdateClient.jt1));
		AddOrUpdateClient.jt2.addFocusListener(new MyFocusListener(info4,AddOrUpdateClient.jt2));
		AddOrUpdateClient.jt3.addFocusListener(new MyFocusListener(info5,AddOrUpdateClient.jt3));
		AddOrUpdateClient.jt4.addFocusListener(new MyFocusListener(info6,AddOrUpdateClient.jt4));
		
		AddOrUpdateRoom.bt1.addActionListener(this);
		AddOrUpdateRoom.bt2.addActionListener(this);
		AddOrUpdateRoom.bt3.addActionListener(this);
		AddOrUpdateRoom.box1.addActionListener(this);
		
		//���Ƿ�������������Զ���ѡ���Ӽ�����
		AddOrUpdateRoom.box2.addItemListener(new ItemListener() {
			//��ΪCombobox�Լ��������¼��������η�Ӧ��������Ҫ����һ��itemlistener��ר�ż���Combobox��ѡ���¼�	
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
                if(e.getStateChange() == ItemEvent.SELECTED){
                	String type=AddOrUpdateRoom.box2.getSelectedItem().toString();
                	if(type.equals(self))
						new AddRoomType();
                }
			}
		});
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e!=null){
			if(e.getSource()==AddOrUpdateAdmin.bt3){
				AddOrUpdateAdmin.reSet();
				aOru='u';
			}else if(e.getSource()==AddOrUpdateClient.bt3){
				AddOrUpdateClient.reSet();
				aOru='u';
			}else if(e.getSource()==AddOrUpdateRoom.bt3){
				AddOrUpdateRoom.reSet();
				aOru='u';
			}else{
				switch(sFlag){
					case adminBF:{
						auAdmin(e);
					}
					break;
					
					case clientBF:{
						auClient(e);
					}
					break;
					
					case roomBF:{
						auRoom(e);
					}
					break;
					}
			}
		}
	}
	
	public void addOpera(int flag){
		switch(flag){
			case adminBF:{				
				AddOrUpdateAdmin.setJtfText();
				aOru='a';
				sFlag=adminBF;
				aOrUadmin.display();
			}
			break;
			case clientBF:{
				AddOrUpdateClient.setJtfText();
				aOru='a';
				sFlag=clientBF;
				aOrUclient.display();				
			}
			break;
			case roomBF:{
				AddOrUpdateRoom.setjtfText();
				AddOrUpdateRoom.setBox2Item(OperaPojo.roomType());
				aOru='a';
				sFlag=roomBF;
				aOrUroom.display();
			}
			break;
		}
	}
	
	public void updateOpera(int flag , int index){
		switch(flag){
			case adminBF:{
				String aname=ContainerForPojo.list_admin.get(index).getAname();
				String apwd=ContainerForPojo.list_admin.get(index).getApwd();
				String state=ContainerForPojo.list_admin.get(index).getState();

				AddOrUpdateAdmin.setJtfText(aname, apwd, state);
				sFlag=adminBF;
				aOru='u';
				pk=aname;
				aOrUadmin.display();
			}
			break;
			case clientBF:{
				String id=ContainerForPojo.list_clent.get(index).getId();
				String cname=ContainerForPojo.list_clent.get(index).getCname();
				String cpwd=ContainerForPojo.list_clent.get(index).getCpwd();
				String sex=ContainerForPojo.list_clent.get(index).getSex();
				String status=ContainerForPojo.list_clent.get(index).getStatus();
				String phoneNum=ContainerForPojo.list_clent.get(index).getPhoneNum();
				
				
				AddOrUpdateClient.setJtfText(id, cname, cpwd, phoneNum, sex, status);
				sFlag=clientBF;
				aOru='u';
				pk=id;
				aOrUclient.display();
			}
			break;
			case roomBF:{
				String roomID=ContainerForPojo.list_room.get(index).getRoomID();
				int floor=ContainerForPojo.list_room.get(index).getFloor();
				int beds=ContainerForPojo.list_room.get(index).getBeds();
				double fee=ContainerForPojo.list_room.get(index).getFee();
				String status=ContainerForPojo.list_room.get(index).getStatus();
				String type=ContainerForPojo.list_room.get(index).getType();
				
				AddOrUpdateRoom.setBox2Item(dao.OperaPojo.roomType());
				AddOrUpdateRoom.setText(roomID, floor, beds, fee, status, type);
				
				preStatus=status;
				
				sFlag=roomBF;
				aOru='u';
				pk=roomID;
				aOrUroom.display();
			}
			break;
		}
	}

	private void auRoom(ActionEvent e) {
		// TODO Auto-generated method stub
		 if(e.getSource()==AddOrUpdateRoom.bt1){
				String roomID=AddOrUpdateRoom.jtf[0].getText();
				String floor=AddOrUpdateRoom.jtf[1].getText();
				String beds=AddOrUpdateRoom.jtf[2].getText();
				String fee=AddOrUpdateRoom.jtf[3].getText();
				if(Check.checkNull(roomID, floor, beds,fee))
					new MyOptionPanel("����Ϊ��", 1);
				else{
					switch(aOru){
						case 'a':{
							if(service.Check.checkExit(roomID, roomBF))
								new MyOptionPanel("�÷����Ѵ���", 0);
							else{
								String status=AddOrUpdateRoom.box1.getSelectedItem().toString();
								String type=AddOrUpdateRoom.box2.getSelectedItem().toString();
								dao.OperaPojo.addRoom(roomID, type, floor, fee, beds, status);
								new MyOptionPanel("��ӳɹ�", 3);
								aOrUroom.dispose();
							}
						}
						break;
						case 'u':{
							if(Check.checkExit(pk, roomID, roomBF))
								new MyOptionPanel("�÷�����Ѿ�����", 0);
							else{
								
								String status=AddOrUpdateRoom.box1.getSelectedItem().toString();
								String type=AddOrUpdateRoom.box2.getSelectedItem().toString();
								if(0==MyOptionPanel.initConfirmPanel("ȷ���޸ģ�")){
									if(ENABLE.equals(status)){
										OperaPojo.updateBillStatus(pk);
										OperaPojo.updateRoom(pk, roomID, type, floor, fee, beds, status);							
									}else{
										if(preStatus.equals("Ԥ��")||preStatus.equals("��ס"))
											status=preStatus;
										OperaPojo.updateRoom(pk, roomID, type, floor, fee, beds,status);
									}
									new MyOptionPanel("�޸ĳɹ�", 3);
									AddOrUpdateRoom.reSet();
									aOrUroom.dispose();
								}
							}
						}
						break;
					}
				}
		 }else if(e.getSource()==AddOrUpdateRoom.bt2){
				aOrUroom.dispose();
				AddOrUpdateRoom.reSet();
		 }
		 		
	}

	private void auClient(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==AddOrUpdateClient.bt1){
			String id=AddOrUpdateClient.jt1.getText();
			String cname=AddOrUpdateClient.jt2.getText();
			String cpwd=AddOrUpdateClient.jt3.getText();
			String phoneNum=AddOrUpdateClient.jt4.getText();
			if(service.Check.checkDefaultNull(id, info3, cname, info4, cpwd, info5, phoneNum, info6))
				new MyOptionPanel("����Ϊ��", 1);
			else{
				switch(aOru){
					case 'a':{
						if(service.Check.checkExit(id, clientBF))
							new MyOptionPanel("�����֤�Ѵ���", 0);
						else{
							String sex=AddOrUpdateClient.box1.getSelectedItem().toString();
							String status=AddOrUpdateClient.box2.getSelectedItem().toString();
							dao.OperaPojo.addClient(id, cname, cpwd, phoneNum, sex, status);
							new MyOptionPanel("��ӳɹ�", 3);
							aOrUclient.dispose();
						}
					}
					break;
					case 'u':{
						if(service.Check.checkExit(pk, id, clientBF))
							new MyOptionPanel("�����֤�Ѿ�����", 0);
						else{
							String sex=AddOrUpdateClient.box1.getSelectedItem().toString();
							String status=AddOrUpdateClient.box2.getSelectedItem().toString();
							if(0==MyOptionPanel.initConfirmPanel("ȷ��Ҫ�޸���")){
								dao.OperaPojo.updateClient(pk,id, cname, cpwd, sex, status, phoneNum);
								new MyOptionPanel("�޸ĳɹ�", 3);
								AddOrUpdateClient.reSet();
								aOrUclient.dispose();							
							}
						}
					}
					break;
				}
			}
		}else if(e.getSource()==AddOrUpdateClient.bt2){
			aOrUclient.dispose();
			AddOrUpdateClient.reSet();
		}
	}

	private void auAdmin(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==AddOrUpdateAdmin.bt1){
			String aname=AddOrUpdateAdmin.nameTextField.getText();
			String apwd=AddOrUpdateAdmin.pwdTextField.getText();
			if(Check.checkDefaultNull(aname, info1, apwd, info2))
				new MyOptionPanel("����Ա�����ƻ����벻��Ϊ��", 0);
			else{
				switch(aOru){
					case 'a':{
						if(Check.checkExit(aname, adminBF))
							new MyOptionPanel("�������Ѿ�����",0);
						else{
							String state=AddOrUpdateAdmin.comboBox.getSelectedItem().toString();
							OperaPojo.addAdmin(aname, apwd, state);
							new MyOptionPanel("��ӳɹ�", 3);
							aOrUadmin.dispose();
						}
					}
					break;
					case 'u':{
						if(Check.checkExit(pk, aname, adminBF))
							new MyOptionPanel("�������Ѿ�����", 0);
						else{
							String state=AddOrUpdateAdmin.comboBox.getSelectedItem().toString();
							if(0==MyOptionPanel.initConfirmPanel("ȷ��Ҫ�޸���")){
								OperaPojo.updateAdmin(pk, aname, apwd, state);
								new MyOptionPanel("�޸ĳɹ�", 3);
								AddOrUpdateAdmin.reSet();
								aOrUadmin.dispose();							
							}
						}
					}
					break;
				}
			}
				
		}else if(e.getSource()==AddOrUpdateAdmin.bt2){
			aOrUadmin.dispose();
			AddOrUpdateAdmin.reSet();
		}
	}

	public void deletOpera(int flag,int index){
		String pk=null;		
		switch(flag){
			case adminBF:{
				pk=utils.ContainerForPojo.list_admin.get(index).getAname();
			}
			break;
			case clientBF:{
				pk=utils.ContainerForPojo.list_clent.get(index).getId();
			}
			break;
			case roomBF:{
				pk=utils.ContainerForPojo.list_room.get(index).getRoomID();
			}
			break;
		}
		OperaPojo.deleteBill(pk, flag);
		OperaPojo.delete(pk, flag);
		new MyOptionPanel("ɾ���ɹ���",3); 
	}
	
	public void showOrChange(String input,int flag){
		String[] imfor=dao.OperaPojo.imforPojo(input, flag);
		switch(flag){
			case adminBF:{				
				AddOrUpdateAdmin.setJtfText(imfor[0], imfor[1], imfor[2]);
				AddOrUpdateAdmin.setJtfUnEditable();
				sFlag=adminBF;
				pk=imfor[0];
				aOrUadmin.display();
			}
			break;
			case clientBF:{
				AddOrUpdateClient.setJtfText(imfor[0],imfor[1],imfor[2],imfor[5],imfor[3],imfor[4]);
				AddOrUpdateClient.setJtfUnEditable();
				sFlag=clientBF;
				pk=imfor[0];
				aOrUclient.display();
			}
			break;
			case roomBF:{
				AddOrUpdateRoom.setBox2Item(dao.OperaPojo.roomType());
				AddOrUpdateRoom.setText(imfor[0], imfor[1], imfor[2], imfor[3], imfor[4], imfor[5]);
				AddOrUpdateRoom.setJtfUnEditable();
				sFlag=roomBF;
				pk=imfor[0];
				aOrUroom.display();
			}
			break;
		}
	}

}
