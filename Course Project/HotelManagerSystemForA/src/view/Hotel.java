package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import pojo.Room;
import utils.ContainerForPojo;
import utils.MyButton;
import utils.MyClock;
import utils.MyLabel;

/*
 * 酒店主界面
 * 
 * 主界面以标签面板显示房间为主
 * 在这里，往标签面板添加滚动面板，滚动面板中添加普通面板
 */

public class Hotel {
	private final int BEGINX=10;
	private final int BEGINY=10;
	private final int LWEIGHT=80;
	private final int LHEIGHT=25;
	private final int LGAPY=43;
	
	private final int BBEGINX=25;
	private final int BBEGINY=25;
	private final int BGAPX=90;
	private final int BGAPY=85;
	private final int BWEIGHT=60;
	private final int BHEIGHT=65;
	private final int ROWNUMBUTTON=7;
	
	private int previous=0;
	
	private String[] imfors={"房间号：","位置：","床位：","收费：","状态:","所属订单：","当前入住人："};
	private String[] tableClumn={"订单号","入住人身份证","入住人","入住人特征","电话号码","入住时间","离店时间",
								 "订金","应付金额","开单时间","记账人"};
	private final String ABLE="可用";
	private final String BOOK="预定";
	private final String USING="入住";
	private final String ABLEURL="source/room/prov.gif";
	private final String BOOKURL="source/room/rese.gif";
	private final String USINGURL="source/room/pree.gif";
	private final String ENABLEURL="source/room/stop.gif";
	private final String WARMING="预定提醒";
	private final String CLIENTBT="客户查询";
	private final String ROOMBT="房间查询";
	private final String BILLBT="订单查询";
	private final String REFRESHBT="刷新";
	private final String EXITBT="退出";
	
	public DefaultTableModel df;
	private String aname;

	private JFrame frame;
	private JPanel mainPain,imforPanel,operaPanel,textPanel;
	private MyLabel currentAdminN,adminN,zhku;
	private MyLabel[] imforLabels,textLabels;
	public MyButton[] buttons;
	public JScrollPane[] jsps;
	private JScrollPane jsp;
	public JTabbedPane tabPanel;
	public JTable table;
	public JPanel[] jps;
	public JPopupMenu pop;
	public JMenuItem createBill,using,complish;
	public MyButton clientB,roomB,billB,refreshB,exitB,warming;
	private MyClock clock;
	private Point origin;
	
