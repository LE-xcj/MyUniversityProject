package action;


import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import dao.OperaPojo;
import service.Check;
import utils.ContainerForPojo;
import utils.MyOptionPanel;
import view.ComplishBill;
import view.OperateBill;
import view.UpdateBill;

public class OperateBillAction implements ActionListener{
	private OperateBill operateB;
	private final static String ALL="ȫ����ʾ";
	private final static String TODAY="����";
	private final static String WEEK="��һ��";
	private final static String BILLNUM="������";
	private final static String CLIENTID="�ͻ�";
	private final static String ADMIN="������";
	private final static String CANCEL="ȡ��";
	private final static String UNCOMPLISH="δ����";
	private String constrain;
	private String billNum;
	private String condition;
	private int totalPage;
	private int currentPage;
	private int row;
	private final int FIRST,STATUSINDEX,CDATEINDEX,ROOMIDINDEX,LDATEINDEX,DEPOSITINDEX;
	private final int CLIENTNAMEINDEX,CLIENTIDINDEX,TOTALFEEINDEX;
	{
		constrain=null;
		billNum=null;
		row=0;
		condition=ALL;
		currentPage=1;
		totalPage=1;
		FIRST=0;
		CLIENTNAMEINDEX=1;
		CLIENTIDINDEX=2;
		ROOMIDINDEX=3;
		CDATEINDEX=4;
		LDATEINDEX=5;
		DEPOSITINDEX=6;
		TOTALFEEINDEX=7;
		STATUSINDEX=9;
	}
	public OperateBillAction(){
		operateB=new OperateBill();
		initAction();
		selectInitWay();
	}
	
