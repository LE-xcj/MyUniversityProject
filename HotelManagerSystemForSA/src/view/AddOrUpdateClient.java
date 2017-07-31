package view;

import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.MyButton;
import utils.MyLabel;

public class AddOrUpdateClient extends JFrame{
	private JPanel mainPanel;
	private static MyLabel lb1,lb2,lb3,lb4,lb5,lb6;
	public static JTextField jt1,jt2,jt3,jt4;
	public static JComboBox box1,box2;
	public static MyButton bt1,bt2,bt3;
	
	private static String info3,info4,info5,info6;
	{
		info3="身份证";
		info4="姓名";
		info5="密码";
		info6="电话号码";
	}
	
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		lb1=new MyLabel(new Rectangle(22, 27, 48, 48), "source/contact_card.png");	
		lb2=new MyLabel(new Rectangle(22, 91, 48, 48), "source/user (2).png");	
		lb3=new MyLabel(new Rectangle(22, 157, 48, 48), "source/password (1).png");
		lb4=new MyLabel(new Rectangle(22, 222, 48, 48), "source/014.png");
		lb5=new MyLabel("性别", new Rectangle(32, 280, 28, 34));
		lb6=new MyLabel("特征",new Rectangle(32, 324, 28, 34));
				
		bt1=new MyButton("source/apply.png", "确认", new Rectangle(22, 393, 96, 35));
		bt2=new MyButton("source/delete_2.png", "关闭", new Rectangle(154, 393, 96, 35));
		bt3=new MyButton("source/File_List.png","修改",new Rectangle(22, 393, 96, 35));

		jt1=new JTextField();
		jt2=new JTextField();
		jt3=new JTextField();
		jt4=new JTextField();
		jt1.setBounds(98, 37, 149, 34);
		jt2.setBounds(98, 100, 149, 34);
		jt3.setBounds(98, 163, 149, 34);
		jt4.setBounds(98, 228, 149, 34);
				
		box1=new JComboBox();
		box1.setModel(new DefaultComboBoxModel(new String[]{"男","女"}));
		box1.setBounds(98, 287, 149, 21);
		
		box2=new JComboBox();
		box2.setModel(new DefaultComboBoxModel(new String[]{"普通","VIP"}));
		box2.setBounds(98, 331, 149, 21);
		
	}
	
	public AddOrUpdateClient(){	
		mainPanel.add(lb1);
		mainPanel.add(lb2);
		mainPanel.add(lb3);
		mainPanel.add(lb4);
		mainPanel.add(lb5);
		mainPanel.add(lb6);
		mainPanel.add(bt1);
		mainPanel.add(bt2);
		mainPanel.add(bt3);
		mainPanel.add(jt1);
		mainPanel.add(jt2);
		mainPanel.add(jt3);
		mainPanel.add(jt4);
		mainPanel.add(box1);
		mainPanel.add(box2);
		setBounds(100, 100, 288, 490);
		setContentPane(mainPanel);
	}
	
	public void display(){	
		mainPanel.setFocusable(true);		//让面板获得聚焦				
		this.setVisible(true);
	}
	
	public static void setJtfText(){
		jt1.setText(info3);
		jt2.setText(info4);
		jt3.setText(info5);
		jt4.setText(info6);
	}
	
	public static void setJtfText(String id,String cname,String cpwd,String phoneNum,String sex,String status){
		jt1.setText(id);
		jt2.setText(cname);
		jt3.setText(cpwd);
		jt4.setText(phoneNum);
		box1.setSelectedItem(sex);
		box2.setSelectedItem(status);
	}
	
	public static void setJtfUnEditable(){
		bt1.setVisible(false);
		bt1.setEnabled(false);
		bt3.setVisible(true);
		bt3.setEnabled(true);
		jt1.setEditable(false);
		jt2.setEditable(false);
		jt3.setEditable(false);
		jt4.setEditable(false);
		box1.setEnabled(false);	
		box2.setEnabled(false);
	}
	
	public static void reSet(){
		bt3.setVisible(false);
		bt3.setEnabled(false);
		bt1.setVisible(true);
		bt1.setEnabled(true);
		jt1.setEditable(true);
		jt2.setEditable(true);
		jt3.setEditable(true);
		jt4.setEditable(true);
		box1.setEnabled(true);	
		box2.setEnabled(true);
	}
	
}
