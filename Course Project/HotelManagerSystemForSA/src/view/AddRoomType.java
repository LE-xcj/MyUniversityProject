package view;

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

import service.Check;
import utils.MyButton;
import utils.MyLabel;
import utils.MyOptionPanel;

	/*
	 * 添加房间类型界面
	 * 1、输入自定义的房间类型，但是房间类型不可以与现有的房间类型重复
	 * 2、添加之后，新的房间类型会出现在“房间界面”选择框中，但是如果没有确认那么该房间类型是不会出现在数据库中的
	 */

public class AddRoomType implements ActionListener{
	private static JPanel mainPanel;
	private static MyLabel label;
	private static JTextField jtf;
	private static MyButton bt1,bt2;
	private JFrame frame;
	private Point origin;
	{
		mainPanel=new JPanel();
		label=new MyLabel("房间类型",new Rectangle(23, 25, 69, 15));
		mainPanel.add(label);
		
		jtf=new JTextField();
		jtf.setBounds(102, 22, 187, 21);
		mainPanel.add(jtf);
		
		bt1=new MyButton("source/choose.gif", "添加",new Rectangle(23, 63, 96, 25));
		mainPanel.add(bt1);
		
		bt2=new MyButton("source/cancel.gif", "取消", new Rectangle(179, 63, 96, 25));
		mainPanel.add(bt2);
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
	}
	
	public AddRoomType(){
		frame=new JFrame();
		origin=new Point();
		mainPanel.setLayout(null);
		frame.setBounds(100, 100, 315, 100);
		frame.setContentPane(mainPanel);
		frame.setUndecorated(true);
		frame.setVisible(true);
		drag();
		
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
		if(e.getSource()==bt1){
			String str=jtf.getText();
			if(Check.checkNull(str))
				new MyOptionPanel("房间类型不能为空", 1);
			else{
				if(Check.checkExitType(str))
					new MyOptionPanel("已经存在这种类型，无需添加！", 1);
				else{
					AddOrUpdateRoom.addBox2Item(str);
					new MyOptionPanel("添加成功!", 3);
					frame.dispose();
				}
			}
			
		}else{
			frame.dispose();
		}
	}
	
}
