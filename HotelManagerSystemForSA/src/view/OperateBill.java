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
 * ������ͼ
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
	private final static String[] conditions={"������","�ͻ�","������"};
	private final static String[] title={"������","��ס��","���֤","��ס�����","��סʱ��",
							"���ʱ��","����","Ӧ�����","ʵ�����","�������","����ʱ��","������"};
	
	
	public OperateBill(){
		
		menuBar=new JMenuBar();
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		openMenu=new JMenu("��");
		menuBar.add(openMenu);
		
		showMenu=new JMenu("��ʾ");
		menuBar.add(showMenu);
		
		excel=new JMenuItem("����Excel");
		openMenu.add(excel);
		
		refresh=new JMenuItem("ˢ��");
		showMenu.add(refresh);
		
		showAll=new JMenuItem("��ʾȫ��");
		showMenu.add(showAll);
		
		showToday=new JMenuItem("��ʾ����");
		showMenu.add(showToday);
		
		showWeek=new JMenuItem("��һ��");
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
		
		complish=new JMenuItem("����");
		cancel=new JMenuItem("ȡ������");
		update=new JMenuItem("�޸�");
		delete=new JMenuItem("ɾ��");
		pop.add(complish);
		pop.add(update);
		pop.add(delete);
		pop.add(cancel);
		
        // ���� table �Զ�������С 
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.setContentPane(mainPanel);
		this.setBounds(100, 100, 862, 525);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		
	}
	
	public void setTotalPage(int totalPage){
		String page="��"+Integer.toString(totalPage)+"ҳ";
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
			bw.close();		//�ر�д���ļ�����
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
