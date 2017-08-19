package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import utils.MyButton;
import utils.MyLabel;

/*
 * 登录界面
 * 1、用正则表达式防止sql注入
 * 2、实现无窗口体拖拽界面，以及监听鼠标Enter事件
 */

public class Login extends JFrame {
	private JPanel mainPanel;
	private MyLabel lb_1, lb_2,lb_3,lb_4,lb_5;
	private Point origin;
	private ImageIcon img1,img2;
	private ImageIcon log1,log2;
	public JTextField jt;
	public JPasswordField jpd;
	public MyButton login,close;
	
	public Login(){	
		img1=new ImageIcon("source/close1.png");
		img2=new ImageIcon("source/close2.png");
		
		log1=new ImageIcon("source/log1.png");
		log2=new ImageIcon("source/log2.png");
		
		this.setBounds(100, 100, 500, 320);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		lb_1=new MyLabel(new Rectangle(10,61,91,76),"source/ald.png");
		mainPanel.add(lb_1);
		
		lb_2=new MyLabel(new Rectangle(24,160,65,65),"source/adt.png");
		mainPanel.add(lb_2);
		
		lb_3 =new MyLabel(new Rectangle(122, 124, 269, 15),"source/line.png");
		mainPanel.add(lb_3);
		
		lb_4=new MyLabel("");
		lb_4.setIcon(new ImageIcon("source/line.png"));
		lb_4.setBounds(122, 210, 269, 15);
		mainPanel.add(lb_4);
		
		lb_5=new MyLabel("登录",new Rectangle(220, 0, 80, 49),new Font("微软雅黑", Font.BOLD, 30));
		mainPanel.add(lb_5);
		
		login=new MyButton(new Rectangle(95, 257, 323, 40));
		setLog1();
		mainPanel.add(login);
		
		close=new MyButton(new Rectangle(452, 0, 56, 39));
		setImg1();
		mainPanel.add(close);
		
		jt=new JTextField();
		jt.setBorder(null);
		jt.setBounds(137, 103, 269, 21);
		mainPanel.add(jt);
		
		jpd=new JPasswordField();
		jpd.setBorder(null);
		jpd.setBounds(132, 190, 259, 21);
		mainPanel.add(jpd);
		
		origin = new Point();	//全局的位置变量，用于表示鼠标在窗口上的位置
		
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
                    origin.x = e.getX(); 			 //当鼠标按下的时候获得窗口当前的位置
                    origin.y = e.getY();
            }
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {  //拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
               
                    Point p = getLocation();  //当鼠标拖动时获取窗口当前位置
                    //设置窗口的位置
                    //窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
                    setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
        });
        

		
		this.setUndecorated(true);		//无窗口体
		this.setContentPane(mainPanel);
		this.setVisible(true);
		
	}
	
	//对“关闭”和“登录”这两个按钮进行“单击监听”和“鼠标移进、移出监听”
	public void setImg1(){
		close.setIcon(img1);
	}
	
	public void setImg2(){
		close.setIcon(img2);
	}
	
	public void setLog1(){
		login.setIcon(log1);
	}
	
	public void setLog2(){
		login.setIcon(log2);
	}
	
}
