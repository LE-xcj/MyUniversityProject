package view;

import java.awt.Color;
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
 */

public class Login {
	private JFrame frame;
	private JPanel mainPanel;
	private MyLabel title,admin,pwd,line1,line2;
	private ImageIcon img1,img2;
	private Point origin;
	public MyButton ok,close;
	public JTextField jtf;
	public JPasswordField jpf;
	
	public Login(){
		
		img1=new ImageIcon("source/x.png");
		img2=new ImageIcon("source/x (1).png");
		
		frame=new JFrame();
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.white);
		
		title=new MyLabel(new Rectangle(205, 0, 80, 47),"登录",30);
		mainPanel.add(title);
		
		admin=new MyLabel(new Rectangle(27, 60, 47, 47), "source/administrator (1).png");
		mainPanel.add(admin);
		
		pwd=new MyLabel(new Rectangle(27, 135, 54, 48), "source/password (1).png");
		mainPanel.add(pwd);
		
		line1=new MyLabel(new Rectangle(107, 94, 218, 15), "source/line3.gif");
		mainPanel.add(line1);
		
		line2=new MyLabel(new Rectangle(107, 168, 218, 15), "source/line3.gif");
		mainPanel.add(line2);
		
		jtf=new JTextField();
		jtf.setBounds(107, 73, 218, 24);
		jtf.setBorder(null);
		mainPanel.add(jtf);
		
		jpf=new JPasswordField();
		jpf.setBounds(107, 147, 218, 24);
		jpf.setBorder(null);
		mainPanel.add(jpf);
		
		ok=new MyButton("source/in.png", new Rectangle(386, 148, 74, 74));
		mainPanel.add(ok);
		
		close=new MyButton("source/x.png", new Rectangle(428, 0, 32, 32));
		mainPanel.add(close);
				
		drag();
		
		frame.setBounds(200, 100, 465, 220);
		frame.setUndecorated(true);
		frame.setContentPane(mainPanel);	
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

	public void setImg1(){
		close.setIcon(img1);
	}
	
	public void setImg2(){
		close.setIcon(img2);
	}
	
	public void close(){
		frame.dispose();
	}
}
