package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DataBase;
import dao.OperaPojo;
import service.Check;
import utils.MyButton;
import utils.MyLabel;
import utils.MyOptionPanel;

	/*
	 * 营业查询
	 * 输入起始日期、终止日期
	 * 		满足条件：起始日期<=终止日期
	 * 				输入的日期格式必须是yyyy-MM-dd
	 * 				输入的日期符合正常情况
	 * 查询的标准是：起始日期 <= 开单时间 <= 终止日期	
	 * 			   订单必选是“已结账”
	 * 
	 */

@SuppressWarnings("serial")
public class Profit extends JFrame implements ActionListener{
	private JPanel mainPanel;
	private JFrame frame;
	private JTextField jtf1, jtf2;
	private MyLabel title,from ,to,lb,profit;
	private MyButton search,close;
	private Point origin;
	
	public Profit(){
		origin = new Point();
		frame=new JFrame();
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		title=new MyLabel(new Rectangle(110, 0, 136, 35), 28);
		title.setText("营业查询");
		mainPanel.add(title);
		
		from=new MyLabel(new Rectangle(32,75,32,32), 21);
		from.setText("从");
		mainPanel.add(from);
		
		to=new MyLabel(new Rectangle(190, 75,32,32),21);
		to.setText("到");
		mainPanel.add(to);
		
		jtf1=new JTextField();
		jtf1.setBounds(64, 81, 109, 25);
		mainPanel.add(jtf1);
		
		jtf2=new JTextField();
		jtf2.setBounds(230, 81, 109, 25);
		mainPanel.add(jtf2);
		
		lb=new MyLabel(new Rectangle(20, 135, 150, 50), 20);
		lb.setText("盈利金额：");
		mainPanel.add(lb);
		
		profit=new MyLabel(new Rectangle(157, 135, 109, 50),20);
		profit.setText("0.0000");
		profit.setForeground(Color.red);
		mainPanel.add(profit);
		
		search=new MyButton("source/find.png", new Rectangle(290, 140, 32, 32));
		mainPanel.add(search);
		
		close=new MyButton("source/close2.png",new Rectangle(315, 0, 36, 36));
		mainPanel.add(close);
		
		drag();
		init();
		
		frame.setUndecorated(true);
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
		frame.setBounds(100, 100, 350, 190);
	}
	
	private void init() {
		// TODO Auto-generated method stub
		search.addActionListener(this);
		close.addActionListener(this);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String p=null;
		if(e.getSource()==search){
			String dateFrom=jtf1.getText();
			String dateTo=jtf2.getText();
			if(!Check.validDate(dateFrom))
				new MyOptionPanel("开始日期有误！", 0);
			else if(!Check.validDate(dateTo))
				new MyOptionPanel("终止日期有误！", 0);
			else if(!Check.dateIsRight(dateFrom, dateTo)){
				new MyOptionPanel("开始日期与终止日期有误", 0);
			}else{
				p=OperaPojo.calculateProfit(dateFrom, dateTo);
				setProfitText(p);
			}
		}else{
			frame.dispose();
		}
	}
	
	private void setProfitText(String pro) {
		// TODO Auto-generated method stub
		if(pro==null)
			profit.setText("0.000");
		else
			profit.setText(pro);
	}

}
