package GUI;

import javax.swing.*;

import Operator.RootControl;
import User.Personl;

import java.awt.*;
import java.awt.event.*;

public class Arround_Friends_List extends JFrame implements ActionListener{
	final int Max_capcity=5;
	int count;
	RootControl control;
	JPanel panel;
	Personl[] Arround_friends;
	JButton[] Add_button;
	JButton[] Read_button;
	JLabel[] Name_label;
	public Arround_Friends_List(boolean box1,boolean box2,boolean box3,RootControl c) {
		count=0;
		control=c;
		panel=new JPanel();
		Arround_friends=new Personl[Max_capcity];
		Add_button=new JButton[Max_capcity];
		Read_button=new JButton[Max_capcity];
		Name_label=new JLabel[Max_capcity];
		
		Initial(box1, box2, box3);
		panel.setLayout(new GridLayout(count, 3));
		
		this.setTitle("周边的人");
		this.setSize(280, 200);
		this.getContentPane().add(panel);
		this.setLocation(500, 350);
		this.setVisible(true);
	}
	
	public void Initial(boolean box1,boolean box2,boolean box3){
		int out=0;
		while(true){
			if(control.previous==null)
				break;
			else{
				if(out>=Max_capcity)
					break;
				Arround_friends[out]=control.Search_NewFriends(box1, box2, box3);
				if(Arround_friends[out]!=null){		
					Name_label[out]=new JLabel(Arround_friends[out].getName());
					Add_button[out]=new JButton("添加");
					Read_button[out]=new JButton("查看信息");
					panel.add(Name_label[out]);
					panel.add(Read_button[out]);
					panel.add(Add_button[out]);
					
					Add_button[out].addActionListener(this);
					Read_button[out].addActionListener(this);
					++out;
					++count;
				}
				
			}
		}
		control.Reset_Previous();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int out=0;out<Max_capcity;++out){
			if(Add_button[out]!=null){
				if(e.getSource()==Add_button[out]){
					if(control.Add_People(Arround_friends[out])){
						JOptionPane.showMessageDialog(null, "添加成功！");
						this.dispose();
					}			
					else
						JOptionPane.showMessageDialog(null, "添加失败！你的好友人数达到上限了！","提示",JOptionPane.WARNING_MESSAGE);
				}
			}
				
		}
		
		for(int out=0;out<Max_capcity;++out){
			if(Read_button[out]!=null){
				if(e.getSource()==Read_button[out]){
					new Friend_Imformation(Arround_friends[out]);
				}
			}
		}
		
	}
}
