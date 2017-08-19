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
		Name_label=new JLabel("�û�����");
		Password_label=new JLabel("���룺");
		Ok_button=new JButton("ȷ��");
		Registor_button=new JButton("ע��");
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
		
		this.setTitle("��¼");
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
			//�ı���Ϊ��
			if(name.equals("")||password.equals("")){
				JOptionPane.showMessageDialog(null,"�������Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
			}else{
				//��¼�ɹ�
				if(control.Login(name, password)){
					this.dispose();
					new Personl_Frame(control);
				}
				//��ʾ���ڣ��˺š����벻ƥ����߸��û�û��ע�ᣩ
				else{
					if(control.listAddress().Exit()){
						JOptionPane.showMessageDialog(null,"�������","��ʾ",JOptionPane.ERROR_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,"���û������ڣ�","��ʾ",JOptionPane.WARNING_MESSAGE);
					}		
				}
			}	
		}
		//ע�����
		if(e.getSource()==Registor_button){
			new Registor_Frame(control);
		}
		
	}
	
}
