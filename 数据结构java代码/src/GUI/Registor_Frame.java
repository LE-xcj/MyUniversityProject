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
		Name_label=new JLabel("�û�����");
		Password_label=new JLabel("���룺");
		Name_textField=new JTextField();
		Password_textField=new JTextField();
		OK_button=new JButton("ȷ��");
		Cancel_button=new JButton("ȡ��");
		
		OK_button.addActionListener(this);
		Cancel_button.addActionListener(this);
		
		panel.setLayout(new GridLayout(3, 2));
		panel.add(Name_label);
		panel.add(Name_textField);
		panel.add(Password_label);
		panel.add(Password_textField);
		panel.add(OK_button);
		panel.add(Cancel_button);
		
		this.setTitle("ע��");
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
			//��ʾע��ɹ�
			if(name.equals("")||password.equals("")){
				JOptionPane.showMessageDialog(null, "�������Ϊ�գ�","��ʾ",JOptionPane.WARNING_MESSAGE);
			}else{
				if(control.Registor(name, password)){				
					JOptionPane.showMessageDialog(null, "ע��ɹ���");
					this.dispose();
					new Imformation_After_Registor(control);					
					
				}else{
					JOptionPane.showMessageDialog(null, "���û����Ѿ����ڣ�\n�����������û���","��ʾ",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		if(e.getSource()==Cancel_button){
			this.dispose();
		}
		
	}
}
