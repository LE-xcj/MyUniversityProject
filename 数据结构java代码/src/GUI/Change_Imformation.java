package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import Operator.RootControl;

public class Change_Imformation extends JFrame implements ActionListener{
	
	
	JPanel panel;
	RootControl control;
	JLabel Name_label;
	JLabel Interest_label;
	JLabel Degree_label;
	JLabel Location_label;
	JLabel Self_label;
	JTextField Name_textField;
	JTextField Interest_textField;
	JTextField Degree_textField;
	JTextField Location_textField;
	JTextArea Self_textArea;
	JButton Ok;
	JButton Cancel;
	public Change_Imformation(RootControl c) {
		control=c;
		panel =new JPanel();
		Name_label=new JLabel("  用户名：");
		Interest_label=new JLabel("   兴趣：");
		Degree_label=new JLabel("   学历：");
		Location_label =new JLabel("   位置：");
		Self_label=new JLabel("    自我简介：");
		
		Name_textField=new JTextField();
		Interest_textField=new JTextField();
		Degree_textField=new JTextField();
		Location_textField=new JTextField();
		Self_textArea=new JTextArea();
		
		Ok =new JButton("确认");
		Cancel =new JButton("取消");
		
		Ok.addActionListener(this);
		Cancel.addActionListener(this);
		
		Name_textField.setText(control.current.getName());
		Interest_textField.setText(control.current.getInterest());
		Degree_textField.setText(control.current.getDegree());
		Location_textField.setText(control.current.getLocation());
		Self_textArea.setText(control.current.getSelf_Introduction());
		
		Name_textField.setEditable(true);
		Interest_textField.setEditable(true);
		Degree_textField.setEditable(true);
		Location_textField.setEditable(true);
		Self_textArea.setEditable(true);
		
		panel.setLayout(new GridLayout(6, 2, 3, 2));
		panel.add(Name_label);
		panel.add(Name_textField);
		panel.add(Interest_label);
		panel.add(Interest_textField);
		panel.add(Degree_label);
		panel.add(Degree_textField);
		panel.add(Location_label);
		panel.add(Location_textField);
		panel.add(Self_label);
		panel.add(Self_textArea);
		panel.add(Ok);
		panel.add(Cancel);
		
		this.getContentPane().add(panel);
		this.setSize(300,300);
		this.setLocation(350, 350);
		this.setTitle(control.current.getName()+"的个人信息");
		this.setVisible(true);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Ok){			
			control.current.setInterest(Interest_textField.getText());
			control.current.setDegree(Degree_textField.getText());
			control.current.setLocation(Location_textField.getText());
			control.current.setSelf_Introduction(Self_textArea.getText());
			control.current.setName("");
			if(control.listAddress().SameName(Name_textField.getText())){
				JOptionPane.showMessageDialog(null, "该用户名已经存在了！","提示",JOptionPane.ERROR_MESSAGE);
			}
			else{
				control.current.setName(Name_textField.getText());
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.dispose();
			}			
		}
		if(e.getSource()==Cancel){
			this.dispose();
			new Personl_Imformation_Frame(control);
		}
	}
}
