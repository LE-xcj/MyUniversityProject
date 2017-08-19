package view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.OperaPojo;
import service.Check;
import utils.MyButton;
import utils.MyLabel;
import utils.MyOptionPanel;

/*
 * ��������
 * 		ֻ��ʾ���˶���������
 * 			1�����˵���
 * 			2�����������֤
 * 			3������������
 * 			4������������
 * 			5����ס����
 * 			6���ۿ�
 * 			7������
 * 			8��Ӧ����û�д��ۣ�
 * 		����Աֻ�ܹ���д������������ۿ�
 * 
 * �ڹ���Ա���˵�������ʾ�ͻ���Ҫ֧������Ǯ
 * ����ǿͻ�Ӧ�����������շ�*����-����*�ۿ�
 */

public class ComplishBill {
	private JFrame frame;
	private JPanel mainPanel;	
	private MyLabel title;
	private MyLabel[] leftLabels;
	private MyLabel[] rightLabels;
	private MyButton ok,close;
	private JTextField jtf;
	
	private String[] INFO={"���ţ�","���������֤��","������������","������������","��ס���䣺"
							,"�ۿۣ�","����","Ӧ����"};
	
	private final int NUMOFLABEL=8;
	private final int BEGINX=25;
	private final int BEGINY=66;
	private final int GAPY=50;
	private final int GAPX=150;
	private final int WEIGHT=94;
	private final int HEIGHT=27;
	private Point origin;
	private String billNum;
	private String clientID;
	private String roomID;
	private String cName;
	private String deposit;
	private String totalFee;
	private String clientStatus;
	private String shouldPay;
	private String discount;
	private ImageIcon img1,img2;
	private final String STATUS="�ѽ���";
	
	//�����ھƵ���������˹��������õ� ���캯��
	public ComplishBill(String billNum,String cName,String clientID,
						String roomID,String deposit,String totalFee,String cStatus){
		this.billNum=billNum;
		this.clientID=clientID;
		this.roomID=roomID;
		this.cName=cName;
		this.deposit=deposit;
		this.totalFee=totalFee;
		this.clientStatus=cStatus;
		
		init();
	}
	
	
	//�����ڶ�����ѯ���������õĹ��캯��
	public ComplishBill(String billNum,String cName,String clientID,
						String roomID,String deposit,String totalFee){
		this.billNum=billNum;
		this.clientID=clientID;
		this.roomID=roomID;
		this.cName=cName;
		this.deposit=deposit;
		this.totalFee=totalFee;
		this.clientStatus=OperaPojo.getClientStatus(clientID);;
			
		init();
	}
	
	private void init(){
		origin = new Point();
		
		img1=new ImageIcon("source/ko-grey.png");
		img2=new ImageIcon("source/cross.png");
		
		frame=new JFrame();
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		title=new MyLabel(new Rectangle(106, 0, 50, 45), 25);
		title.setText("����");
		mainPanel.add(title);
		
		jtf=new JTextField();
		jtf.setBounds(175,316, 60, 27);
		mainPanel.add(jtf);
		
		ok=new MyButton("source/list-accept.png", "ȷ��", new Rectangle(78, 460, 96, 36));
		mainPanel.add(ok);
		close=new MyButton(new Rectangle(246, 5, 24, 24));
		setImg1();
		mainPanel.add(close);
		
		initLabel();
		initAction();
		drag();
		setRightText();
		
		
		frame.setContentPane(mainPanel);
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 276, 498);
		frame.setVisible(true);
	}
	
	private void setRightText(){
		rightLabels[0].setText(billNum);
		rightLabels[1].setText(clientID);
		rightLabels[2].setText(cName);
		rightLabels[3].setText(clientStatus);
		rightLabels[4].setText(roomID);
		rightLabels[5].setText(deposit);
		rightLabels[6].setText(totalFee);
	}

	private void drag() {
		// TODO Auto-generated method stub
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  //���£�mousePressed ���ǵ����������걻����û��̧��
                    origin.x = e.getX();  //����갴�µ�ʱ���ô��ڵ�ǰ��λ��
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

	private void initAction() {
		// TODO Auto-generated method stub
		ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				discount=jtf.getText();
				if("".equals(discount)){		//�����ۿ۶�ȣ���ô��Ĭ����1
					shouldPay=totalFee;
					if(0==MyOptionPanel.initConfirmPanel("���ۿۣ�Ӧ����"+shouldPay)){						
						OperaPojo.updateBill(billNum, shouldPay, STATUS,roomID);
						MyOptionPanel.initSuccessPanel("�ɹ�����");
						frame.dispose();
					}						
				}else if(Check.isDiscount(discount)){		//�ж�������ۿ۶���Ƿ�����
					Double temp=new Double(totalFee);
					Double temp2=new Double(discount);
					shouldPay=Double.toString(Math.round(temp*temp2));		//ʵ�����
					if(0==MyOptionPanel.initConfirmPanel(discount+"�ۺ�Ӧ����"+shouldPay)){
						OperaPojo.updateBill(billNum, shouldPay, STATUS,roomID);
						MyOptionPanel.initSuccessPanel("�ɹ�����");
						frame.dispose();
					}
				}else{
					MyOptionPanel.initWrongPanel("������ۿ�����");
				}
					
				
			}
		});
		
		close.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				setImg2();
			}
			
			public void mouseExited(MouseEvent e){			
				setImg1();
			}
			
			public void mouseClicked(MouseEvent e){
				frame.dispose();
			}
		});
	}

	protected void setImg1() {
		// TODO Auto-generated method stub
		close.setIcon(img1);
	}

	protected void setImg2() {
		// TODO Auto-generated method stub
		close.setIcon(img2);
	}

	private void initLabel() {
		// TODO Auto-generated method stub
		leftLabels=new MyLabel[NUMOFLABEL];
		rightLabels=new MyLabel[NUMOFLABEL-1];
		int x=BEGINX;
		int y=BEGINY;
		for(int i=0;i<NUMOFLABEL;++i){
			leftLabels[i]=new MyLabel(INFO[i]);
			leftLabels[i].setBounds(x,y,WEIGHT,HEIGHT);
			mainPanel.add(leftLabels[i]);
			y+=GAPY;		
		}
		x+=GAPX;
		y=BEGINY;
		int count=0;
		for(int i=0;i<NUMOFLABEL;++i){
			rightLabels[count]=new MyLabel(1, new Rectangle(x,y,WEIGHT,HEIGHT));
			mainPanel.add(rightLabels[count]);
			y+=GAPY;
			if(5==i)
				continue;
			++count;			
		}
	}
	
	
}

