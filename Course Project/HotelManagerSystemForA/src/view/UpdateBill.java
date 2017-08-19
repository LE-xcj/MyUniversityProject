package view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dao.OperaPojo;
import service.Check;
import utils.MyButton;
import utils.MyFocusListener;
import utils.MyLabel;
import utils.MyOptionPanel;

/*
 * 修改的内容：房号、入住日期、离店日期、订金
 * 1、只显示已经存在而且不是禁用的房间
 * 2、检测入住日期与离店日期是否符合正常情况（日期合法、入住日期 <= 离店日期）
 * 3、是否与其他订单的入住日期和离店日期有冲突
 * 			一）、检索该房间所有未结账的账单的入住日期和离店日期
 * 			二）、比较更改的日期与其他订单的日期是否具有重叠
 * 			三）、即： 其他订单的入住日期 <=  更改的入住日期  <= 其他订单的离店日期  （重叠）
 * 					其他订单的入住日期<=  更改的离店日期   <= 其他订单的离店日期  （重叠）
 * 					更改的日期<=其他订单的入住日期  且  其他订单的离店日期 <= 更改的离店日期
 * 凡是重叠都是不符合的
 * 4、订金是否合法
 */

@SuppressWarnings("serial")
public class UpdateBill extends JFrame implements ActionListener{
	private JFrame frame;
	private JPanel mainPanel;
	private JTextField jtf1,jtf2,jtf3;
	private MyLabel lb1,lb2,lb3,lb4,title;
	private MyButton bt1,bt2;
	private JComboBox<String> box;
    private Point origin= new Point();
    private String billNum,roomID,cDate,lDate,deposit;
	
    public UpdateBill(){
    	
    }
	public UpdateBill(String billNum,String roomID,String cDate,String lDate,String deposit){
		
		this.billNum=billNum;
		this.roomID=roomID;
		this.cDate=cDate;
		this.lDate=lDate;
		this.deposit=deposit;
		
		frame=new JFrame();
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		title=new MyLabel(new Rectangle(165, 0, 200, 28), 25);
		mainPanel.add(title);
		
		lb1=new MyLabel(new Rectangle(26, 82, 95, 15), "source/browse.gif");
		lb1.setText("房间号：");
		mainPanel.add(lb1);
		
		box=new JComboBox<String>();
		box.setBounds(107, 79, 107, 21);
		mainPanel.add(box);
		
		lb2=new MyLabel(new Rectangle(256, 82, 95, 15), "source/u04.gif");
		lb2.setText("订金:");
		mainPanel.add(lb2);
		
		jtf1=new JTextField();
		jtf1.setBounds(338, 79, 107, 21);
		mainPanel.add(jtf1);		
		
		lb3=new MyLabel("入住时间:",new Rectangle(26, 126, 95, 15));
		mainPanel.add(lb3);
		
		jtf2=new JTextField();
		jtf2.setBounds(107, 123, 107, 21);
		mainPanel.add(jtf2);
		
		lb4=new MyLabel("离店时间:",new Rectangle(256, 126, 95, 15));
		mainPanel.add(lb4);
		
		jtf3=new JTextField();
		jtf3.setBounds(338, 123, 107, 21);
		mainPanel.add(jtf3);
		
		bt1=new MyButton("source/apply.png", "确认",new Rectangle(80, 176, 96, 30));
		mainPanel.add(bt1);
		
		bt2=new MyButton("source/delete_2.png", "取消",new Rectangle(304, 176,96, 30));
		mainPanel.add(bt2);
		
		drag();
		init();
		initItem();
		frame.setBounds(100, 100, 485, 232);
		frame.setContentPane(mainPanel);
		frame.setUndecorated(true);
		frame.setVisible(true);
	}
	
	private void initItem() {
		// TODO Auto-generated method stub
		String[] room=OperaPojo.AllroomID();
		int length=room.length;
		for(int i=0;i<length;++i)
			box.addItem(room[i]);
		
		box.setSelectedItem(roomID);
	}

	private void init() {
		// TODO Auto-generated method stub
		String str="修改 "+billNum+" 订单";
		title.setText(str);
		jtf1.setText(deposit);
		jtf2.setText(cDate);
		jtf3.setText(lDate);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		box.addActionListener(this);
		jtf1.addFocusListener(new MyFocusListener(deposit, jtf1));
		jtf2.addFocusListener(new MyFocusListener(cDate, jtf2));
		jtf3.addFocusListener(new MyFocusListener(lDate, jtf3));
	}

	private void drag() {
		// TODO Auto-generated method stub

        
        frame.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
                        origin.x = e.getX();  //当鼠标按下的时候获得窗口当前的位置
                        origin.y = e.getY();
                }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {  //拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
                   
                        Point p = frame.getLocation();  //当鼠标拖动时获取窗口当前位置
						//设置窗口的位置
                        //窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
                        frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
                }
        });
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt1){
			String roomID=box.getSelectedItem().toString();
			String deposit=jtf1.getText();
			String cDate=jtf2.getText();
			String lDate=jtf3.getText();
			if(!Check.isMoney(deposit))
				MyOptionPanel.initWrongPanel("订金的金额违法！");
			else if(!Check.validDate(cDate))
				MyOptionPanel.initWrongPanel("入住日期有误！");
			else if(!Check.validDate(lDate))
				 MyOptionPanel.initWrongPanel("离店日期有误！"); 
			else if(!Check.dateIsRight(cDate, lDate))
				MyOptionPanel.initWrongPanel("入住日期与离店日期有误！");
			else if(OperaPojo.isOverlap(roomID, cDate, lDate,billNum)){
				MyOptionPanel.initWrongPanel("该时间段与其他订单的时间有冲突！");
			}
			else{
				if(0==MyOptionPanel.initConfirmPanel("确定修改？")){
					OperaPojo.updateBill(billNum, roomID,this.roomID, cDate, lDate, deposit);
					MyOptionPanel.initSuccessPanel("修改成功！");
					frame.dispose();
					}					
				}
		}else if(e.getSource()==bt2){
			frame.dispose();
		}
	}

}
