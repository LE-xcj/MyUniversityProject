package view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.OperaPojo;
import utils.MyButton;
import utils.MyLabel;
import utils.MyOptionPanel;

public class UsingRoom {
	private JFrame frame;
	private JPanel mainPanel;
	private MyLabel billNum,clientID,clientName,title;
	private MyLabel lb1,lb2;
	private JComboBox<String> box1;
	private MyButton ok,close;
	private final String USING="入住房间";
	private final String BILLNUM="订单号";
	private final String CLIENTID="身份证";
	private final String CLIENTNAM="入住人姓名";
	private String roomID;
	private Point origin;
	private String[] item1,item2;
	
	public UsingRoom(String roomID,String[] item1,String[] item2,String[] item3){
		this.roomID=roomID;
		this.item1=item2;
		this.item2=item3;
		
		frame=new JFrame();
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		String text=USING+roomID;
		title=new MyLabel(new Rectangle(155, 0, 190, 36),text,0, 28);
		mainPanel.add(title);
		
		billNum=new MyLabel("source/file_edit48.png", BILLNUM, new Rectangle(34, 64, 48, 68));
		mainPanel.add(billNum);
		
		clientID=new MyLabel("source/contact_card.png", CLIENTID, new Rectangle(150, 64, 48, 68));
		mainPanel.add(clientID);
		
		clientName=new MyLabel("source/user (2).png",CLIENTNAM,new Rectangle(267, 64, 80, 68));
		mainPanel.add(clientName);
		
		box1=new JComboBox<String>(item1);
		box1.setBounds(20, 145, 75, 21);
		mainPanel.add(box1);
		
		lb1=new MyLabel(1, new Rectangle(155, 145, 75, 21)) ;
		mainPanel.add(lb1);
		
		lb2=new MyLabel(1, new Rectangle(285, 145, 75, 21));
		mainPanel.add(lb2);
		
		ok=new MyButton("source/todolist.png", new Rectangle(400, 120, 67, 59));
		mainPanel.add(ok);
		
		close=new MyButton("source/101.png",new Rectangle(435, 0, 41, 23));
		mainPanel.add(close);
		
		drag();
		initButton();
		setText(box1.getSelectedIndex());
		
		frame.setContentPane(mainPanel);
		frame.setBounds(100, 100, 466, 176);
		frame.setUndecorated(true);
		frame.setVisible(true);		
			
	}
	
	private void initButton() {
		// TODO Auto-generated method stub
		ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				String billNum=box1.getSelectedItem().toString();
				OperaPojo.updateRoomStatus(roomID, billNum);
				MyOptionPanel.initSuccessPanel("成功入住！");
				frame.dispose();
			}
		});
		
		close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				frame.dispose();
			}
		});
		
		box1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
                if(e.getStateChange() == ItemEvent.SELECTED){
                      int index=box1.getSelectedIndex();	//修改后
                      setText(index);
                }
			}
		});
	}

	private void drag() {
		// TODO Auto-generated method stub
		origin = new Point();	//全局的位置变量，用于表示鼠标在窗口上的位置
		
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
                    origin.x = e.getX(); 			 //当鼠标按下的时候获得窗口当前的位置
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
	
	private void setText(int index){
		lb1.setText(item1[index]);
		lb2.setText(item2[index]);
	}

}
