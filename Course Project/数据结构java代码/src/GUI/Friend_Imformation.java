package GUI;

import javax.swing.*;

import User.Personl;

import java.awt.*;

public class Friend_Imformation extends JFrame{
	JPanel panel;
	Personl friend;
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
	public Friend_Imformation(Personl f){
		panel =new JPanel();
		friend=f;
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
		
		
		Name_textField.setText(friend.getName());
		Interest_textField.setText(friend.getInterest());
		Degree_textField.setText(friend.getDegree());
		Location_textField.setText(friend.getLocation());
		Self_textArea.setText(friend.getSelf_Introduction());
		
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
		
		this.getContentPane().add(panel);
		this.setSize(300,300);
		this.setLocation(350, 350);
		this.setTitle(friend.getName()+"�ĸ�����Ϣ");
		this.setVisible(true);
	}
}
