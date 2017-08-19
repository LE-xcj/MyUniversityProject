package GUI;

import javax.swing.*;
import javax.swing.text.html.ImageView;

import Operator.RootControl;

import java.awt.*;
import java.awt.event.*;
import java.net.NetworkInterface;


public class Personl_Frame extends JFrame implements ActionListener {
	JPanel panel;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	RootControl control;
	public Personl_Frame(RootControl c) {
		control=c;
		
		panel=new JPanel();
		panel.setLayout(new GridLayout(4, 2, 3, 3));
		button1=new JButton("进入");
		button2=new JButton("进入");
		button3=new JButton("进入");
		button4=new JButton("注销");
		button5=new JButton("退出");
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		
		Icon icon1=new ImageIcon("Imgae/a3u.png");
		label1=new JLabel("  个人信息");
		label2=new JLabel("  好友列表");
		label3=new JLabel("  周边朋友");
		
		
		label1.setIcon(icon1);
		label2.setIcon(new ImageIcon("Imgae/al8.png"));
		label3.setIcon(new ImageIcon("Imgae/find_cho.png"));
		
		panel.add(label1);
		panel.add(button1);
		panel.add(label2);
		panel.add(button2);
		panel.add(label3);
		panel.add(button3);
		panel.add(button5);
		panel.add(button4);
		
		this.setTitle(control.current.getName());
		this.getContentPane().add(panel);
		this.setSize(240,230);
		this.setLocation(500, 300);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button1){
			new Personl_Imformation_Frame(control);
		}
		
		if(e.getSource()==button2){
			new FriendList_Frame(control);
		}
		
		if(e.getSource()==button3){
			new Friends_Arround(control);
		}
		
		if(e.getSource()==button4){
			this.dispose();
			control.listAddress().Update_User_list();
			new Login_Registor_Frame(control);
		}
		
		if(e.getSource()==button5){
			control.listAddress().Update_User_list();
			this.dispose();
		}
	}
	
}
