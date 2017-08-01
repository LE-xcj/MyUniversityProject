package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import dao.OperaPojo;
import service.Check;
import utils.MyButton;
import utils.MyFocusListener;
import utils.MyLabel;
import utils.MyOptionPanel;

public class CreateBill {	
	
	private final int LABELNUM=5;
	private final int LABELNUM2=4;
	private final int CJTFS=3;
	private final int BEGINX=10;
	private final int BEGINY=50;
	private final int GAPX=80;
	private final int GAPY=50;
	private final int BGAPX=90;
	private final int WEIGHT=80;
	private final int HEIGHT=30;
	private final int LINEGAP=25;
	private final int LINEHEIGHT=5;
	
	private JFrame frame;
	private JPanel mainPanel,clientPanel,billPanel;
	private MyLabel[] clientLabels,billLabels;
	private MyLabel title,clientT,billT,searchT;
	private MyLabel[] clines,blines;
	private MyButton ok,close,search;
	private JTextField[] cjtfs,bjtfs;
	private JTextField searchTextField;
	private JRadioButton r1,r2,r3,r4;
	private ButtonGroup sex,status;
	
	private String roomID;
	private String billNum;
	private String aname;
	private String roomStatus;
	
	private final String ABLE="����";
	private final String CREATEBILL="����";
	private final String CLIENTTITLE="�ͻ���Ϣ";
	private final String BILLTITLE="������Ϣ";
	private final String SEARCHTITLE="���ټ���";
	private final String FEMAIL="��";
	private final String MAIL="Ů";
	private final String NORMAL="��ͨ";
	private final String VIP="VIP";
	private final String LINEURL="source/line3.gif";
	
	private String[]  CLABELIMFOR={"���֤��","������","�ֻ����룺","�Ա�","������"};
	private String[] BLABELIMFOR={"��ס����ţ�","��סʱ�䣺","���ʱ�䣺","����"};
	private Point origin;
	private ImageIcon img1,img2;
	
	private boolean checkClient=true;
	
