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

import service.Check;
import utils.MyButton;
import utils.MyFocusListener;
import utils.MyLabel;
import utils.MyOptionPanel;

	/*
	 * ���ò�ͬ���ͷ�����շѱ�׼
	 * 1��Ԫ/ÿ��
	 * 2��ֻ���޸��޸Ŀ��÷�����շѱ�׼
	 */

@SuppressWarnings("serial")
public class SetRoomFee extends JFrame implements ActionListener{
	private JPanel mainPanel;
	private JTextField jtf;
	private JComboBox<String> box;
	private MyLabel title,label1,label2;
	private MyButton bt1,bt2;
	private Point origin;
	
	
	public SetRoomFee(){

		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		title=new MyLabel(new Rectangle(106, 10, 95, 27), 22);
		title.setText("�շ�����");
		mainPanel.add(title);
		
		label1=new MyLabel(new Rectangle(26, 65, 93, 32), "source/card.png");
		label1.setText("��������");
		mainPanel.add(label1);
		label2=new MyLabel(new Rectangle(26, 113, 104, 34), "source/assets-yen.png");
		label2.setText("�շѱ�׼");
		mainPanel.add(label2);
		
		bt1=new MyButton("source/apply.png", "ȷ��", new Rectangle(26, 170, 96, 30));
		mainPanel.add(bt1);
		bt2=new MyButton("source/delete_2.png", "�ر�", new Rectangle(180, 170, 96, 30));
		mainPanel.add(bt2);
		
		jtf=new JTextField();
		jtf.setBounds(new Rectangle(160, 117, 114, 28));
		mainPanel.add(jtf);
		
		box=new JComboBox<String>();
		box.setBounds(160, 70, 114, 21);
		mainPanel.add(box);
	
		init();
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		
		origin = new Point();	//ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
		
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  //���£�mousePressed ���ǵ����������걻����û��̧��
                    origin.x = e.getX();  //����갴�µ�ʱ���ô��ڵ�ǰ��λ��
                    origin.y = e.getY();
            }
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {  //�϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
               
                    Point p = getLocation();  //������϶�ʱ��ȡ���ڵ�ǰλ��
                    //���ô��ڵ�λ��
                    //���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
                    setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
        });

        
		this.setContentPane(mainPanel);
		this.setBounds(100, 100, 300, 215);
		this.setUndecorated(true);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt1){

			String fee=jtf.getText();
			if(Check.isNumber(fee)){
				String type=box.getSelectedItem().toString();
				if(0==MyOptionPanel.initConfirmPanel("ȷ���޸ģ�")){			
					dao.OperaPojo.setFee(type, fee);
					new MyOptionPanel("�޸ĳɹ���", 3);
				}
			}else
				new MyOptionPanel("���õĽ������", 0);
				
		}else if(e.getSource()==bt2){
			this.dispose();
		}
	}
	
	public void init(){	
		String[] type=dao.OperaPojo.roomType();
		int length=type.length;
		
		for(int i=0;i<length;++i)
			box.addItem(type[i]);
				
		box.setFocusable(true);
		jtf.setText("/ÿ��");
		jtf.addFocusListener(new MyFocusListener("/ÿ��", jtf));

	}
	
}
