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
	 * 管理员、客户、房间添加与更新的控制中心
	 * 1、这里有一个严重的漏洞，就是不能改主键，如果修改了，那么订单也需要修改，我认为在现实中也不应该修改主键才对
	 * 所以在这里，我认为不需要改主键
	 */

public class OperaAction implements ActionListener{
	//添加与更新界面
	public static AddOrUpdateAdmin aOrUadmin=new AddOrUpdateAdmin();		//管理员	
	public static AddOrUpdateClient  aOrUclient=new AddOrUpdateClient();		//客户
	public static AddOrUpdateRoom aOrUroom=new AddOrUpdateRoom();				//房间
	
	//标志
	private final static int adminBF=0;
	private final static int clientBF=1;
	private final static int roomBF=2;
	private static int sFlag=adminBF;		//当前标志
	
	private static String info1,info2,info3,info4,info5,info6;		//输入框的提示语
	private static String ENABLE,self;		//禁用、自定义
	private String pk=null;					//修改的时候，需要用这个变量记录修改前的主键，防止提示主键重复的错误
	private String preStatus;
	private char aOru='a';					//记录当前的操作是添加还是修改
	
	{
		preStatus="可用";
		
		info1="管理员名称";
		info2="密码";
		
		info3="身份证";
		info4="姓名";
		info5="密码";
		info6="电话号码";
		
		self="自定义";
		
		ENABLE="禁用";
	}
	
	public OperaAction(){
		initButton();
	}
	
	private void initButton(){
		//添加、更新管理员信息界面的按钮监听器
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
		
		//这是房间界面下拉的自定义选项，添加监听器
		AddOrUpdateRoom.box2.addItemListener(new ItemListener() {
			//因为Combobox对监听到的事件会有两次反应，所以需要创建一个itemlistener，专门监听Combobox的选择事件	
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
					new MyOptionPanel("不能为空", 1);
				else{
					switch(aOru){
						case 'a':{
							if(service.Check.checkExit(roomID, roomBF))
								new MyOptionPanel("该房号已存在", 0);
							else{
								String status=AddOrUpdateRoom.box1.getSelectedItem().toString();
								String type=AddOrUpdateRoom.box2.getSelectedItem().toString();
								dao.OperaPojo.addRoom(roomID, type, floor, fee, beds, status);
								new MyOptionPanel("添加成功", 3);
								aOrUroom.dispose();
							}
						}
						break;
						case 'u':{
							if(Check.checkExit(pk, roomID, roomBF))
								new MyOptionPanel("该房间号已经存在", 0);
							else{
								
								String status=AddOrUpdateRoom.box1.getSelectedItem().toString();
								String type=AddOrUpdateRoom.box2.getSelectedItem().toString();
								if(0==MyOptionPanel.initConfirmPanel("确定修改？")){
									if(ENABLE.equals(status)){
										OperaPojo.updateBillStatus(pk);
										OperaPojo.updateRoom(pk, roomID, type, floor, fee, beds, status);							
									}else{
										if(preStatus.equals("预定")||preStatus.equals("入住"))
											status=preStatus;
										OperaPojo.updateRoom(pk, roomID, type, floor, fee, beds,status);
									}
									new MyOptionPanel("修改成功", 3);
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
				new MyOptionPanel("不能为空", 1);
			else{
				switch(aOru){
					case 'a':{
						if(service.Check.checkExit(id, clientBF))
							new MyOptionPanel("该身份证已存在", 0);
						else{
							String sex=AddOrUpdateClient.box1.getSelectedItem().toString();
							String status=AddOrUpdateClient.box2.getSelectedItem().toString();
							dao.OperaPojo.addClient(id, cname, cpwd, phoneNum, sex, status);
							new MyOptionPanel("添加成功", 3);
							aOrUclient.dispose();
						}
					}
					break;
					case 'u':{
						if(service.Check.checkExit(pk, id, clientBF))
							new MyOptionPanel("该身份证已经存在", 0);
						else{
							String sex=AddOrUpdateClient.box1.getSelectedItem().toString();
							String status=AddOrUpdateClient.box2.getSelectedItem().toString();
							if(0==MyOptionPanel.initConfirmPanel("确定要修改吗？")){
								dao.OperaPojo.updateClient(pk,id, cname, cpwd, sex, status, phoneNum);
								new MyOptionPanel("修改成功", 3);
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
				new MyOptionPanel("管理员的名称或密码不能为空", 0);
			else{
				switch(aOru){
					case 'a':{
						if(Check.checkExit(aname, adminBF))
							new MyOptionPanel("该名称已经存在",0);
						else{
							String state=AddOrUpdateAdmin.comboBox.getSelectedItem().toString();
							OperaPojo.addAdmin(aname, apwd, state);
							new MyOptionPanel("添加成功", 3);
							aOrUadmin.dispose();
						}
					}
					break;
					case 'u':{
						if(Check.checkExit(pk, aname, adminBF))
							new MyOptionPanel("该名称已经存在", 0);
						else{
							String state=AddOrUpdateAdmin.comboBox.getSelectedItem().toString();
							if(0==MyOptionPanel.initConfirmPanel("确定要修改吗？")){
								OperaPojo.updateAdmin(pk, aname, apwd, state);
								new MyOptionPanel("修改成功", 3);
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
		new MyOptionPanel("删除成功！",3); 
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
