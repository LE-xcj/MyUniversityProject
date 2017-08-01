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
	
	private final String ROOMTYPE="�������ͣ�";
	private final String SEARCHROOM="��������";
	private final String FROM="��";
	private final String TO="��";
	
	
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
					MyOptionPanel.initWrongPanel("��ס��������");
				else if(!Check.validDate(lDate))
					MyOptionPanel.initWrongPanel("�����������");
				else if(!Check.dateIsRight(cDate, lDate))
					MyOptionPanel.initWrongPanel("��ס�������������˳�򲻵���");
				else{
					String[][] roomImfor=OperaPojo.suitableRoom(type, cDate, lDate);
					if(null==roomImfor)
						MyOptionPanel.initWarmingPanel("û���ʺϵķ��䣡");
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
	origin = new Point();	//ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
		
    frame.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {  //���£�mousePressed ���ǵ����������걻����û��̧��
                origin.x = e.getX(); 			 //����갴�µ�ʱ���ô��ڵ�ǰ��λ��
                    origin.y = e.getY();
          	}
        });
        
    frame.addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseDragged(MouseEvent e) {  //�϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
               
                Point p = frame.getLocation();  //������϶�ʱ��ȡ���ڵ�ǰλ��
                //���ô��ڵ�λ��
                //���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
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
