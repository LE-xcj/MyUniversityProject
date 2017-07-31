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
	 * Ӫҵ��ѯ
	 * ������ʼ���ڡ���ֹ����
	 * 		������������ʼ����<=��ֹ����
	 * 				��������ڸ�ʽ������yyyy-MM-dd
	 * 				��������ڷ����������
	 * ��ѯ�ı�׼�ǣ���ʼ���� <= ����ʱ�� <= ��ֹ����	
	 * 			   ������ѡ�ǡ��ѽ��ˡ�
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
		title.setText("Ӫҵ��ѯ");
		mainPanel.add(title);
		
		from=new MyLabel(new Rectangle(32,75,32,32), 21);
		from.setText("��");
		mainPanel.add(from);
		
		to=new MyLabel(new Rectangle(190, 75,32,32),21);
		to.setText("��");
		mainPanel.add(to);
		
		jtf1=new JTextField();
		jtf1.setBounds(64, 81, 109, 25);
		mainPanel.add(jtf1);
		
		jtf2=new JTextField();
		jtf2.setBounds(230, 81, 109, 25);
		mainPanel.add(jtf2);
		
		lb=new MyLabel(new Rectangle(20, 135, 150, 50), 20);
		lb.setText("ӯ����");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String p=null;
		if(e.getSource()==search){
			String dateFrom=jtf1.getText();
			String dateTo=jtf2.getText();
			if(!Check.validDate(dateFrom))
				new MyOptionPanel("��ʼ��������", 0);
			else if(!Check.validDate(dateTo))
				new MyOptionPanel("��ֹ��������", 0);
			else if(!Check.dateIsRight(dateFrom, dateTo)){
				new MyOptionPanel("��ʼ��������ֹ��������", 0);
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
