package view;

import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.MyButton;
import utils.MyLabel;

@SuppressWarnings("unchecked")
public class AddOrUpdateAdmin extends JFrame{
	private JPanel mainPanel;
	private static MyLabel lb1,lb2,lb3;

	public static JTextField nameTextField;
	public static JTextField pwdTextField;
	public static MyButton bt1,bt2,bt3;
	public static JComboBox comboBox;
	
	private static String info1,info2;
	{
		info1="管理员名称";
		info2="密码";
	}
	
	{
		
		lb1=new MyLabel(new Rectangle(28, 103, 48, 48),"source/password-protection.png");
		lb2=new MyLabel(new Rectangle(28, 30, 48, 48),"source/administrator (1).png");		
		lb3=new MyLabel("状态",new Rectangle(38, 171, 54, 21));
		
		nameTextField=new JTextField();
		pwdTextField=new JTextField();
		nameTextField.setBounds(112, 40, 171, 38);
		pwdTextField.setBounds(112, 103, 171, 40);
		
		bt1=new MyButton("source/apply.png","确认",new Rectangle(24, 216, 96, 35));
		bt2=new MyButton( "source/delete_2.png","取消", new Rectangle(164, 216, 96, 35));
		bt3=new MyButton("source/File_List.png","修改",new Rectangle(24, 216, 96, 35));
		
		comboBox= new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"可用", "不可用"}));
		comboBox.setBounds(112, 171, 171, 21);
		
		setBounds(100, 100, 312, 300);
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		
	}
	
	public AddOrUpdateAdmin(){
		mainPanel.add(lb1);
		mainPanel.add(lb2);
		mainPanel.add(nameTextField);
		mainPanel.add(pwdTextField);
		mainPanel.add(lb3);
		mainPanel.add(comboBox);
		mainPanel.add(bt1);
		mainPanel.add(bt2);
		mainPanel.add(bt3);		
	}
	
	public void display(){
		mainPanel.setFocusable(true);
		this.setVisible(true);
	}
	
	public static void setJtfText(){
		nameTextField.setText(info1);
		pwdTextField.setText(info2);
	}
	
	//更新时，将要修改的用户信息显示在“管理员界面”中，并默认是不可编辑
	public static void setJtfText(String aname,String apwd,String state){
		nameTextField.setText(aname);
		pwdTextField.setText(apwd);
		comboBox.setSelectedItem(state);
	}
	
	public static void setJtfUnEditable(){
		bt1.setVisible(false);
		bt1.setEnabled(false);
		bt3.setVisible(true);
		bt3.setEnabled(true);
		nameTextField.setEditable(false);
		pwdTextField.setEditable(false);
		comboBox.setEnabled(false);		
	}
	
	public static void reSet(){
		bt3.setVisible(false);
		bt3.setEnabled(false);
		bt1.setVisible(true);
		bt1.setEnabled(true);
		nameTextField.setEditable(true);
		pwdTextField.setEditable(true);
		comboBox.setEnabled(true);
	}
	
}
