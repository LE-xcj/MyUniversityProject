package view;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.OperaPojo;
import service.Check;
import utils.ContainerForPojo;
import utils.MyButton;
import utils.MyLabel;
import utils.MyOptionPanel;

@SuppressWarnings("serial")
public class SUClient extends JFrame implements ActionListener{
	private JPanel mainPanel;
	private MyLabel lb1,lb2,lb3,lb4,lb5,lb6;
	public  JTextField jt1,jt2,jt3,jt4;
	private JRadioButton r1,r2,r3,r4;
	private ButtonGroup sex,status;
	public MyButton bt1,bt2,bt3;
	
	private static String MAIL="男";
	private static String FEMAIL="女";
	private static String NORMAL="普通";
	private static String VIP="VIP";
	
	private String clientID;
	private String cStatus;
	private String cSex;
	private String phoneNum;
	private String cname;
	
	
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		lb1=new MyLabel(new Rectangle(22, 27, 48, 48), "source/contact_card.png");	
		lb2=new MyLabel(new Rectangle(22, 91, 48, 48), "source/user (2).png");	
		lb3=new MyLabel(new Rectangle(22, 157, 48, 48), "source/password (1).png");
		lb4=new MyLabel(new Rectangle(22, 222, 48, 48), "source/014.png");
		lb5=new MyLabel("性别", new Rectangle(32, 280, 28, 34));
		lb6=new MyLabel("特征",new Rectangle(32, 324, 28, 34));
				
		bt1=new MyButton("source/apply.png", "确认", new Rectangle(22, 393, 96, 35));
		bt2=new MyButton("source/delete_2.png", "关闭", new Rectangle(154, 393, 96, 35));
		bt3=new MyButton("source/File_List.png","修改",new Rectangle(22, 393, 96, 35));

		jt1=new JTextField();
		jt2=new JTextField();
		jt3=new JTextField();
		jt4=new JTextField();
		jt1.setBounds(98, 37, 149, 34);
		jt2.setBounds(98, 100, 149, 34);
		jt3.setBounds(98, 163, 149, 34);
		jt4.setBounds(98, 228, 149, 34);
				
		sex=new ButtonGroup();
		r1=new JRadioButton(MAIL);
		r2=new JRadioButton(FEMAIL);
		r1.setBounds(100, 287, 50, 21);
		r2.setBounds(180, 287, 50, 21);
		mainPanel.add(r1);
		mainPanel.add(r2);
		sex.add(r1);
		sex.add(r2);
		
		status=new ButtonGroup();
		r3=new JRadioButton(NORMAL);
		r4=new JRadioButton(VIP);
		r3.setBounds(98, 331, 80, 21);
		r4.setBounds(180, 331, 80, 21);
		mainPanel.add(r3);
		mainPanel.add(r4);
		status.add(r3);
		status.add(r4);	
	}
	
	public SUClient(int index,boolean update){	
		this.clientID=ContainerForPojo.client_list.get(index).getClientID();
		this.cname=ContainerForPojo.client_list.get(index).getcName();
		this.phoneNum=ContainerForPojo.client_list.get(index).getPhoneNum();
		this.cStatus=ContainerForPojo.client_list.get(index).getStatus();
		this.cSex=ContainerForPojo.client_list.get(index).getSex();
		addC();
		setEditable(update);
	}
	
	public SUClient(String[] imfor,boolean update){
		int count=0;
		this.clientID=imfor[count++];
		this.cname=imfor[count++];
		this.phoneNum=imfor[count++];
		this.cSex=imfor[count++];
		this.cStatus=imfor[count];
		addC();
		setEditable(update);
	}
	
	private void addC(){
		mainPanel.add(lb1);
		mainPanel.add(lb2);
		mainPanel.add(lb3);
		mainPanel.add(lb4);
		mainPanel.add(lb5);
		mainPanel.add(lb6);
		mainPanel.add(bt1);
		mainPanel.add(bt2);
		mainPanel.add(bt3);
		mainPanel.add(jt1);
		mainPanel.add(jt2);
		mainPanel.add(jt3);
		mainPanel.add(jt4);
		
		initButtonAction();
		setText();
		
		setBounds(100, 100, 288, 490);
		setContentPane(mainPanel);
		setVisible(true);
	}
	
	private void initButtonAction() {
		// TODO Auto-generated method stub
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
	}

	private void setText(){
		jt1.setText(clientID);
		jt2.setText(cname);
		jt3.setText("*************");
		jt4.setText(phoneNum);
		if(MAIL.equals(cSex))
			r1.setSelected(true);
		else 
			r2.setSelected(true);
		if(NORMAL.equals(cStatus))
			r3.setSelected(true);
		else
			r4.setSelected(true);
	}
	
	private void setEditable(boolean update){
		bt1.setVisible(update);
		bt1.setEnabled(update);
		bt3.setVisible(!update);
		bt3.setEnabled(!update);
		jt1.setEditable(false);
		jt2.setEditable(update);
		jt3.setEditable(false);
		jt4.setEditable(update);
			
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt1){
			String cName=jt2.getText();
			String phoneNum=jt4.getText();
			if("".equals(cName))
				MyOptionPanel.initWarmingPanel("客户姓名不能为空！");
			else if(!Check.isNumber(phoneNum))
				MyOptionPanel.initWarmingPanel("手机号码输入有误！");
			else{
				String clientID=jt1.getText();
				String sex=getSex();
				String status=getStatus();
				OperaPojo.updateClientImfor(clientID, cName, phoneNum, status,sex);
				MyOptionPanel.initSuccessPanel("修改成功！");
				this.dispose();
			}				
		}else if(e.getSource()==bt2){
			dispose();
		}else if(e.getSource()==bt3){
			setEditable(true);
		}
	}
	
	private String getSex(){
		if(r1.isSelected())
			return MAIL;
		else
			return FEMAIL;
	}
	
	private String getStatus(){
		if(r3.isSelected())
			return NORMAL;
		else
			return VIP;
	}
	
	public static void main(String[] args) {
		new SUClient(1,true);
	}
	
}
