package view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import utils.ContainerForPojo;
import utils.MyButton;
import utils.MyLabel;

public class Client {
	private JFrame frame;
	private JPanel mainPanel,jp;
	private JScrollPane jsp;
	private MyLabel title,totalPage,currentPage;
	public MyButton close,search,left,right,refresh;
	public MyButton[] buttons;
	public JTextField jtf;
	public JComboBox<String> box;
	public JPopupMenu pop;
	public JMenuItem select,delete,update;
	
	private final String TITLE="�ͻ���Ϣ";
	private final String SELECT="�鿴";
	private final String UPDATE="�޸�";
	private final String DELETE="ɾ��";
	private String[] options={"ȫ����ʾ","��ͨ","VIP"};
	
	private final int OPTIONSNUM=3;
	private final int BEGINX=10;
	private final int BEGINY=10;
	private final int GAPX=80;
	private final int GAPY=150;
	private final int ROWNUM=6;
	private final int IconSize=80;
	
	
	private final String MAIL="��";
	private final String MAILURL="source/user_2.png";
	private final String FEMAINLURL="source/user_1.png";
	private Point origin;
	
	public Client(){
		frame=new JFrame();
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		pop=new JPopupMenu();
		select=new JMenuItem(SELECT);
		update=new JMenuItem(UPDATE);
		delete=new JMenuItem(DELETE);
		
		pop.add(select);
		pop.add(update);
		pop.add(delete);
		
		jsp=new JScrollPane();
		jsp.setBounds(10, 50, 514, 291);
		mainPanel.add(jsp);
		
		jp=new JPanel();
		jp.setLayout(null);
		jsp.add(jp);
		
		title=new MyLabel(new Rectangle(184, 0, 112, 30), TITLE, 28);
		mainPanel.add(title);
		
		box=new JComboBox<String>();
		box.setBounds(10, 23, 97, 21);
		addItems();
		mainPanel.add(box);
		
		jtf=new JTextField();
		jtf.setBounds(388, 353, 92, 21);
		mainPanel.add(jtf);
		
		close=new MyButton("source/x.png", new Rectangle(482, 0, 32, 32));
		mainPanel.add(close);
		
		search=new MyButton("source/find (1).png", new Rectangle(493, 351, 24, 24));
		mainPanel.add(search);
		
		left=new MyButton("source/navigation-180.png", new Rectangle(156, 355, 16, 16));
		mainPanel.add(left);
		
		right=new MyButton("source/navigation.png",new Rectangle(249, 355, 16, 16));
		mainPanel.add(right);
		
		refresh=new MyButton("source/128.png",new Rectangle(370, 353,16,16));
		mainPanel.add(refresh);
		
		totalPage=new MyLabel(new Rectangle(10, 356, 54, 18), 18);
		mainPanel.add(totalPage);
		
		currentPage=new MyLabel(1, new Rectangle(207, 356, 24, 15));
		mainPanel.add(currentPage);
		
		drag();
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 530, 380);
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
		
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
	
	public void initScrollPanelButton(){
		ArrayList<pojo.Client> clients=ContainerForPojo.client_list;
		int length=clients.size();
		if(length>ROWNUM*2)
			jp.setPreferredSize(new Dimension(514, 400));
		jsp.setViewportView(jp);
		jp.removeAll();
		buttons=new MyButton[length];
		String url=null;
		int x=BEGINX;
		int y=BEGINY;
		for(int i=0;i<length;++i){
			if(clients.get(i).getSex().equals(MAIL))
				url=MAILURL;
			else
				url=FEMAINLURL;
			buttons[i]=new MyButton(url, clients.get(i).getcName(),0);
			buttons[i].setBounds(x,y,IconSize,IconSize);
			jp.add(buttons[i]);		
			if(0==(i+1)%ROWNUM){
				x=BEGINX;
				y+=GAPY;
			}else{
				x+=GAPX;
			}
		}
			
	}
	
	public void close(){
		frame.dispose();
	}
	
	public void setCurrentPage(String page){
		currentPage.setText(page);
	}
	
	public void setTotalPage(String pages){
		String totalP="��"+pages+"ҳ";
		totalPage.setText(totalP);
	}

	private void addItems() {
		// TODO Auto-generated method stub
		for(int i=0;i<OPTIONSNUM;++i)
			box.addItem(options[i]);
	}
}
