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
	 * 设置不同类型房间的收费标准
	 * 1、元/每晚
	 * 2、只能修改修改可用房间的收费标准
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
		title.setText("收费设置");
		mainPanel.add(title);
		
		label1=new MyLabel(new Rectangle(26, 65, 93, 32), "source/card.png");
		label1.setText("房间类型");
		mainPanel.add(label1);
		label2=new MyLabel(new Rectangle(26, 113, 104, 34), "source/assets-yen.png");
		label2.setText("收费标准");
		mainPanel.add(label2);
		
		bt1=new MyButton("source/apply.png", "确认", new Rectangle(26, 170, 96, 30));
		mainPanel.add(bt1);
		bt2=new MyButton("source/delete_2.png", "关闭", new Rectangle(180, 170, 96, 30));
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
		
		origin = new Point();	//全局的位置变量，用于表示鼠标在窗口上的位置
		
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
                    origin.x = e.getX();  //当鼠标按下的时候获得窗口当前的位置
                    origin.y = e.getY();
            }
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {  //拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
               
                    Point p = getLocation();  //当鼠标拖动时获取窗口当前位置
                    //设置窗口的位置
                    //窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
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
				if(0==MyOptionPanel.initConfirmPanel("确定修改？")){			
					dao.OperaPojo.setFee(type, fee);
					new MyOptionPanel("修改成功！", 3);
				}
			}else
				new MyOptionPanel("设置的金额有误", 0);
				
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
		jtf.setText("/每晚");
		jtf.addFocusListener(new MyFocusListener("/每晚", jtf));

	}
	
}