	private void initAction(){
		
		operateB.showAll.addActionListener(this);
		operateB.showToday.addActionListener(this);
		operateB.refresh.addActionListener(this);
		operateB.showWeek.addActionListener(this);
		operateB.search.addActionListener(this);
		operateB.comboBox.addActionListener(this);
		operateB.update.addActionListener(this);
		operateB.delete.addActionListener(this);
		operateB.complish.addActionListener(this);
		operateB.cancel.addActionListener(this);
		operateB.excel.addActionListener(this);
		
		operateB.left.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==MouseEvent.BUTTON1){
					if(currentPage==1)
						MyOptionPanel.initWarmingPanel("���Ѿ��ǵ�һҳ�ˣ�");
					else{
						--currentPage;
						selectInitWay();
					}
				}
			}
		});
		
		operateB.right.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==MouseEvent.BUTTON1){
					if(currentPage==totalPage)
						MyOptionPanel.initWarmingPanel("���Ѿ������һҳ�ˣ�");
					else{
						++currentPage;
						selectInitWay();
					}
				}
			}
		});
		
		
		
		operateB.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==MouseEvent.BUTTON3){
					operateB.pop.show(operateB.table, e.getX(), e.getY());
				}
			}
		});

	}
	
	private void selectInitWay(){
		clear();
		operateB.setCurrentPage(currentPage);
		switch(condition){
			case ALL:
			case TODAY:
			case WEEK:{
				totalPage=OperaPojo.pageOfBill(condition);
				operateB.setTotalPage(totalPage);
				OperaPojo.initBillVector(condition, currentPage);			
			}
			break;
			case BILLNUM:
			case CLIENTID:
			case ADMIN:{	
				totalPage=OperaPojo.pageOfBill(condition,constrain);
				operateB.setTotalPage(totalPage);
				OperaPojo.initBillVector(condition, constrain,currentPage);
			}
			break;
		}
		setRowData();
	}
	
	private void clear() {
		// TODO Auto-generated method stub
		int length=operateB.df.getRowCount();
		for(int i=0;i<length;++i)
			operateB.df.removeRow(0);
		ContainerForPojo.bill_vector.clear();
		
	}

	public void setRowData(){
		String[][] rowData=OperaPojo.tableData();
		int length=ContainerForPojo.bill_vector.size();
		for(int i=0;i<length;++i)
			operateB.df.addRow(rowData[i]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e!=null){
			if(e.getSource()==operateB.update){
				row=operateB.table.getSelectedRow();
				String status=operateB.table.getValueAt(row, STATUSINDEX).toString();
				if(Check.canCancleOrComplish(status)){
					billNum=operateB.table.getValueAt(row, FIRST).toString();
					if(OperaPojo.canComplish(billNum))
						MyOptionPanel.initWarmingPanel("�ö����Ŀͻ��Ѿ���ס�ˣ��޷��޸ģ�");
					else{
						String roomID=operateB.table.getValueAt(row,ROOMIDINDEX).toString();
						String cDate=operateB.table.getValueAt(row,CDATEINDEX).toString();
						String lDate=operateB.table.getValueAt(row,LDATEINDEX).toString();
						String deposit=operateB.table.getValueAt(row,DEPOSITINDEX).toString();
						new UpdateBill(billNum, roomID, cDate, lDate, deposit);
					}
				}else{
					MyOptionPanel.initWrongPanel("����ʧ�ܣ�");
					return;
				}					
			}else if(e.getSource()==operateB.delete){
				row=operateB.table.getSelectedRow();
				billNum=operateB.table.getValueAt(row, FIRST).toString();
				String status=operateB.table.getValueAt(row, STATUSINDEX).toString();
				if(UNCOMPLISH.equals(status)){
					MyOptionPanel.initWarmingPanel("�ö�����״̬���޷�ɾ����");
					return;
				}
				if(0==MyOptionPanel.initConfirmPanel("ȷ��ɾ����")){
					if(Check.canDeleteBill(status)){						
						OperaPojo.deleteBill(billNum);
						MyOptionPanel.initSuccessPanel("ɾ���ɹ���");					
					}else{
						MyOptionPanel.initWrongPanel("�޷�ɾ�����ö���δ���˻�ȡ��");
						return;
					}
				}
			}else if(e.getSource()==operateB.complish){
				row=operateB.table.getSelectedRow();
				String status=operateB.table.getValueAt(row, STATUSINDEX).toString();
				if(Check.canCancleOrComplish(status)){
					billNum=operateB.table.getValueAt(row, FIRST).toString();
					if(OperaPojo.canComplish(billNum)){
						String cName=operateB.table.getValueAt(row, CLIENTNAMEINDEX).toString();
						String clientID=operateB.table.getValueAt(row, CLIENTIDINDEX).toString();
						String roomID=operateB.table.getValueAt(row, ROOMIDINDEX).toString();
						String deposit=operateB.table.getValueAt(row, DEPOSITINDEX).toString();
						String totalFee=operateB.table.getValueAt(row, TOTALFEEINDEX).toString();
						new ComplishBill(billNum, cName, clientID, roomID, deposit, totalFee);
						return;
					}
					else
						MyOptionPanel.initWarmingPanel("�ö����Ŀͻ���δ��ס���޷����ˣ�");
				}else{
					MyOptionPanel.initWrongPanel("����ʧ�ܣ�");	
					return;
				}
					
			}else if(e.getSource()==operateB.cancel){
				row=operateB.table.getSelectedRow();
				String status=operateB.table.getValueAt(row, STATUSINDEX).toString();
				if(Check.canCancleOrComplish(status)){
					billNum=operateB.table.getValueAt(row, FIRST).toString();					
					if(0==MyOptionPanel.initConfirmPanel("ȷ���޸ģ�")){					
						OperaPojo.updateBillStatus(billNum, CANCEL);
						MyOptionPanel.initWarmingPanel("�޸ĳɹ���");
					}else
						return;
				}else{
					MyOptionPanel.initWrongPanel("����ʧ�ܣ�");
					return;
				}
			}else if(e.getSource()==operateB.search){
				condition=operateB.comboBox.getSelectedItem().toString();
				constrain=operateB.textField.getText();
			}else if(e.getSource()==operateB.showAll){
				condition=ALL;
			}else if(e.getSource()==operateB.showToday){
				condition=TODAY;
			}else if(e.getSource()==operateB.showWeek){
				condition=WEEK;
			}else if(e.getSource()==operateB.refresh){
				//
			}else if(e.getSource()==operateB.excel){
				FileDialog fd=new FileDialog(new Frame(),"����Ecel",FileDialog.SAVE);
				fd.setLocation(400,250);
				fd.setVisible(true);
				String fileUrl=fd.getDirectory()+fd.getFile()+".xls";
				try {
					operateB.exportExcel(fileUrl);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					MyOptionPanel.initWrongPanel("����ʧ�ܣ�");
				}
			}
		}
		currentPage=1;
		selectInitWay();
	}
	
	
}
