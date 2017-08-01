package GUI;
import javax.swing.*;

import Operator.RootControl;

import java.awt.*;
import java.awt.event.*;


public class Registor_Frame extends JFrame implements ActionListener {
	JPanel panel;
	JLabel Name_label;
	JLabel Password_label;
	JTextField Name_textField;
	JTextField Password_textField;
	JButton OK_button;
	JButton Cancel_button;
	RootControl control;
	public Registor_Frame(RootControl c){
		control=c;
		panel=new JPanel();
		Name_label=new JLabel("用户名：");
		Password_label=new JLabel("密码：");
		Name_textField=new JTextField();
		Password_textField=new JTextField();
		OK_button=new JButton("确认");
		Cancel_button=new JButton("取消");
		
		OK_button.addActionListener(this);
		Cancel_button.addActionListener(this);
		
		panel.setLayout(new GridLayout(3, 2));
		panel.add(Name_label);
		panel.add(Name_textField);
		panel.add(Password_label);
		panel.add(Password_textField);
		panel.add(OK_button);
		panel.add(Cancel_button);
		
		this.setTitle("注册");
		this.getContentPane().add(panel);
		this.setLocation(450, 350);
		this.setSize(220,150);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==OK_button){
			String name=Name_textField.getText();
			String password=Password_textField.getText();
			//提示注册成功
			if(name.equals("")||password.equals("")){
				JOptionPane.showMessageDialog(null, "输入框不能为空！","提示",JOptionPane.WARNING_MESSAGE);
			}else{
				if(control.Registor(name, password)){				
					JOptionPane.showMessageDialog(null, "注册成功！");
					this.dispose();
					new Imformation_After_Registor(control);					
					
				}else{
					JOptionPane.showMessageDialog(null, "该用户名已经存在！\n请重新输入用户名","提示",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		if(e.getSource()==Cancel_button){
			this.dispose();
		}
		
	}
}
