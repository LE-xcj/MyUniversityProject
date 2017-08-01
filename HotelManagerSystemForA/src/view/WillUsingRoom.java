package view;



import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.OperaPojo;
import service.Check;
import utils.MyButton;
import utils.MyLabel;
import utils.MyOptionPanel;

@SuppressWarnings("serial")
public class WillUsingRoom extends JFrame implements ActionListener{

	private JPanel mainPain;
	private String[] columnTitle={"订单号","入住人身份证","入住人","入住时间","入住房间","手机号码"};
	private MyLabel title,lb1,lb2;
	private JTextField jtf;
	private JScrollPane jsp;
	private MyButton search;
	private JTable table;
	private  DefaultTableModel df;
	
	private final String TITLE="订单提醒";
	private final String LB1="离入住还有：";
	private final String DAY="天";
	
	public WillUsingRoom() {
		// TODO Auto-generated constructor stub
		mainPain=new JPanel();
		mainPain.setLayout(null);
		
		title=new MyLabel(new Rectangle(10, 0, 87, 30), 20);
		title.setText(TITLE);
		mainPain.add(title);
		
		lb1=new MyLabel(LB1, new Rectangle(269, 10, 80, 15));
		mainPain.add(lb1);
		
		lb2=new MyLabel(DAY, new Rectangle(436, 10, 22, 15));
		mainPain.add(lb2);
				
		jtf=new JTextField();
		jtf.setBounds(346, 7, 80, 21);
		mainPain.add(jtf);
		
		search=new MyButton("source/find02.gif", new Rectangle(455, 10, 16, 16));
		mainPain.add(search);
		
		jsp=new JScrollPane();
		jsp.setBounds(10, 40, 487, 242);
		mainPain.add(jsp);
		
		table=new JTable();
		jsp.setViewportView(table);
		
		df=new DefaultTableModel(null,columnTitle);
		table.setModel(df);
		
		initButton();
		
		this.setContentPane(mainPain);
		this.setBounds(100, 100, 523, 343);
		this.setVisible(true);
	}
	private void initButton() {
		// TODO Auto-generated method stub
		search.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==search){
			String dayGap=jtf.getText();
			if("".equals(dayGap))
				MyOptionPanel.initWarmingPanel("输入不能为空！");
			else{
				if(Check.dayGapRight(dayGap)){
					OperaPojo.initWillUsingBill(dayGap);
					String[][] data=OperaPojo.getWillUsingBillData();
					setRowData(data);
				}else{
					MyOptionPanel.initWarmingPanel("输入有误！");
				}
			}
		}
	}
	
	private void setRowData(String[][] data){
		clear();
		if(null==data)
			return;
		int length=data.length;
		for(int i=0;i<length;++i){
			df.addRow(data[i]);	
		}
	}
	private void clear() {
		// TODO Auto-generated method stub
		int length=df.getRowCount();
		if(0==length)
			return;
		for(int i=0;i<length;++i)
			df.removeRow(0);		//每当移除一行，其他行的记录的下标就会自动更新
	}
}
