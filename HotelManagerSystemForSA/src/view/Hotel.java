package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import dao.OperaPojo;
import utils.ContainerForPojo;
import utils.MyButton;
import utils.MyLabel;

	/*
	 * �Ƶ�������
	 * 1��������ֻ����ʾ����
	 * 2��
	 */
public class Hotel extends JFrame{
	private final static int adminBF=0;
	private final static int clientBF=1;
	private final static int roomBF=2;
	
	//��������λ�ò�������
	private final static int beginX=35;			//��������е�һ����ť����ʼ������
	private final static int beginY=30;			//������
	private final static int colGap=70;			//��ť֮�����Ҽ��
	private final static int rowGap=50;			//���¼��
	private final static int iconSize=84;		//����������水ť�Ĵ�С	
	private final static int roomIconW=84;		//��������з��䰴ť��ͼ�곤
	private final static int roomIconH=74;		//��
	private final static int length=4;			//ÿһ�������4����ť
	private final static int change_PanelSize_Num=8;		//�����������еİ�ť����8������ô��Ҫ��ʾ������
	
	//��״̬���������Ͻǣ��У�������ǩ��λ�ò�������
	private final static int sBeginX=4;			//��һ����ǩ��ʼ������		
	private final static int sBeginY=9;
	private final static int sTextW=89;	
	private final static int sTextH=16;
	private final static int sGapX=130;			//���
	private final static int sGapY=20;
	
	//����Ϣ��������ߣ���λ�ò�������
	private final int beginX_T=15;			//��Ϣ���е�һ����ǩ�ĳ�ʼ������
	private final int beginY_T=15;			//������
	private final int textSize=80;			//��ǩ�Ĵ�С
	private final int textGap1=60;			//���¼��
	private final int textColGap1=60;		//���Ҽ��
	private final int textGap2=48;
	private final int textColGap2=60;
	
	//���
	private JPanel mainPanel,searchPanel,operPanel,statusPanel,imforPanelA;
	private JScrollPane aScrollP,scrollPanel;		
	private JPanel jp;
	public static JTable table;
	public static DefaultTableModel df;
		
	private MyLabel lb,totalPage,currentPage;
	private MyLabel[] imforText;
	private MyLabel[] statusImforText;
		
	public JTextField jt;
	public JButton add, delete, update, refresh;
	public MyButton adminB, clientB , roomB , billB , profitB ,feeSet, exitB,search,left,right,theFirst,theLast;
	public MyButton[] buttons;
	public static JComboBox comboBox,comboBox2;
	
	private static String[] adminImfor={"����Ա : ","���� : ","״̬ : "};
	private static String[] clientImfor={"���֤ : ","���� : ","�Ա� : ","��Ȩ : ","�绰 : ","���� �� "};
	private static String[] roomImfor={"���� : ","�������� : ","λ�� : ","��λ : ","�շ� : ","״̬ : "};
	private static String[] adminStatusImfor={"����Ա������ : ","���� : ","������ : "};
	private static String[] clientStatusImfor={"�ͻ������� : ","��ͨ�ͻ� : ","VIP : "};
	private static String[] roomStatusImfor={"�������� : ", "���� : ","ռ�� : "};
	private static String[] searchOption={"����Ա", "�ͻ�","����"};
	private static String[] tableColumn={"������","��ס�����֤","������","��ס����"};
	
	
	@SuppressWarnings("unchecked")
	public Hotel(){
		
		mainPanel=new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);		
		
		operPanel=new JPanel();
		operPanel.setBounds(226, 10, 666, 73);
		operPanel.setLayout(null);
		mainPanel.add(operPanel);
		
		adminB=new MyButton("source/ToolBar/m01.gif", "����Ա");
		adminB.setBounds(0, 0, 86, 77);
		operPanel.add(adminB);
		
		clientB=new MyButton("source/ToolBar/m02.gif", "�ͻ�");
		clientB.setBounds(86, 0, 86, 77);
		operPanel.add(clientB);
		
		roomB=new MyButton("source/ToolBar/068.png", "����");
		roomB.setBounds(173, 0, 86, 77);
		operPanel.add(roomB);
		
		billB=new MyButton("source/ToolBar/entry_preview.png","����");
		billB.setBounds(259, 0, 86, 77);	
		operPanel.add(billB);
		
		profitB=new MyButton("source/ToolBar/m06.gif", "Ӫҵ��ѯ");
		profitB.setBounds(345, 0, 86, 77);
		operPanel.add(profitB);
		
		feeSet=new MyButton("source/ToolBar/m09.gif", "�շ�����");
		feeSet.setBounds(431, 0, 86, 77);
		operPanel.add(feeSet);
		
