package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Operator.RootControl;

public class Login_Registor_Frame extends JFrame implements ActionListener {
	JLabel Name_label;
	JLabel Password_label;
	JButton Ok_button;
	JButton Registor_button;
	JTextField Name_textfield;
	JPasswordField Password_textfield;
	JPanel panel;
	RootControl control;
	public Login_Registor_Frame(RootControl c){
		control=c;
		panel =new JPanel(); 
		panel.setLayout(new GridLayout(3, 2, 5, 5));
		Name_label=new JLabel("用户名：");
		Password_label=new JLabel("密码：");
		Ok_button=new JButton("确认");
		Registor_button=new JButton("注册");
		Name_textfield=new JTextField();
		Password_textfield=new JPasswordField();
		
		Ok_button.addActionListener(this);
		Registor_button.addActionListener(this);
		
		panel.add(Name_label);
		panel.add(Name_textfield);
		panel.add(Password_label);
		panel.add(Password_textfield);
		panel.add(Ok_button);
		panel.add(Registor_button);
		
		this.setTitle("登录");
		this.getContentPane().add(panel);
		this.setSize(220,150);
		this.setLocation(500, 300);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String name=Name_textfield.getText();
		char[] temp=Password_textfield.getPassword();
		String password=new String(temp);
		
		if(e.getSource()==Ok_button){
			//文本域为空
			if(name.equals("")||password.equals("")){
				JOptionPane.showMessageDialog(null,"输入框不能为空","提示",JOptionPane.WARNING_MESSAGE);
			}else{
				//登录成功
				if(control.Login(name, password)){
					this.dispose();
					new Personl_Frame(control);
				}
				//提示窗口（账号、密码不匹配或者该用户没有注册）
				else{
					if(control.listAddress().Exit()){
						JOptionPane.showMessageDialog(null,"密码错误！","提示",JOptionPane.ERROR_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,"该用户不存在！","提示",JOptionPane.WARNING_MESSAGE);
					}		
				}
			}	
		}
		//注册界面
		if(e.getSource()==Registor_button){
			new Registor_Frame(control);
		}
		
	}
	
}
