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
 * �Ƶ�������
 * ʵ�ֹ��ܣ�
 * 	  1���������
 * 		һ�������ղ�ͬ�ķ������ͽ��з������ʾ
 * 		��������ʾ�������Ϣ���Լ�����й�����δ���˵��˵���������ס���ڽ������򣩣�
 *		������������������ס������
 *
 *
 *	  2���ͻ�����
 *	  3����������
 *
 *˵����
 *		1���������û�з�ҳ��ʾ������һ����������ܹ���ʾ���޸�ͬ���͵ķ���
 *		2����������еİ�ť�������еķ��������±����һһ��Ӧ
 *		3��ÿ���л���ǩ�������¼��ر�ѡ��ǩ��Ӧ�������͵ķ���
 *		4����ˢ�¡���ťֻ��Ե�ǰ��ѡ��ǩ��Ӧ�ķ������ͽ���ˢ�£����������¼��ط�������
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
		OperaPojo.initRoomList("��׼���˷�");
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

	
	//һ�μ��ر�ǩ����ѡ���ǩ
	private void initTabPanel(){
		String[] type=OperaPojo.roomType();
		if(type==null)
			return;
		hotel.initTabPanel(type);
	}
	
	
	//��Ӧ����
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

	
	//����������ֻҪ���䲻��ͣ�õ�״̬�����Կ����������ﲻ��֤�����ܷ�ɹ�
	private void createBill() {
		// TODO Auto-generated method stub
		if(Check.canBooking(index)){
			String roomID=ContainerForPojo.room_list.get(index).getRoomID();
			String status=ContainerForPojo.room_list.get(index).getStatus();
			new CreateBill(roomID,aname,status);
		}else{
			MyOptionPanel.initWrongPanel("��ǰ����״̬�����¶���");
		}
	}
	
	//���������ó���ס��ֻ��Ԥ���еķ���ſ��Խ��������ó���ס״̬
	private void usingRoom() {
		// TODO Auto-generated method stub
		if(Check.canUsingRoom(index)){
			String roomID=ContainerForPojo.room_list.get(index).getRoomID();
			String[] item1=OperaPojo.billImfors(BILLNUM);		//����������
			String[] item2=OperaPojo.billImfors(CLIENTID);		//�ͻ������֤
			String[] item3=OperaPojo.billImfors(CLIENTNAME);	//�ͻ�������
			new UsingRoom(roomID, item1, item2, item3);
 		}else{
			MyOptionPanel.initWarmingPanel("Ŀǰ�÷����״̬����ֱ����ס��");
		}
	}
	
	//���˲�����ֻ��������ס�Ŀͻ����ܽ���
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
			MyOptionPanel.initWarmingPanel("Ŀǰ�÷����״̬���ܽ��ˣ�");
		}
	}

	/*
	 * ����������еİ�ť�����굥���ļ�����
	 * 		���ֻҪ��������еİ�ť����������������Ҽ���������ʾ�������Ϣ�͹�������
	 */
	private void initButtonsAction(){
		int length=hotel.buttons.length;
		for(int i=0;i<length;++i){
			hotel.buttons[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {					
					index=findIndex(e);	
					
					String[] text=OperaPojo.roomImfor(index);		//��ȡ�������ġ����䡱����ť������Ϣ
					String roomID=text[0];
					//String roomID=ContainerForPojo.room_list.get(index).getRoomID();
					
					OperaPojo.initBill(roomID);				//���ظ÷�������Ķ�����δ���˵Ķ�����	
					String[][] data=OperaPojo.getRowData();			//��ȡ��Щ��������Ϣ
					
					hotel.setImforText(text);			//������Ϣ���Ϊ�÷������Ϣ
					hotel.addRowData(data);				//���������������
					
					if(e.getButton()==MouseEvent.BUTTON3)
						hotel.pop.show(hotel.buttons[index], e.getX(), e.getY());
				}
			});
		}
	}
	
	//����ǩ�����Ӽ�������������ǩ�л��Ķ���
	private void initTabAction(){
		hotel.tabPanel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				refreshTabPanel();
			}
		});
	}
	
	//���ݱ�ǩ����ѡ��ֵ���ػ��������е����
	private void refreshTabPanel(){
		hotel.clearPreviousButtons();
		hotel.clearTableData();
		
		int i=hotel.tabPanel.getSelectedIndex();		//��ȡ��ѡ��ǩ���±�
		String type=hotel.tabPanel.getTitleAt(i);		//��ȡ��ǩ���ı�����
		
		OperaPojo.initRoomList(type);
		hotel.initTabPanelButtons(i);
		initButtonsAction();
	}
	
	
	//Ѱ�ҹ����������һ����ť������ˣ�ֻ�԰�ť���е����ļ��������Լ������Ҽ���
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
