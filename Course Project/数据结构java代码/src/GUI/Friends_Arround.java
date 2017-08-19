package GUI;

import javax.swing.*;

import Operator.RootControl;

import java.awt.*;
import java.awt.event.*;


public class Friends_Arround extends JFrame implements ActionListener{
	boolean box1,box2,box3;
	RootControl control;
	JPanel panel;
	JCheckBox Box1;
	JCheckBox Box2;
	JCheckBox Box3;
	JButton Search;
	public Friends_Arround(RootControl c) {
		control=c;
		panel=new JPanel();
		Box1=new JCheckBox("��Ȥ");
		Box2=new JCheckBox("λ��");
		Box3=new JCheckBox("ѧ��");
				
		Search=new JButton("����");
		Search.addActionListener(this);
		
		panel.setLayout(new GridLayout(4, 1));
		panel.add(Box1);
		panel.add(Box2);
		panel.add(Box3);
		panel.add(Search);
		
		this.getContentPane().add(panel);
		this.setTitle("�����ܱߵ���");
		this.setSize(300, 100);
		this.setLocation(500, 350);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		box1=Box1.isSelected();
		box2=Box2.isSelected();
		box3=Box3.isSelected();
		if(e.getSource()==Search){
			new Arround_Friends_List(box1, box2, box3, control);
		}
		
	}

}
