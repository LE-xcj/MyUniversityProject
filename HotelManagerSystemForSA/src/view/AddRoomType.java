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
	 * ��ӷ������ͽ���
	 * 1�������Զ���ķ������ͣ����Ƿ������Ͳ����������еķ��������ظ�
	 * 2�����֮���µķ������ͻ�����ڡ�������桱ѡ����У��������û��ȷ����ô�÷��������ǲ�����������ݿ��е�
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
		label=new MyLabel("��������",new Rectangle(23, 25, 69, 15));
		mainPanel.add(label);
		
		jtf=new JTextField();
		jtf.setBounds(102, 22, 187, 21);
		mainPanel.add(jtf);
		
		bt1=new MyButton("source/choose.gif", "���",new Rectangle(23, 63, 96, 25));
		mainPanel.add(bt1);
		
		bt2=new MyButton("source/cancel.gif", "ȡ��", new Rectangle(179, 63, 96, 25));
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
		if(e.getSource()==bt1){
			String str=jtf.getText();
			if(Check.checkNull(str))
				new MyOptionPanel("�������Ͳ���Ϊ��", 1);
			else{
				if(Check.checkExitType(str))
					new MyOptionPanel("�Ѿ������������ͣ�������ӣ�", 1);
				else{
					AddOrUpdateRoom.addBox2Item(str);
					new MyOptionPanel("��ӳɹ�!", 3);
					frame.dispose();
				}
			}
			
		}else{
			frame.dispose();
		}
	}
	
}
