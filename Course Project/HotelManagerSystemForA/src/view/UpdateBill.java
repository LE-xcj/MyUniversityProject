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
 * �޸ĵ����ݣ����š���ס���ڡ�������ڡ�����
 * 1��ֻ��ʾ�Ѿ����ڶ��Ҳ��ǽ��õķ���
 * 2�������ס��������������Ƿ����������������ںϷ�����ס���� <= ������ڣ�
 * 3���Ƿ���������������ס���ں���������г�ͻ
 * 			һ���������÷�������δ���˵��˵�����ס���ں��������
 * 			�������Ƚϸ��ĵ����������������������Ƿ�����ص�
 * 			���������� ������������ס���� <=  ���ĵ���ס����  <= �����������������  ���ص���
 * 					������������ס����<=  ���ĵ��������   <= �����������������  ���ص���
 * 					���ĵ�����<=������������ס����  ��  ����������������� <= ���ĵ��������
 * �����ص����ǲ����ϵ�
 * 4�������Ƿ�Ϸ�
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
		lb1.setText("����ţ�");
		mainPanel.add(lb1);
		
		box=new JComboBox<String>();
		box.setBounds(107, 79, 107, 21);
		mainPanel.add(box);
		
		lb2=new MyLabel(new Rectangle(256, 82, 95, 15), "source/u04.gif");
		lb2.setText("����:");
		mainPanel.add(lb2);
		
		jtf1=new JTextField();
		jtf1.setBounds(338, 79, 107, 21);
		mainPanel.add(jtf1);		
		
		lb3=new MyLabel("��סʱ��:",new Rectangle(26, 126, 95, 15));
		mainPanel.add(lb3);
		
		jtf2=new JTextField();
		jtf2.setBounds(107, 123, 107, 21);
		mainPanel.add(jtf2);
		
		lb4=new MyLabel("���ʱ��:",new Rectangle(256, 126, 95, 15));
		mainPanel.add(lb4);
		
		jtf3=new JTextField();
		jtf3.setBounds(338, 123, 107, 21);
		mainPanel.add(jtf3);
		
		bt1=new MyButton("source/apply.png", "ȷ��",new Rectangle(80, 176, 96, 30));
		mainPanel.add(bt1);
		
		bt2=new MyButton("source/delete_2.png", "ȡ��",new Rectangle(304, 176,96, 30));
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
		String str="�޸� "+billNum+" ����";
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
                public void mousePressed(MouseEvent e) {  //���£�mousePressed ���ǵ����������걻����û��̧��
                        origin.x = e.getX();  //����갴�µ�ʱ���ô��ڵ�ǰ��λ��
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt1){
			String roomID=box.getSelectedItem().toString();
			String deposit=jtf1.getText();
			String cDate=jtf2.getText();
			String lDate=jtf3.getText();
			if(!Check.isMoney(deposit))
				MyOptionPanel.initWrongPanel("����Ľ��Υ����");
			else if(!Check.validDate(cDate))
				MyOptionPanel.initWrongPanel("��ס��������");
			else if(!Check.validDate(lDate))
				 MyOptionPanel.initWrongPanel("�����������"); 
			else if(!Check.dateIsRight(cDate, lDate))
				MyOptionPanel.initWrongPanel("��ס�����������������");
			else if(OperaPojo.isOverlap(roomID, cDate, lDate,billNum)){
				MyOptionPanel.initWrongPanel("��ʱ���������������ʱ���г�ͻ��");
			}
			else{
				if(0==MyOptionPanel.initConfirmPanel("ȷ���޸ģ�")){
					OperaPojo.updateBill(billNum, roomID,this.roomID, cDate, lDate, deposit);
					MyOptionPanel.initSuccessPanel("�޸ĳɹ���");
					frame.dispose();
					}					
				}
		}else if(e.getSource()==bt2){
			frame.dispose();
		}
	}

}
