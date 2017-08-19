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
	 * 酒店主界面
	 * 1、单纯的只是显示界面
	 * 2、
	 */
public class Hotel extends JFrame{
	private final static int adminBF=0;
	private final static int clientBF=1;
	private final static int roomBF=2;
	
	//滚动面板的位置参数设置
	private final static int beginX=35;			//滚动面板中第一个按钮的起始横坐标
	private final static int beginY=30;			//纵坐标
	private final static int colGap=70;			//按钮之间左右间隔
	private final static int rowGap=50;			//上下间隔
	private final static int iconSize=84;		//滚动面板里面按钮的大小	
	private final static int roomIconW=84;		//滚动面板中房间按钮的图标长
	private final static int roomIconH=74;		//宽
	private final static int length=4;			//每一行最多有4个按钮
	private final static int change_PanelSize_Num=8;		//如果滚动面板中的按钮超过8个，那么就要显示滚动条
	
	//“状态栏”（左上角）中，六个标签的位置参数设置
	private final static int sBeginX=4;			//第一个标签起始横坐标		
	private final static int sBeginY=9;
	private final static int sTextW=89;	
	private final static int sTextH=16;
	private final static int sGapX=130;			//间隔
	private final static int sGapY=20;
	
	//“信息栏”（左边），位置参数设置
	private final int beginX_T=15;			//信息栏中第一个标签的初始横坐标
	private final int beginY_T=15;			//纵坐标
	private final int textSize=80;			//标签的大小
	private final int textGap1=60;			//上下间距
	private final int textColGap1=60;		//左右间距
	private final int textGap2=48;
	private final int textColGap2=60;
	
	//组件
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
	
	private static String[] adminImfor={"管理员 : ","密码 : ","状态 : "};
	private static String[] clientImfor={"身份证 : ","姓名 : ","性别 : ","特权 : ","电话 : ","密码 ： "};
	private static String[] roomImfor={"房号 : ","房间类型 : ","位置 : ","床位 : ","收费 : ","状态 : "};
	private static String[] adminStatusImfor={"管理员总人数 : ","可用 : ","不可用 : "};
	private static String[] clientStatusImfor={"客户总人数 : ","普通客户 : ","VIP : "};
	private static String[] roomStatusImfor={"房间总数 : ", "可用 : ","占用 : "};
	private static String[] searchOption={"管理员", "客户","房间"};
	private static String[] tableColumn={"订单号","入住人身份证","记账人","入住日期"};
	
	
	@SuppressWarnings("unchecked")
	public Hotel(){
		
		mainPanel=new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);		
		
		operPanel=new JPanel();
		operPanel.setBounds(226, 10, 666, 73);
		operPanel.setLayout(null);
		mainPanel.add(operPanel);
		
		adminB=new MyButton("source/ToolBar/m01.gif", "管理员");
		adminB.setBounds(0, 0, 86, 77);
		operPanel.add(adminB);
		
		clientB=new MyButton("source/ToolBar/m02.gif", "客户");
		clientB.setBounds(86, 0, 86, 77);
		operPanel.add(clientB);
		
		roomB=new MyButton("source/ToolBar/068.png", "房间");
		roomB.setBounds(173, 0, 86, 77);
		operPanel.add(roomB);
		
		billB=new MyButton("source/ToolBar/entry_preview.png","订单");
		billB.setBounds(259, 0, 86, 77);	
		operPanel.add(billB);
		
		profitB=new MyButton("source/ToolBar/m06.gif", "营业查询");
		profitB.setBounds(345, 0, 86, 77);
		operPanel.add(profitB);
		
		feeSet=new MyButton("source/ToolBar/m09.gif", "收费设置");
		feeSet.setBounds(431, 0, 86, 77);
		operPanel.add(feeSet);
		
		exitB=new MyButton("source/ToolBar/m10.gif", "退出");
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
		add.setText("添加");
		add.setBounds(334, 435, 104, 34);
		mainPanel.add(add);
		
		delete=new JButton(new ImageIcon("source/cancel.gif"));
		delete.setText("删除");
		delete.setBounds(477, 435, 104, 34);
		mainPanel.add(delete);
		
		update=new JButton(new ImageIcon("source/portrait-edit.png"));
		update.setText("修改");
		update.setBounds(621, 435, 104, 34);
		mainPanel.add(update);
		
		refresh=new JButton(new ImageIcon("source/refresh.png"));
		refresh.setText("刷新");
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
			
