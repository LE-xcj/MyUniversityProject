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
 * �Ƶ�������
 * 
 * �������Ա�ǩ�����ʾ����Ϊ��
 * ���������ǩ�����ӹ�����壬��������������ͨ���
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
	
	private String[] imfors={"����ţ�","λ�ã�","��λ��","�շѣ�","״̬:","����������","��ǰ��ס�ˣ�"};
	private String[] tableClumn={"������","��ס�����֤","��ס��","��ס������","�绰����","��סʱ��","���ʱ��",
								 "����","Ӧ�����","����ʱ��","������"};
	private final String ABLE="����";
	private final String BOOK="Ԥ��";
	private final String USING="��ס";
	private final String ABLEURL="source/room/prov.gif";
	private final String BOOKURL="source/room/rese.gif";
	private final String USINGURL="source/room/pree.gif";
	private final String ENABLEURL="source/room/stop.gif";
	private final String WARMING="Ԥ������";
	private final String CLIENTBT="�ͻ���ѯ";
	private final String ROOMBT="�����ѯ";
	private final String BILLBT="������ѯ";
	private final String REFRESHBT="ˢ��";
	private final String EXITBT="�˳�";
	
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
		
		currentAdminN=new MyLabel("��ǰ����Ա��", new Rectangle(20, 110, 80, 15));
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
		
		createBill=new JMenuItem("����");
		using=new JMenuItem("��ס");
		complish=new JMenuItem("����");
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
		origin = new Point();	//ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
		
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  //���£�mousePressed ���ǵ����������걻����û��̧��
                    origin.x = e.getX(); 			 //����갴�µ�ʱ���ô��ڵ�ǰ��λ��
                    origin.y = e.getY();
            }
        });
        
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {  //�϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
               
                    Point p = frame.getLocation();  //������϶�ʱ��ȡ���ڵ�ǰλ��
                    //���ô��ڵ�λ��
                    //���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
                    frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
        });
	}


	/*
	 * ��ʼ����ǩ����ж��ٸ���ǩ�����ڿ��ǵ��������Ͳ���Ƶ�����ӻ���٣�
	 * �����������ֻ���ط�������һ��
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
	
	//�����һ�α�ǩ�������������ͼ������
	public void clearPreviousButtons(){
		ContainerForPojo.room_list.clear();
		textPanel.removeAll();		//��ߣ��������󣩵ı�ǩ��Ϣ
		textPanel.repaint();		//�ػ�
		jps[previous].removeAll();
	}
	
	//���ر�ǩ����еĹ�������е������İ�ť
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
		//һ���Ƚ���������е�������������ؽ�ȥ������ٽ���������е�������
		jsps[jpIndex].setViewportView(jps[jpIndex]);			//���һ��Ҫ���������������ƽ�˳�򲻵����������
		
	}
	
	//�������Ϣ���е���ʾ��Ϣ�����̶���ֻ����һ�Σ�
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
	
	
	//������Ϣ��壨��ߣ����ı����ݣ��������Ϣ
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
	
	//���table������
	public void clearTableData(){
		int length=df.getRowCount();
		if(0==length)
			return;
		for(int i=0;i<length;++i)
			df.removeRow(0);		//ÿ���Ƴ�һ�У������еļ�¼���±�ͻ��Զ�����
	}
	
	//��table���������
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
