package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dao.DataBase;
import dao.OperaPojo;
import pojo.Bill;
import service.Check;
import utils.ContainerForPojo;
import utils.MyOptionPanel;
import view.ComplishBill;
import view.CreateBill;
import view.Hotel;
import view.SearchRoom;
import view.UsingRoom;
import view.WillUsingRoom;

/*
 * 酒店主控制
 * 实现功能：
 * 	  1、房间管理
 * 		一）、按照不同的房间类型进行分面板显示
 * 		二）、显示房间的信息，以及与此有关联且未结账的账单（按照入住日期进行排序）；
 *		三）、开单、房间入住、结账
 *
 *
 *	  2、客户管理
 *	  3、订单管理
 *
 *说明：
 *		1、滚动面板没有分页显示，所以一个滚动面板能够显示无限个同类型的房间
 *		2、滚动面板中的按钮与容器中的房间对象的下标可以一一对应
 *		3、每次切换标签都会重新加载被选标签对应房间类型的房间
 *		4、“刷新”按钮只会对当前被选标签对应的房间类型进行刷新，并不会重新加载房间类型
 *
 *
 *	 
 */

public class HotelAction implements ActionListener{
	private Hotel hotel;
	private int index=-1;
	private static final int BILLNUM=0;
	private static final int CLIENTID=1;
	private static final int CLIENTNAME=2;
	public static String aname;
	@SuppressWarnings("static-access")
	public HotelAction(String aname){
		this.aname=aname;
		hotel=new Hotel(aname);
		initTabPanel();
		OperaPojo.initRoomList("标准单人房");
		hotel.initTabPanelButtons(0);
		initButtonsAction();
		initAction();
		initTabAction();
	}
	
	private void initAction() {
		// TODO Auto-generated method stub
		hotel.createBill.addActionListener(this);
		hotel.using.addActionListener(this);
		hotel.complish.addActionListener(this);
		hotel.clientB.addActionListener(this);
		hotel.roomB.addActionListener(this);
		hotel.billB.addActionListener(this);
		hotel.refreshB.addActionListener(this);
		hotel.exitB.addActionListener(this);
		hotel.warming.addActionListener(this);
	}

	
	//一次加载标签面板的选项标签
	private void initTabPanel(){
		String[] type=OperaPojo.roomType();
		if(type==null)
			return;
		hotel.initTabPanel(type);
	}
	
	
	//响应动作
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==hotel.createBill){
			createBill();
		}else if(e.getSource()==hotel.using){
			usingRoom();
		}else if(e.getSource()==hotel.complish){
			complishBill();
		}else if(e.getSource()==hotel.clientB){
			new ClientAction();
		}else if(e.getSource()==hotel.roomB){
			new SearchRoom();
		}else if(e.getSource()==hotel.billB){
			new OperateBillAction();
		}else if(e.getSource()==hotel.refreshB){
			refreshTabPanel();
		}else if(e.getSource()==hotel.warming){
			new WillUsingRoom();
		}else if(e.getSource()==hotel.exitB){
			System.exit(0);
		}
	}

	
	//开单操作，只要房间不是停用的状态都可以开单，但这里不保证订单能否成功
	private void createBill() {
		// TODO Auto-generated method stub
		if(Check.canBooking(index)){
			String roomID=ContainerForPojo.room_list.get(index).getRoomID();
			String status=ContainerForPojo.room_list.get(index).getStatus();
			new CreateBill(roomID,aname,status);
		}else{
			MyOptionPanel.initWrongPanel("当前房间状态不能下订单");
		}
	}
	
	//将房间设置成入住，只有预定中的房间才可以将房间设置成入住状态
	private void usingRoom() {
		// TODO Auto-generated method stub
		if(Check.canUsingRoom(index)){
			String roomID=ContainerForPojo.room_list.get(index).getRoomID();
			String[] item1=OperaPojo.billImfors(BILLNUM);		//关联订单号
			String[] item2=OperaPojo.billImfors(CLIENTID);		//客户的身份证
			String[] item3=OperaPojo.billImfors(CLIENTNAME);	//客户的名字
			new UsingRoom(roomID, item1, item2, item3);
 		}else{
			MyOptionPanel.initWarmingPanel("目前该房间的状态不能直接入住！");
		}
	}
	
	//结账操作，只有正在入住的客户才能结账
	private void complishBill() {
		// TODO Auto-generated method stub
		if(Check.canComplishBill(index)){
			ArrayList<Bill> bills=ContainerForPojo.bill_list;
			String billNum=ContainerForPojo.room_list.get(index).getBillNum();
			String roomID=ContainerForPojo.room_list.get(index).getRoomID();
			
			int billIndex=Check.billNumIndex(billNum);
			String cName=bills.get(billIndex).getCname();
			String cID=bills.get(billIndex).getCid();
			String deposit=Double.toString(bills.get(billIndex).getDeposit());
			String totalFee=Double.toString(bills.get(billIndex).getTotalFee());
			String cStatus=bills.get(billIndex).getcStatus();
			new ComplishBill(billNum, cName, cID, roomID, deposit, totalFee,cStatus);
		}else{
			MyOptionPanel.initWarmingPanel("目前该房间的状态不能结账！");
		}
	}

	/*
	 * 给滚动面板中的按钮添加鼠标单击的监听器
	 * 		这里，只要滚动面板中的按钮被单击（左键，或右键）都会显示房间的信息和关联订单
	 */
	private void initButtonsAction(){
		int length=hotel.buttons.length;
		for(int i=0;i<length;++i){
			hotel.buttons[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {					
					index=findIndex(e);	
					
					String[] text=OperaPojo.roomImfor(index);		//获取被单击的“房间”（按钮）的信息
					String roomID=text[0];
					//String roomID=ContainerForPojo.room_list.get(index).getRoomID();
					
					OperaPojo.initBill(roomID);				//加载该房间关联的订单（未结账的订单）	
					String[][] data=OperaPojo.getRowData();			//获取这些订单的信息
					
					hotel.setImforText(text);			//设置信息面板为该房间的信息
					hotel.addRowData(data);				//填充主界面下面表格
					
					if(e.getButton()==MouseEvent.BUTTON3)
						hotel.pop.show(hotel.buttons[index], e.getX(), e.getY());
				}
			});
		}
	}
	
	//给标签面板添加监听器，监听标签切换的动作
	private void initTabAction(){
		hotel.tabPanel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				refreshTabPanel();
			}
		});
	}
	
	//根据标签面板的选项值，重绘滚动面板中的组件
	private void refreshTabPanel(){
		hotel.clearPreviousButtons();
		hotel.clearTableData();
		
		int i=hotel.tabPanel.getSelectedIndex();		//获取被选标签的下标
		String type=hotel.tabPanel.getTitleAt(i);		//获取标签的文本内容
		
		OperaPojo.initRoomList(type);
		hotel.initTabPanelButtons(i);
		initButtonsAction();
	}
	
	
	//寻找滚动面板中哪一个按钮被点击了（只对按钮进行单击的监听，可以监听左右键）
	protected int findIndex(MouseEvent e) {
		// TODO Auto-generated method stub
		int length=hotel.buttons.length;
		for(int i=0;i<length;++i){
			if(e.getSource()==hotel.buttons[i]){
				return i;
			}
			
		}
		return -1;
	}

	public static void main(String[] args) {
		DataBase.setConnetion();
		new HotelAction("xcj");
	}


}
