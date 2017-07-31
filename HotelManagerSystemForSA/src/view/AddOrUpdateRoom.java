package view;

import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.MyButton;
import utils.MyLabel;

public class AddOrUpdateRoom extends JFrame{
	private static MyLabel label[];
	private JPanel mainPanel;
	public static JTextField[] jtf;
	public static MyButton bt1,bt2,bt3;
	public static JComboBox box1,box2;
	
	private final static int beginX=20;
	private final static int beginY=20;
	private final static int labelNum=6;
	private final static int jtfNum=4;
	private final static int colGap=20;
	private final static int rowGap=50;
	private final static int w=80;
	private final static int h=40;
	private final static int rW=100;
	private final static int rH=30;
	
	private final static String[] info={"房号 : ","楼层 : ","床位 : ","收费  元/每晚: ","房间类型 : ","房间状态  :"};
	private final static String[] info2={"可用","禁用"};
	public final static String self="自定义";
	{
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		label=new MyLabel[labelNum];
		jtf=new JTextField[jtfNum];
		
		box1=new JComboBox();
		box2=new JComboBox();
		box1.setModel(new DefaultComboBoxModel(info2));

		
		bt1=new MyButton("source/apply.png", "确认", new Rectangle(10, 355, 96, 35));				
		bt2=new MyButton("source/delete_2.png", "关闭", new Rectangle(131, 355, 96, 35));			
		bt3=new MyButton("source/File_List.png","修改",new Rectangle(10, 355, 96, 35));
		
	}
	
	public AddOrUpdateRoom(){	
		
		int x=beginX;
		int y=beginY;
		for(int i=0;i<labelNum;++i){
			label[i]=new MyLabel(info[i],new Rectangle(x,y,w,h));
			y+=rowGap;	
			mainPanel.add(label[i]);
		}
		
		x=beginX+colGap+w;
		y=beginY;
		for(int i=0;i<jtfNum;++i){
			jtf[i]=new JTextField();
			jtf[i].setBounds(x,y,rW,rH);
			y+=rowGap;
			mainPanel.add(jtf[i]);
		}
		
		box1.setBounds(x,y,rW,rH);
		box2.setBounds(x,y+rowGap,rW,rH);
		mainPanel.add(bt1);
		mainPanel.add(bt2);
		mainPanel.add(box1);
		mainPanel.add(box2);
		mainPanel.add(bt3);
		setBounds(100, 100, 250, 439);
		setContentPane(mainPanel);
	}
	
	public void display(){		
		this.setVisible(true);
	}
	
	public static void setjtfText(){
		for(int i=0;i<jtfNum;++i)
			jtf[i].setText("");
	}
	
	public static void setText(String roomID,int floor , int beds, double fee, String status, String type){
		jtf[0].setText(roomID);
		jtf[1].setText(Integer.toString(floor));
		jtf[2].setText(Integer.toString(beds));
		jtf[3].setText(Double.toString(fee));		
		box1.setSelectedItem(status);		
		box2.setSelectedItem(type);
		
	}
	
	public static void setText(String roomID,String type,String status,String floor,String fee,String beds){
		jtf[0].setText(roomID);
		jtf[1].setText(floor);
		jtf[2].setText(beds);
		jtf[3].setText(fee);		
		box1.setSelectedItem(status);		
		box2.setSelectedItem(type);
	}
		
	public static void setBox2Item(String[] type){
		int length=type.length;
		box2.removeAllItems();
		for(int i=0;i<length;++i){
			box2.addItem(type[i]);
		}
		box2.addItem(self);
		
	}
	
	@SuppressWarnings("unchecked")
	//将新加的房间类型加在自定义选项的前面
	public static void addBox2Item(String type){
		int count=box2.getItemCount();		//选项的总数
		box2.insertItemAt(type, count-1);	
		box2.setSelectedIndex(count-1);		//在房间界面的选择框中自动选择“新加入的房间类型”
	}
	
	public static void setJtfUnEditable(){
		bt1.setVisible(false);
		bt1.setEnabled(false);
		bt3.setVisible(true);
		bt3.setEnabled(true);
		for(int i=0;i<jtfNum;++i)
			jtf[i].setEditable(false);
		box1.setEnabled(false);	
		box2.setEnabled(false);
	}
	
	public static void reSet(){
		bt3.setVisible(false);
		bt3.setEnabled(false);
		bt1.setVisible(true);
		bt1.setEnabled(true);
		for(int i=0;i<jtfNum;++i)
			jtf[i].setEditable(true);
		box1.setEnabled(true);	
		box2.setEnabled(true);
	}
	
}
