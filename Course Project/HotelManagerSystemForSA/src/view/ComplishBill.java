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

public class ComplishBill {
	private JFrame frame;
	private JPanel mainPanel;	
	private MyLabel title;
	private MyLabel[] leftLabels;
	private MyLabel[] rightLabels;
	private MyButton ok,close;
	private JTextField jtf;
	
	private String[] INFO={"单号：","结账人身份证：","结账人姓名：","结账人特征：","入住房间："
							,"折扣：","订金：","应付金额："};
	
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
	private final String status="已结账";
	
	public ComplishBill(String billNum,String cName,String clientID,String roomID,String deposit,String totalFee){
		this.billNum=billNum;
		this.clientID=clientID;
		this.roomID=roomID;
		this.cName=cName;
		this.deposit=deposit;
		this.totalFee=totalFee;
		this.clientStatus=dao.OperaPojo.getClientStatus(clientID);
		
		origin = new Point();
		
		img1=new ImageIcon("source/ko-grey.png");
		img2=new ImageIcon("source/cross.png");
		
		frame=new JFrame();
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		title=new MyLabel(new Rectangle(106, 0, 50, 45), 25);
		title.setText("结账");
		mainPanel.add(title);
		
		jtf=new JTextField();
		jtf.setBounds(175,316, 60, 27);
		mainPanel.add(jtf);
		
		ok=new MyButton("source/list-accept.png", "确认", new Rectangle(78, 460, 96, 36));
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
            public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
                    origin.x = e.getX();  //当鼠标按下的时候获得窗口当前的位置
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

	private void initAction() {
		// TODO Auto-generated method stub
		ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				discount=jtf.getText();
				if("".equals(discount)){
					shouldPay=totalFee;
					if(0==MyOptionPanel.initConfirmPanel("无折扣，应付金额："+shouldPay)){						
						OperaPojo.updateBill(billNum, shouldPay, status);
						new MyOptionPanel("成功结账", 3);
						frame.dispose();
					}						
				}else if(Check.isDiscount(discount)){
					Double temp=new Double(totalFee);
					Double temp2=new Double(discount);
					shouldPay=Double.toString(Math.round(temp*temp2));
					if(0==MyOptionPanel.initConfirmPanel(discount+"折后应付金额："+shouldPay)){
						OperaPojo.updateBill(billNum, shouldPay, status);
						new MyOptionPanel("成功结账", 3);
						frame.dispose();
					}
				}else{
					new MyOptionPanel("输入的折扣有误！", 0);
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
