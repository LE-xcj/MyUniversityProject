package GUI;

import javax.swing.*;

import Operator.RootControl;

import java.awt.*;
import java.awt.event.*;

public class FriendList_Frame extends JFrame implements ActionListener{
	final int amount=4;
	int row;
	JLabel[] Name_label;
	JButton[] Delete_button;
	JButton[] Check_button;
	JPanel panel;
	RootControl control;
	public FriendList_Frame(RootControl c) {
		control =c;
		row=control.current.myFriends.AmountOf_friends();
		panel=new JPanel();
		Name_label=new JLabel[amount];
		Delete_button=new JButton[amount];
		Check_button=new JButton[amount];
		for(int out=0;out<amount;++out){
			Name_label[out]=new JLabel();
			Delete_button[out]=new JButton("删除");
			Check_button[out]=new JButton("查看信息");
		}
		panel.setLayout(new GridLayout(row, 2));
		Initial();
		this.setLocation(500, 350);
		this.getContentPane().add(panel);
		this.setTitle("好友");
		this.setSize(300,row*65);
		this.setVisible(true);
			
	}
	
	public void Initial(){
		String text;		
		for(int out=0;out<amount;++out){
			
			if(control.current.myFriends.friends[out]!=null){
				text=control.current.myFriends.friends[out].getName();
				Name_label[out].setText(text);
				panel.add(Name_label[out]);
				
				Check_button[out].addActionListener(this);
				Delete_button[out].addActionListener(this);
				panel.add(Check_button[out]);
				panel.add(Delete_button[out]);				
				
				
			}			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int out=0;out<amount;++out){
			if(e.getSource()==Delete_button[out]){
				if(control.Delete_Friends(out)){
					JOptionPane.showMessageDialog(null, "删除成功！");
				}
				this.dispose();
				new FriendList_Frame(control);
				break;
			}
		}
		
		for(int out=0;out<amount;++out){
			if(e.getSource()==Check_button[out]){
				new Friend_Imformation(control.getFriend(out));
			}
		}
		
	}
	
}
