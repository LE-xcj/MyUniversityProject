package GUI;

import javax.swing.*;

import Operator.RootControl;

import java.awt.*;
import java.awt.event.*;

public class Personl_Imformation_Frame extends JFrame implements ActionListener{
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
	JButton Change;
	JButton Back;
	public Personl_Imformation_Frame(RootControl c) {
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
		
		Change =new JButton("修改");
		Back =new JButton("返回");
		
		Change.addActionListener(this);
		Back.addActionListener(this);
		
		Name_textField.setText(control.current.getName());
		Interest_textField.setText(control.current.getInterest());
		Degree_textField.setText(control.current.getDegree());
		Location_textField.setText(control.current.getLocation());
		Self_textArea.setText(control.current.getSelf_Introduction());
		
		Name_textField.setEditable(false);
		Interest_textField.setEditable(false);
		Degree_textField.setEditable(false);
		Location_textField.setEditable(false);
		Self_textArea.setEditable(false);
		
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
		panel.add(Change);
		panel.add(Back);
		
		this.getContentPane().add(panel);
		this.setSize(300,300);
		this.setLocation(350, 350);
		this.setTitle(control.current.getName()+"的个人信息");
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Change){	
			this.dispose();
			new Change_Imformation(control);
		}
		
		if(e.getSource()==Back){
			this.dispose();
		}
		
	}
	
}