	public Hotel(String aname){
		this.aname=aname;
				
		frame=new JFrame();
		mainPain=new JPanel();
		mainPain.setLayout(null);	
		
		zhku=new MyLabel(new Rectangle(0, 0, 410, 63), "source/zhku2.png");
		mainPain.add(zhku);
		
		clock=new MyClock();
		clock.setBounds(10, 70, 200, 30);
		mainPain.add(clock);
		
		currentAdminN=new MyLabel("当前管理员：", new Rectangle(20, 110, 80, 15));
		mainPain.add(currentAdminN);
		
		adminN=new MyLabel(1, new Rectangle(132, 110, 69, 15));
		adminN.setText(aname);
		mainPain.add(adminN);
		
		warming=new MyButton("source/tip_21.png",WARMING,0);
		warming.setBounds(400,0,81,73);
		mainPain.add(warming);
		
		operaPanel=new JPanel();
		operaPanel.setLayout(null);
		operaPanel.setBounds(470, 0, 427, 73);
		mainPain.add(operaPanel);
		
		clientB=new MyButton("source/users_17.png",CLIENTBT , 0);
		clientB.setBounds(0, 0, 81, 73);
		operaPanel.add(clientB);
		
		roomB=new MyButton("source/068.png", ROOMBT, 0);
		roomB.setBounds(81, 0, 81, 73);
		operaPanel.add(roomB);
		
		billB=new MyButton("source/invoice (1).png", BILLBT, 0);
		billB.setBounds(164, 0, 81, 73);
		operaPanel.add(billB);
		
		refreshB=new MyButton("source/view-refresh.png", REFRESHBT, 0);
		refreshB.setBounds(245, 0, 81, 73);
		operaPanel.add(refreshB);
		
		exitB=new MyButton("source/system-shutdown.png", EXITBT, 0);
		exitB.setBounds(326, 0, 81, 73);
		operaPanel.add(exitB);
		
		imforPanel=new JPanel();
		imforPanel.setLayout(null);
		imforPanel.setBounds(10, 132, 85, 290);
		imforPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainPain.add(imforPanel);
		
		textPanel=new JPanel();
		textPanel.setBounds(105, 132, 107, 290);
		textPanel.setLayout(null);
		mainPain.add(textPanel);
		
		tabPanel=new JTabbedPane(JTabbedPane.TOP);
		tabPanel.setBounds(212, 77, 645, 345);
		mainPain.add(tabPanel);
		
		jsp=new JScrollPane();	
		jsp.setBounds(10, 435, 848, 94);
		mainPain.add(jsp);
		
		table=new JTable();
		jsp.setViewportView(table);
		
		df=new DefaultTableModel(null, tableClumn);
		table.setModel(df);
		
		
		pop=new JPopupMenu();
		
		createBill=new JMenuItem("开单");
		using=new JMenuItem("入住");
		complish=new JMenuItem("结账");
		pop.add(createBill);
		pop.add(using);
		pop.add(complish);
		
		initImforPanel();
		
		drag();
		frame.setBounds(200, 100, 875, 540);
		frame.setContentPane(mainPain);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
	
	
	private void drag() {
		// TODO Auto-generated method stub
		origin = new Point();	//全局的位置变量，用于表示鼠标在窗口上的位置
		
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
                    origin.x = e.getX(); 			 //当鼠标按下的时候获得窗口当前的位置
                    origin.y = e.getY();
            }
        });
        
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {  //拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
               
                    Point p = frame.getLocation();  //当鼠标拖动时获取窗口当前位置
                    //设置窗口的位置
                    //窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
                    frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
        });
	}


	/*
	 * 初始化标签面板有多少个标签（由于考虑到房间类型不会频繁增加或减少）
	 * 所以在这里就只加载房间类型一次
	 */
	public void initTabPanel(String[] roomType){	
		int length=roomType.length;
		jsps=new JScrollPane[length];
		jps=new JPanel[length];
		for(int i=0;i<length;++i){
			jsps[i]=new JScrollPane();
			jps[i]=new JPanel();
			jps[i].setLayout(null);
			tabPanel.add(roomType[i], jsps[i]);
			jsps[i].add(jps[i]);
		}
	}
	
	//清除上一次标签面板的组件，否认图标会残留
	public void clearPreviousButtons(){
		ContainerForPojo.room_list.clear();
		textPanel.removeAll();		//左边（不是最左）的标签信息
		textPanel.repaint();		//重绘
		jps[previous].removeAll();
	}
	
	//加载标签面板中的滚动面板中的面板里的按钮
	public void initTabPanelButtons(final int jpIndex){
		previous=jpIndex;
		ArrayList<Room> rooms=ContainerForPojo.room_list;
		int length=rooms.size();
		int time=length/ROWNUMBUTTON+1;
		int x=BBEGINX;
		int y=BBEGINY;
		buttons=new MyButton[length];
		String roomID,url,status;
		for(int i=0;i<length;++i){
			status=rooms.get(i).getStatus();
			roomID=rooms.get(i).getRoomID();
			if(ABLE.equals(status))
				url=ABLEURL;
			else if(BOOK.equals(status))
				url=BOOKURL;
			else if(USING.equals(status))
				url=USINGURL;
			else 
				url=ENABLEURL;
			buttons[i]=new MyButton(url, roomID, 1);
			buttons[i].setBounds(x, y,BWEIGHT , BHEIGHT);
			jps[jpIndex].add(buttons[i]);
			if(0==(i+1)%ROWNUMBUTTON){
				y+=BGAPY;
				x=BBEGINX;
			}else{
				x+=BGAPX;
			}
		}
		jps[jpIndex].setPreferredSize(new Dimension(645, 84*time));
		//一定先将滚动面板中的面板里的组件加载进去，最后再将滚动面板中的面板可视
		jsps[jpIndex].setViewportView(jps[jpIndex]);			//这个一定要放在这里，如果语句的推进顺序不当会出现问题
		
	}
	
	//最左边信息栏中的提示信息，（固定，只加载一次）
	public void initImforPanel(){
		int length=imfors.length;
		int x=BEGINX;
		int y=BEGINY;
		imforLabels=new MyLabel[length];
		for(int i=0;i<length;++i){
			imforLabels[i]=new MyLabel(imfors[i]);
			imforLabels[i].setBounds(x,y,LWEIGHT,LHEIGHT);
			imforPanel.add(imforLabels[i]);
			y+=LGAPY;
		}
	}
	
	
	//设置信息面板（左边）的文本类容，房间的信息
	public void setImforText(String[] text){
		textPanel.removeAll();
		textPanel.repaint();
		int length=text.length;
		int x=BEGINX;
		int y=BEGINY;
		textLabels=new MyLabel[length];
		for(int i=0;i<length;++i){
			textLabels[i]=new MyLabel(text[i], 1);
			textLabels[i].setBounds(x,y,LWEIGHT,LHEIGHT);
			textPanel.add(textLabels[i]);
			y+=LGAPY;
		}
	}
	
	//清除table中数据
	public void clearTableData(){
		int length=df.getRowCount();
		if(0==length)
			return;
		for(int i=0;i<length;++i)
			df.removeRow(0);		//每当移除一行，其他行的记录的下标就会自动更新
	}
	
	//往table中添加数据
	public void addRowData(String[][] data){
		clearTableData();
		if(null==data)
			return;
		int length=data.length;
		for(int i=0;i<length;++i){
			df.addRow(data[i]);	
		}
	}
	
	public String getAname() {
		return aname;
	}
	
	
}
