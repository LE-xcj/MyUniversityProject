package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Operator.RootControl;

public class Imformation_After_Registor extends JFrame implements ActionListener{
	JPanel panel;
	RootControl control;
	JLabel Interest_label;
	JLabel Degree_label;
	JLabel Location_label;
	JLabel Self_label;
	JTextField Interest_textField;
	JTextField Degree_textField;
	JTextField Location_textField;
	JTextArea Self_textArea;
	JButton Ok;
	JButton Cancel;
	public Imformation_After_Registor(RootControl c) {
		control=c;
		panel =new JPanel();
		Interest_label=new JLabel("   兴趣：");
		Degree_label=new JLabel("   学历：");
		Location_label =new JLabel("   位置：");
		Self_label=new JLabel("    自我简介：");
		
		Interest_textField=new JTextField();
		Degree_textField=new JTextField();
		Location_textField=new JTextField();
		Self_textArea=new JTextArea();
		
		Ok =new JButton("确认");
		Cancel =new JButton("跳过");
		
		Ok.addActionListener(this);
		Cancel.addActionListener(this);
		
		
		panel.setLayout(new GridLayout(5, 2, 3, 2));
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
		this.setTitle("个人信息");
		this.setVisible(true);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		String interest=Interest_textField.getText();
		String degree=Degree_textField.getText();
		String location=Location_textField.getText();
		String introduction=Self_textArea.getText();
		if(e.getSource()==Ok){
			control.listAddress().last.setInterest(interest);
			control.listAddress().last.setDegree(degree);
			control.listAddress().last.setLocation(location);
			control.listAddress().last.setSelf_Introduction(introduction);
			control.listAddress().Update_personl_Imformation_After_registor(interest, degree, location, introduction);
			JOptionPane.showMessageDialog(null, "填写成功！");
			this.dispose();
		}
		if(e.getSource()==Cancel){
			this.dispose();
		}
	}
}