		initStatusPanel(adminBF, dao.OperaPojo.countTotal(adminBF), dao.OperaPojo.validRow(adminBF, "可用"));
		initImforPanelA(adminBF);
		
		this.setBounds(200, 100, 883, 575);
		this.setVisible(true);
		this.setContentPane(mainPanel);
		
	}

	
	//初始化“状态栏”（左上角）
	public void initStatusPanel(int flag,int num1,int num2) {
		// TODO Auto-generated method stub
		statusPanel.removeAll();		//移除面板中所有标签组件
		statusPanel.repaint();			//然后重绘面板，这样才能显示新的标签
		int x=sBeginX;
		int y=sBeginY;
		int left=0;
		int right=1;
		
		String[] temp=null;
		//根据标志，选择相应的标签语字符串数组
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

	
	
	//初始化信息栏
	public void initImforPanelA(int flag) {
		// TODO Auto-generated method stub
		imforPanelA.removeAll();		//移除面板中所有的组件
		imforPanelA.repaint();			//这个重绘面板很重要，不然面板不会重新显示新增加的标签
		
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
	 * 初始化滚动面板，并根据按钮的个数设置面板的大小
	 * 0：代表管理员状态
	 * 1：代表客户状态
	 * 2：代表房间状态
	 */

	public void initAScrollP(int size,int flag){
		//根据按钮的个数设置jp面板的大小（如果数量<=8，那么就不显示下拉滚动条）
		if(size>change_PanelSize_Num)
			jp.setPreferredSize(new Dimension(600, 600));
		else
			jp.setPreferredSize(new Dimension(600, 300));
		initButtons(size,flag);
		aScrollP.setViewportView(jp);
	}
	
	
	/*
	 * 初始化滚动面板的按钮
	 * 要注意：
	 * 	1、按钮的位置、数量
	 *  2、显示图标
	 *  3、最多只显示16个图标按钮
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
			if("可用".equals(status))
				buttons[i]=new MyButton(valid,ContainerForPojo.list_room.get(i).getRoomID(),0);
			else if("预定".equals(status))
				buttons[i]=new MyButton(book,ContainerForPojo.list_room.get(i).getRoomID(),0);
			else if("入住".equals(status))
				buttons[i]=new MyButton(pree,ContainerForPojo.list_room.get(i).getRoomID(),0);
			else if("清洁".equals(status))
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
			if("男".equals(ContainerForPojo.list_clent.get(i).getSex()))
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
			if("可用".equals(ContainerForPojo.list_admin.get(i).getState()))
				buttons[i]=new MyButton(url1,ContainerForPojo.list_admin.get(i).getAname(),0);
			else
				buttons[i]=new MyButton(url2,ContainerForPojo.list_admin.get(i).getAname(),0);
		}
	}
	
	/*
	 * 设置信息面板中的文字信息
	 * 要注意：
	 * 	1、只有“管理员”，“客户”，“房间”这三个功能才有信息栏
	 * 	2、都是同一个面板，只是显示的标签数量和信息不同而已
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
		imforText[5].setText(Integer.toString(ContainerForPojo.list_room.get(index).getFloor())+" 楼");
		imforText[7].setText(Integer.toString(ContainerForPojo.list_room.get(index).getBeds())+" 张");
		imforText[9].setText(Double.toString(ContainerForPojo.list_room.get(index).getFee())+" / 晚");
		imforText[11].setText(ContainerForPojo.list_room.get(index).getStatus());
	}
	
	public void setTotalPage(int pageNum){
		String temp=Integer.toString(pageNum);
		String page="共"+temp+"页";
		totalPage.setText(page);
	}
	
	public void setCurrentPage(int cPage){
		String page=Integer.toString(cPage);
		currentPage.setText(page);
	}
	
	@SuppressWarnings("unchecked")
	//添加筛选框的选项值
	public void addBox2Items(String[] conditions){
		comboBox2.removeAllItems();
		int length=conditions.length;
		for(int i=0;i<length;++i)
			comboBox2.addItem(conditions[i]);
	}
	
	//清空“表格中”的数据项
	public void clear() {
		// TODO Auto-generated method stub
		int length=df.getRowCount();
		if(0==length)
			return;
		for(int i=0;i<length;++i)
			df.removeRow(0);
		ContainerForPojo.list_bill.clear();		
	}
	
	//添加“表格数据项”
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