	public CreateBill(String roomID,String aname,String roomStatus){
		img1=new ImageIcon("source/x.png");
		img2=new ImageIcon("source/x (1).png");
		
		this.roomID=roomID;
		this.aname=aname;
		this.roomStatus=roomStatus;
		
		frame=new JFrame();
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setFocusable(true);
		mainPanel.setOpaque(false);
		
		
		title=new MyLabel(new Rectangle(175, 0, 60, 30), 30);
		title.setText(CREATEBILL);
		mainPanel.add(title);
		
		clientPanel=new JPanel();
		clientPanel.setBounds(10, 45, 190, 340);
		clientPanel.setBackground(Color.WHITE);
		clientPanel.setLayout(null);
		clientPanel.setOpaque(false);
		clientPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainPanel.add(clientPanel);
		initClientPanel();
		
		
		billPanel=new JPanel();
		billPanel.setBounds(200, 45, 210, 340);
		billPanel.setBackground(Color.WHITE);
		billPanel.setLayout(null);
		billPanel.setOpaque(false);
		billPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainPanel.add(billPanel);		
		initBillPanel();
		
		initButton();
		initAction();
		resetJRadioButton();
		drag();
		
		frame.setContentPane(mainPanel);
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 420, 400);
		frame.setVisible(true);
	}
	
	private void initAction() {
		// TODO Auto-generated method stub
		searchTextField.addFocusListener(new MyFocusListener("�ͻ����֤", searchTextField));
		
		close.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				setImag2();
			}
			
			public void mouseExited(MouseEvent e){
				
				setImag1();
			}
			
			public void mouseClicked(MouseEvent e){
				frame.dispose();
			}
		});
		
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				String clientID=searchTextField.getText();
				String[] imfor=null;
				imfor=OperaPojo.clientImfor(clientID);
				
				if(null==(imfor=OperaPojo.clientImfor(clientID))){
					MyOptionPanel.initWarmingPanel("���޴˿ͻ�");
					reset();
					resetJRadioButton();
					checkClient=true;
				}
				else{
					int i;
					for(i=0;i<CJTFS;++i)
						cjtfs[i].setText(imfor[i]);
					
					if(FEMAIL.equals(imfor[i++]))
						r1.setSelected(true);
					else
						r2.setSelected(true);
					
					if(NORMAL.equals(imfor[i]))
						r3.setSelected(true);
					else
						r4.setSelected(true);
					setClientJTFEndit(false);
					checkClient=false;
				}
			}
		});
		
		ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				String clientID=cjtfs[0].getText();
				String cDate=bjtfs[1].getText();
				String lDate=bjtfs[2].getText();
				String deposit=bjtfs[3].getText();
				if(checkClient){
					String phoneNum=cjtfs[2].getText();
					String cname=cjtfs[1].getText();
					if("".equals(clientID))
						MyOptionPanel.initWarmingPanel("�ͻ������֤����");
					else if("".equals(cname))
						MyOptionPanel.initWarmingPanel("�ͻ������ֲ���Ϊ�գ�");
					else if(!Check.isNumber(phoneNum))
						MyOptionPanel.initWarmingPanel("�ֻ������ʽ����");
					else if(!OperaPojo.exitClient(clientID)){ 
						String sex=getSex();
						String status=getStatus();
						OperaPojo.addClient(clientID, cname, status, sex, phoneNum);
					}
				}
				if(!Check.validDate(cDate))
					MyOptionPanel.initWrongPanel("��ס��������");
				else if(!Check.validDate(lDate))
					MyOptionPanel.initWrongPanel("�����������");
				else if(!Check.dateIsRight(cDate, lDate))
					MyOptionPanel.initWrongPanel("��ס������������ڲ�������");
				else if(!Check.isMoney(deposit))
					MyOptionPanel.initWrongPanel("����������");
				else if(OperaPojo.isOverlap(roomID, cDate, lDate, billNum))
					MyOptionPanel.initWarmingPanel("��ʱ����Ѿ��������ͻ�Ԥ����");
				else{
					String billNum=OperaPojo.createBill(clientID, roomID, cDate, lDate, deposit,aname);	
					if(ABLE.equals(roomStatus))
						OperaPojo.updateRoomStatus(roomID);
					MyOptionPanel.initSuccessPanel("�����ɹ������ţ�"+billNum);
					reset();
					clear();
				}
				
			}
			
		});
	}
	
	private void resetJRadioButton(){
		r1.setSelected(true);
		r3.setSelected(true);
	}
	
	
	private String getSex(){
		if(r1.isSelected())
			return FEMAIL;
		return MAIL;
	}
	
	private String getStatus(){
		if(r3.isSelected())
			return NORMAL;
		return VIP;
	}
	
	private void reset(){
		for(int i=0;i<CJTFS;++i)
			cjtfs[i].setText(null);
		setClientJTFEndit(true);
	}
	
	private void setClientJTFEndit(boolean flag){
		for(int i=0;i<CJTFS;++i)
			cjtfs[i].setEditable(flag);
	}

	private void initButton() {
		// TODO Auto-generated method stub
		close=new MyButton(new Rectangle(388,0,32,32));
		setImag1();
		mainPanel.add(close);
	}

	private void setImag1() {
		// TODO Auto-generated method stub
		close.setIcon(img1);
	}
	
	private void setImag2(){
		close.setIcon(img2);
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

	private void initBillPanel() {
		// TODO Auto-generated method stub
		billT=new MyLabel(new Rectangle(60, 0,90, 30),BILLTITLE,0,18);
		billPanel.add(billT);
		billLabels=new MyLabel[LABELNUM2];
		blines=new MyLabel[LABELNUM2];
		int x=BEGINX;
		int y=BEGINY;
		for(int i=0;i<LABELNUM2;++i){
			billLabels[i]=new MyLabel(BLABELIMFOR[i]);
			billLabels[i].setBounds(x,y,WEIGHT,HEIGHT);
			billPanel.add(billLabels[i]);
			y+=GAPY;
		}
		
		y+=GAPY;
		searchT=new MyLabel(SEARCHTITLE);
		searchT.setBounds(x, y, 60, 30);
		clientPanel.add(searchT);
		
		searchTextField=new JTextField();
		searchTextField.setBounds(x+60, y+5, WEIGHT, 20);
		clientPanel.add(searchTextField);
		
		search=new MyButton("source/find.gif", new Rectangle(x+70+WEIGHT,y+6,16,16));
		clientPanel.add(search);
		
		x+=BGAPX;
		y=BEGINY;	
		bjtfs=new JTextField[LABELNUM2];
		for(int i=0;i<LABELNUM2;++i){
			bjtfs[i]=new JTextField();
			bjtfs[i].setBounds(x,y,WEIGHT,HEIGHT);
			bjtfs[i].setBorder(null);
			bjtfs[i].setOpaque(false);
			billPanel.add(bjtfs[i]);
			blines[i]=new MyLabel(new Rectangle(x,y+LINEGAP,WEIGHT,LINEHEIGHT), LINEURL);	
			billPanel.add(blines[i]);
			y+=GAPY;
		}
		
		y+=30;
		ok=new MyButton("source/file_edit48.png", new Rectangle(x+40,y,48,48));
		billPanel.add(ok);
		
		bjtfs[0].setText(roomID);
		bjtfs[0].setEditable(false);
	}

	private void initClientPanel() {
		// TODO Auto-generated method stub
		clientT=new MyLabel(new Rectangle(50, 0, 90, 31),CLIENTTITLE, 0,18);
		clientPanel.add(clientT);
		clientLabels=new MyLabel[LABELNUM];
		cjtfs=new JTextField[CJTFS];
		int x=BEGINX;
		int y=BEGINY;
		
		for(int i=0;i<LABELNUM;++i){
			clientLabels[i]=new MyLabel(CLABELIMFOR[i],new Rectangle(x,y,WEIGHT,HEIGHT));
			clientPanel.add(clientLabels[i]);
			y+=GAPY;
		}
	
		x+=GAPX;
		y=BEGINY;
		cjtfs=new JTextField[CJTFS];
		clines=new MyLabel[CJTFS];
		for(int i=0;i<CJTFS;++i){
			cjtfs[i]=new JTextField();
			cjtfs[i].setBounds(x,y,WEIGHT,HEIGHT);
			cjtfs[i].setBorder(null);
			cjtfs[i].setOpaque(false);
			clientPanel.add(cjtfs[i]);
			clines[i]=new MyLabel(new Rectangle(x,y+LINEGAP,WEIGHT,LINEHEIGHT), LINEURL);
			clientPanel.add(clines[i]);
			y+=GAPY;
		}
				
		sex=new ButtonGroup();
		r1=new JRadioButton(FEMAIL);
		r2=new JRadioButton(MAIL);
		sex.add(r1);
		sex.add(r2);		
	
		status=new ButtonGroup();
		r3=new JRadioButton(NORMAL);
		r4=new JRadioButton(VIP);
		status.add(r3);
		status.add(r4);
		
		int rx=x-10;
		int ry=y+5;
		
		r1.setBounds(rx,ry,40,20);
		r2.setBounds(rx+50,ry,40,20);
		r3.setBounds(rx-5,ry+GAPY,55,20);
		r4.setBounds(rx+50,ry+GAPY,50,20);
		
		clientPanel.add(r1);
		clientPanel.add(r2);
		clientPanel.add(r3);
		clientPanel.add(r4);
			
	}
	
	public void clear(){
		for(int i=0;i<CJTFS;++i)
			cjtfs[i].setText("");
		for(int i=1;i<LABELNUM2;++i)
			bjtfs[i].setText("");
		searchTextField.setText("");
	}
	
	
	
	public static void main(String[] args) {
		dao.DataBase.setConnetion();
		new CreateBill("123","xcj","����");
	}
}
