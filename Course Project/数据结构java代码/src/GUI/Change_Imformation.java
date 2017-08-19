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
		Name_label=new JLabel("  �û�����");
		Interest_label=new JLabel("   ��Ȥ��");
		Degree_label=new JLabel("   ѧ����");
		Location_label =new JLabel("   λ�ã�");
		Self_label=new JLabel("    ���Ҽ�飺");
		
		Name_textField=new JTextField();
		Interest_textField=new JTextField();
		Degree_textField=new JTextField();
		Location_textField=new JTextField();
		Self_textArea=new JTextArea();
		
		Ok =new JButton("ȷ��");
		Cancel =new JButton("ȡ��");
		
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
		this.setTitle(control.current.getName()+"�ĸ�����Ϣ");
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
				JOptionPane.showMessageDialog(null, "���û����Ѿ������ˣ�","��ʾ",JOptionPane.ERROR_MESSAGE);
			}
			else{
				control.current.setName(Name_textField.getText());
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				this.dispose();
			}			
		}
		if(e.getSource()==Cancel){
			this.dispose();
			new Personl_Imformation_Frame(control);
		}
	}
}
