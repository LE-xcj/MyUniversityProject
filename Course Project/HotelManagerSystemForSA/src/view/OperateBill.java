package view;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import utils.MyButton;
import utils.MyLabel;

/*
 * 订单视图
 * 
 */

public class OperateBill extends JFrame{
	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JScrollPane scrollPane;
	private MyLabel lb1,lb2;
	public JTextField textField;
	public JMenu openMenu,showMenu;
	public JMenuItem excel,showAll,showToday,showWeek,refresh,update,delete,complish,cancel;
	public JTable table;
	public MyButton search,left,right;
	public JComboBox comboBox;
	public DefaultTableModel df;
	public JPopupMenu pop;
	private final static String[] conditions={"订单号","客户","记账人"};
	private final static String[] title={"订单号","入住人","身份证","入住房间号","入住时间",
							"离店时间","订金","应付金额","实付金额","订单情况","开单时间","记账人"};
	
	
	public OperateBill(){
		
		menuBar=new JMenuBar();
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		openMenu=new JMenu("打开");
		menuBar.add(openMenu);
		
		showMenu=new JMenu("显示");
		menuBar.add(showMenu);
		
		excel=new JMenuItem("导出Excel");
		openMenu.add(excel);
		
		refresh=new JMenuItem("刷新");
		showMenu.add(refresh);
		
		showAll=new JMenuItem("显示全部");
		showMenu.add(showAll);
		
		showToday=new JMenuItem("显示今天");
		showMenu.add(showToday);
		
		showWeek=new JMenuItem("近一周");
		showMenu.add(showWeek);
		
		scrollPane=new JScrollPane();
		scrollPane.setBounds(0, 0, 846, 416);
		mainPanel.add(scrollPane);
		
		table=new JTable();
		table.setRowHeight(40);
		scrollPane.setViewportView(table);
		
		df=new DefaultTableModel(null, title);
		table.setModel(df);
		
		textField=new JTextField();
		textField.setBounds(584, 426, 187, 32);
		mainPanel.add(textField);
		
		search=new MyButton("source/print-preview.png", new Rectangle(780, 426, 32, 32));
		mainPanel.add(search);
		
		comboBox=new JComboBox();
		comboBox.setBounds(503, 429, 71, 25);
		setItem();
		mainPanel.add(comboBox);
		
		lb1=new MyLabel(new Rectangle(10, 430, 53, 15), 18);
		mainPanel.add(lb1);
		
		lb2=new MyLabel(new Rectangle(325, 426, 18, 23), 20);
		lb2.setForeground(Color.red	);
		mainPanel.add(lb2);
		
		left=new MyButton("source/left2.gif", new Rectangle(270, 426, 24, 24));
		mainPanel.add(left);
		
		right=new MyButton("source/right2.gif", new Rectangle(368, 426, 24, 24));
		mainPanel.add(right);
		
		pop=new JPopupMenu();
		
		complish=new JMenuItem("结账");
		cancel=new JMenuItem("取消订单");
		update=new JMenuItem("修改");
		delete=new JMenuItem("删除");
		pop.add(complish);
		pop.add(update);
		pop.add(delete);
		pop.add(cancel);
		
        // 不让 table 自动调整大小 
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.setContentPane(mainPanel);
		this.setBounds(100, 100, 862, 525);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		
	}
	
	public void setTotalPage(int totalPage){
		String page="共"+Integer.toString(totalPage)+"页";
		lb1.setText(page);
	}
	
	public void setCurrentPage(int currentPage){
		String page=Integer.toString(currentPage);
		lb2.setText(page);
	}
	
	public void setItem(){
		int length=conditions.length;
		for(int i=0;i<length;++i)
			comboBox.addItem(conditions[i]);
	}
	
	public void exportExcel(String fileUrl) throws IOException{
		File file=new File(fileUrl);
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		int col=df.getColumnCount();
		for(int i=0;i<col;++i){
			bw.write(df.getColumnName(i).toString());
			bw.write("\t");
		}
		bw.newLine();
		int[] rows=table.getSelectedRows();
		int row=rows.length;
		if(0==row){
			bw.close();		//关闭写入文件操作
			return;
		}
		for(int i=0;i<row;++i){
			for(int j=0;j<col;++j){			
				bw.write(df.getValueAt(rows[i], j).toString());
				bw.write("\t");
			}
			bw.newLine();
		}
		bw.close();
	}
	
}
