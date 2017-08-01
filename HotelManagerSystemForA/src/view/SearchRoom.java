package view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.OperaPojo;
import service.Check;
import utils.MyButton;
import utils.MyLabel;
import utils.MyOptionPanel;

public class SearchRoom {
	private final String URL1="source/x.png";
	private final String URL2="source/x (1).png";
	private ImageIcon img1,img2;
	
	
	private JFrame frame;
	private JPanel mainPanel;
	private JTextField jtf1,jtf2;
	private MyLabel title,from ,to,roomType;
	private JComboBox<String> box;
	private MyButton search,close;
	private Point origin;
	
	private final String ROOMTYPE="房间类型：";
	private final String SEARCHROOM="房间搜索";
	private final String FROM="从";
	private final String TO="到";
	
	
	public SearchRoom(){
		img1=new ImageIcon(URL1);
		img2=new ImageIcon(URL2);
		
		 frame=new JFrame();
		 
		 mainPanel=new JPanel();
		 mainPanel.setLayout(null);
		 
		 title=new MyLabel(new Rectangle(57, 0, 100, 35), 25);
		 title.setText(SEARCHROOM);
		 mainPanel.add(title);
		 
		 roomType=new MyLabel(ROOMTYPE, new Rectangle(10, 67, 70, 15));
		 mainPanel.add(roomType);
		 
		 box=new JComboBox<String>();
		 box.setBounds(101, 64, 93, 21);
		 mainPanel.add(box);
		 
		 from=new MyLabel(FROM, new Rectangle(29, 113, 22, 15));
		 mainPanel.add(from);
		 
		 to=new MyLabel(TO,new Rectangle(29, 160, 22, 15));
		 mainPanel.add(to);
		 
		 jtf1=new JTextField();
		 jtf1.setBounds(101, 110, 93, 21);
		 mainPanel.add(jtf1);
		 
		 jtf2=new JTextField();
		 jtf2.setBounds(101, 157, 93, 21);
		 mainPanel.add(jtf2);
		 
		 search=new MyButton("source/ys.png", new Rectangle(170, 200, 66, 43));
		 mainPanel.add(search);
		 
		 close=new MyButton(URL1, new Rectangle(210, 0,32, 32));
		 mainPanel.add(close);
		 
		 drag();
		 setBox();
		 initButtonAction();
		 frame.setBounds(100, 100, 240, 250);
		 frame.setContentPane(mainPanel);
		 frame.setUndecorated(true);
		 frame.setVisible(true);
	}

	private void initButtonAction() {
		// TODO Auto-generated method stub
		close.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				setImg2();
			}
			
			public void mouseExited(MouseEvent e){
				
				setImg1();
			}
			
			public void mouseClicked(MouseEvent e){
				frame.dispose();
			}
		});
		
		search.addMouseListener(new MouseAdapter() {		
			public void mouseClicked(MouseEvent e){
				String type=box.getSelectedItem().toString();
				String cDate=jtf1.getText();
				String lDate=jtf2.getText();
				if(!Check.validDate(cDate))
					MyOptionPanel.initWrongPanel("入住日期有误");
				else if(!Check.validDate(lDate))
					MyOptionPanel.initWrongPanel("离店日期有误");
				else if(!Check.dateIsRight(cDate, lDate))
					MyOptionPanel.initWrongPanel("入住日期与离店日期顺序不当！");
				else{
					String[][] roomImfor=OperaPojo.suitableRoom(type, cDate, lDate);
					if(null==roomImfor)
						MyOptionPanel.initWarmingPanel("没有适合的房间！");
					else
						new SelectRoom(roomImfor);
				}
					
			}
		});
		
	}

	protected void setImg1() {
		// TODO Auto-generated method stub
		close.setIcon(img1);
	}

	protected void setImg2() {
		// TODO Auto-generated method stub
		close.setIcon(img2);
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
		
	private void setBox(){
		String[] roomType=OperaPojo.roomType();
		if(null==roomType)
			return;
		int length=roomType.length;
		for(int i=0;i<length;++i)
			box.addItem(roomType[i]);
	}
		
}