		exitB=new MyButton("source/ToolBar/m10.gif", "�˳�");
		exitB.setBounds(517, 0, 86, 77);
		operPanel.add(exitB);
			
		searchPanel=new JPanel();
		searchPanel.setBounds(335, 479, 532, 47);
		searchPanel.setLayout(null);
		mainPanel.add(searchPanel);
		
		jt=new JTextField();
		jt.setBounds(98, 10, 360, 32);
		searchPanel.add(jt);
		
		comboBox=new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(searchOption));
		comboBox.setBounds(0, 10, 82, 30);
		searchPanel.add(comboBox);
		
		search=new MyButton("source/find.png",new Rectangle(456, 5, 66, 39));
		searchPanel.add(search);
			
		add=new JButton(new ImageIcon("source/add_user.png"));
		add.setText("���");
		add.setBounds(334, 435, 104, 34);
		mainPanel.add(add);
		
		delete=new JButton(new ImageIcon("source/cancel.gif"));
		delete.setText("ɾ��");
		delete.setBounds(477, 435, 104, 34);
		mainPanel.add(delete);
		
		update=new JButton(new ImageIcon("source/portrait-edit.png"));
		update.setText("�޸�");
		update.setBounds(621, 435, 104, 34);
		mainPanel.add(update);
		
		refresh=new JButton(new ImageIcon("source/refresh.png"));
		refresh.setText("ˢ��");
		refresh.setBounds(753, 435, 104, 34);
		mainPanel.add(refresh);

		jp=new JPanel();
		jp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));		
		jp.setLayout(null);
		
		aScrollP=new JScrollPane();
		aScrollP.setBounds(226, 109, 615, 313);		
		aScrollP.add(jp);
		mainPanel.add(aScrollP);
					
		imforPanelA=new JPanel();
		imforPanelA.setBounds(10, 109, 171, 313);
		imforPanelA.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainPanel.add(imforPanelA);
		
		statusPanel=new JPanel();
		statusPanel.setBounds(10, 10, 171, 89);
		statusPanel.setLayout(null);
		mainPanel.add(statusPanel);
		
		left=new MyButton("source/left2.gif",new Rectangle(447, 85, 24, 24));
		mainPanel.add(left);
		
		right=new MyButton("source/right2.gif", new Rectangle(554, 85, 24, 24));
		mainPanel.add(right);
		
		theFirst=new MyButton("source/left1.gif",new Rectangle(414, 85, 24, 24));
		mainPanel.add(theFirst);
		
		theLast=new MyButton("source/add2.gif", new Rectangle(588, 85, 24, 24));
		mainPanel.add(theLast);
		
		totalPage=new MyLabel(new Rectangle(226, 85, 61, 24), 16);
		currentPage=new MyLabel(new Rectangle(505, 83, 24, 24),17);
		currentPage.setForeground(Color.red);
		mainPanel.add(totalPage);
		mainPanel.add(currentPage);
		
		comboBox2=new JComboBox();
		comboBox2.setBounds(725, 86, 104, 21);
		mainPanel.add(comboBox2);
		
		df=new DefaultTableModel(null,tableColumn);
		table=new JTable();
		table.setModel(df);
		
		scrollPanel=new JScrollPane();
		scrollPanel.setBounds(10, 435, 314, 91);
		scrollPanel.setViewportView(table);
		mainPanel.add(scrollPanel);
			
		initStatusPanel(adminBF, dao.OperaPojo.countTotal(adminBF), dao.OperaPojo.validRow(adminBF, "����"));
		initImforPanelA(adminBF);
		
		this.setBounds(200, 100, 883, 575);
		this.setVisible(true);
		this.setContentPane(mainPanel);
		
	}

	
	//��ʼ����״̬���������Ͻǣ�
	public void initStatusPanel(int flag,int num1,int num2) {
		// TODO Auto-generated method stub
		statusPanel.removeAll();		//�Ƴ���������б�ǩ���
		statusPanel.repaint();			//Ȼ���ػ���壬����������ʾ�µı�ǩ
		int x=sBeginX;
		int y=sBeginY;
		int left=0;
		int right=1;
		
		String[] temp=null;
		//���ݱ�־��ѡ����Ӧ�ı�ǩ���ַ�������
		if(adminBF==flag)
			temp=adminStatusImfor;
		else if(clientBF==flag)
			temp=clientStatusImfor;
		else 
			temp=roomStatusImfor;
		
		statusImforText=new MyLabel[3*2];
		for(int i=0;i<3;++i){
			statusImforText[left]=new MyLabel(temp[i],new Rectangle(x,y,sTextW,sTextH));
			x+=sGapX;
			statusImforText[right]=new MyLabel(1,new Rectangle(x,y,sTextW,sTextH));
			statusPanel.add(statusImforText[left]);
			statusPanel.add(statusImforText[right]);
			x=sBeginX;
			y+=sGapY;
			left+=2;
			right+=2;
		}
			
		statusImforText[1].setText(Integer.toString(num1));
		statusImforText[3].setText(Integer.toString(num2));
		statusImforText[5].setText(Integer.toString(num1-num2));
	}

	
	
	//��ʼ����Ϣ��
	public void initImforPanelA(int flag) {
		// TODO Auto-generated method stub
		imforPanelA.removeAll();		//�Ƴ���������е����
		imforPanelA.repaint();			//����ػ�������Ҫ����Ȼ��岻��������ʾ�����ӵı�ǩ
		
		lb=new MyLabel(new Rectangle(0,0,32,32),"source/document_info.png");
		imforPanelA.add(lb);
		int labelNum=0;
		switch(flag){
			case adminBF:
				labelNum=3;
			break;
			case clientBF:
			case roomBF:
				labelNum=6;
			break;
		}
		
		int x=beginX_T;
		int y=beginY_T;
		int gapX,gapY;
		String[] temp;
		if(adminBF==flag){
			temp=adminImfor;
			gapX=textColGap1;
			gapY=textGap1;
		}
		else if(clientBF==flag){
			temp=clientImfor;
			gapX=textColGap2;
			gapY=textGap2;
		}
		else {
			temp=roomImfor;
			gapX=textColGap2+10;
			gapY=textGap2;
		}
		
		imforText=new MyLabel[labelNum*2];
		int first,second;
		first=0;
		second=1;
		
		imforPanelA.setLayout(null);
		for(int i=0;i<labelNum;++i){
			imforText[first]=new MyLabel(temp[i],new Rectangle(x, y,textSize, textSize));		
			y+=gapY;
			imforPanelA.add(imforText[first]);
			first+=2;
		}
		y=beginY_T;
		x+=gapX;
		for(int i=0;i<labelNum;++i){
			imforText[second]=new MyLabel(0,new Rectangle(x, y,textSize, textSize));
			imforPanelA.add(imforText[second]);
			y+=gapY;
			second+=2;
		}
		
	}
	
	/*
	 * ��ʼ��������壬�����ݰ�ť�ĸ����������Ĵ�С
	 * 0���������Ա״̬
	 * 1������ͻ�״̬
	 * 2��������״̬
	 */

	public void initAScrollP(int size,int flag){
		//���ݰ�ť�ĸ�������jp���Ĵ�С���������<=8����ô�Ͳ���ʾ������������
		if(size>change_PanelSize_Num)
			jp.setPreferredSize(new Dimension(600, 600));
		else
			jp.setPreferredSize(new Dimension(600, 300));
		initButtons(size,flag);
		aScrollP.setViewportView(jp);
	}
	
	
	/*
	 * ��ʼ���������İ�ť
	 * Ҫע�⣺
	 * 	1����ť��λ�á�����
	 *  2����ʾͼ��
	 *  3�����ֻ��ʾ16��ͼ�갴ť
	 *  
	 */
	private void initButtons(int size,int flag) {
		// TODO Auto-generated method stub
		jp.removeAll();
		int w,h;
		w=h=iconSize;
		buttons=new MyButton[size];
		switch(flag){
			case adminBF: {
				w=iconSize;
				h=iconSize;
				setAdminIcon(size);
			}
			break;
			case clientBF:{
				w=iconSize;
				h=iconSize;
				setUserIcon(size);
			}
			break;
			case roomBF:{
				w=roomIconW;
				h=roomIconH;
				setBedIcon(size);
			}
			break;
			
		}
		int x=beginY;
		int y=beginY;
		for(int i=0;i<size;++i){
			if(i%length==0){
				if(i!=0){
					x=beginX;
					y+=h+rowGap;
				}
				buttons[i].setBounds(x,y,w,h);
			}else{
				x+=w+colGap;
				buttons[i].setBounds(x, y, w, h);
			}
			jp.add(buttons[i]);
		}
		
	}

	private void setBedIcon(int size) {
		// TODO Auto-generated method stub
		String clean="source/room/clean.gif";
		String valid="source/room/prov.gif";
		String pree="source/room/pree.gif";
		String book="source/room/rese.gif";
		String unable="source/room/stop.gif";
		String status=null;
		for(int i=0;i<size;++i){
			status=ContainerForPojo.list_room.get(i).getStatus();
			if("����".equals(status))
				buttons[i]=new MyButton(valid,ContainerForPojo.list_room.get(i).getRoomID(),0);
			else if("Ԥ��".equals(status))
				buttons[i]=new MyButton(book,ContainerForPojo.list_room.get(i).getRoomID(),0);
			else if("��ס".equals(status))
				buttons[i]=new MyButton(pree,ContainerForPojo.list_room.get(i).getRoomID(),0);
			else if("���".equals(status))
				buttons[i]=new MyButton(clean,ContainerForPojo.list_room.get(i).getRoomID(),0);
			else 
				buttons[i]=new MyButton(unable,ContainerForPojo.list_room.get(i).getRoomID(),0);
		}
	}

	private void setUserIcon(int size) {
		// TODO Auto-generated method stub
		String url1="source/user_2.png";
		String url2="source/user_1.png";
		for(int i=0;i<size;++i){
			if("��".equals(ContainerForPojo.list_clent.get(i).getSex()))
				buttons[i]=new MyButton(url1,ContainerForPojo.list_clent.get(i).getCname(),0);
			else
				buttons[i]=new MyButton(url2,ContainerForPojo.list_clent.get(i).getCname(),0);
		}
	}

	private void setAdminIcon(int size) {
		// TODO Auto-generated method stub
		String url1="source/ooopic_1495720342.png";
		String url2="source/ooopic_1495720360.png";
		for(int i=0;i<size;++i){
			if("����".equals(ContainerForPojo.list_admin.get(i).getState()))
				buttons[i]=new MyButton(url1,ContainerForPojo.list_admin.get(i).getAname(),0);
			else
				buttons[i]=new MyButton(url2,ContainerForPojo.list_admin.get(i).getAname(),0);
		}
	}
	
	/*
	 * ������Ϣ����е�������Ϣ
	 * Ҫע�⣺
	 * 	1��ֻ�С�����Ա�������ͻ����������䡱���������ܲ�����Ϣ��
	 * 	2������ͬһ����壬ֻ����ʾ�ı�ǩ��������Ϣ��ͬ����
	 */
	public void setAdminText(int index){
		imforText[1].setText(ContainerForPojo.list_admin.get(index).getAname());
		imforText[3].setText(ContainerForPojo.list_admin.get(index).getApwd());
		imforText[5].setText(ContainerForPojo.list_admin.get(index).getState());
	}
	
	public void setClientText(int index){
		imforText[1].setText(ContainerForPojo.list_clent.get(index).getId());
		imforText[3].setText(ContainerForPojo.list_clent.get(index).getCname());
		imforText[5].setText(ContainerForPojo.list_clent.get(index).getSex());
		imforText[7].setText(ContainerForPojo.list_clent.get(index).getStatus());
		imforText[9].setText(ContainerForPojo.list_clent.get(index).getPhoneNum());
		imforText[11].setText(ContainerForPojo.list_clent.get(index).getCpwd());
	}
	
	public void setRoomText(int index){
		imforText[1].setText(ContainerForPojo.list_room.get(index).getRoomID());
		imforText[3].setText(ContainerForPojo.list_room.get(index).getType());
		imforText[5].setText(Integer.toString(ContainerForPojo.list_room.get(index).getFloor())+" ¥");
		imforText[7].setText(Integer.toString(ContainerForPojo.list_room.get(index).getBeds())+" ��");
		imforText[9].setText(Double.toString(ContainerForPojo.list_room.get(index).getFee())+" / ��");
		imforText[11].setText(ContainerForPojo.list_room.get(index).getStatus());
	}
	
	public void setTotalPage(int pageNum){
		String temp=Integer.toString(pageNum);
		String page="��"+temp+"ҳ";
		totalPage.setText(page);
	}
	
	public void setCurrentPage(int cPage){
		String page=Integer.toString(cPage);
		currentPage.setText(page);
	}
	
	@SuppressWarnings("unchecked")
	//���ɸѡ���ѡ��ֵ
	public void addBox2Items(String[] conditions){
		comboBox2.removeAllItems();
		int length=conditions.length;
		for(int i=0;i<length;++i)
			comboBox2.addItem(conditions[i]);
	}
	
	//��ա�����С���������
	public void clear() {
		// TODO Auto-generated method stub
		int length=df.getRowCount();
		if(0==length)
			return;
		for(int i=0;i<length;++i)
			df.removeRow(0);
		ContainerForPojo.list_bill.clear();		
	}
	
	//��ӡ���������
	public void addTableRowData(int index){
		String roomID=buttons[index].getText();
		OperaPojo.initBriefBill(roomID);
		int length=ContainerForPojo.list_bill.size();
		if(0==length)
			return;
		String[][] rowData=OperaPojo.hotelTableDate();
		for(int i=0;i<length;++i)
			df.addRow(rowData[i]);
	}


}
