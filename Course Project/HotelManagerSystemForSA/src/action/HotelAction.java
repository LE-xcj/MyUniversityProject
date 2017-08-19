package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import dao.OperaPojo;
import service.Check;
import utils.ContainerForPojo;
import utils.MyOptionPanel;
import view.Hotel;
import view.Profit;
import view.SetRoomFee;

/*
 * �Ƶ������������
 * �������¹��ܣ�
 * 1���Թ���Ա���û������䡢����������ɾ���
 * 2�����ò�ͬ�������͵��շѽ����ǲ��ܶ��Ѿ���ס�ķ����޸��շѽ��
 * 3�����Ը��������ʱ��ν���Ӫҵ���ѯ
 * 4�����ÿһ�����䶼����ʾ��û����ɵ����ж�����Ҫ��Ϣ�������š���ס��ʡ��֤�������ˡ���סʱ��
 */

public class HotelAction implements ActionListener{
	private Hotel hotel;
	private OperaAction operaAction=new OperaAction();
	private final int adminBF=0;		//������־
	private final int clientBF=1;
	private final int roomBF=2;		
	
	private int flag=adminBF;			//��ǰ������־
	private int index=-1;				//��������а�ť���±�
		
	private final static String adminOpt="����Ա";		//��������Ĭ�ϵ�һ��ѡ�����ǡ�����Ա��
	private final static String clientOpt="�ͻ�";
	private final static String roomOpt="����";
	
	private final int firstPage=1;						//�������ĵ�һҳ
	private int lastPage=1;								
	private int currentPage=firstPage;							//��ǰҳ
	
	//״̬���ı�ǩ��ʾ����Ͻǣ�
	private final static String all="ȫ����ʾ";			
	private final static String adminState="����";		
	private final static String clientStatus="��ͨ";
	private final static String roomStatus="����";	
	
	//ɸѡ�ؼ���
	private static String[] conditions1={"ȫ����ʾ","����","������"};		
	private static String[] conditions2={"ȫ����ʾ","��ͨ","VIP"};
	private static String[] conditions3={"ȫ����ʾ","����","��ռ��"};
	private static String condition=all;				//��ǰɸѡ������Ĭ���ǡ�ȫ����ʾ��
	
	public HotelAction(){
		hotel=new Hotel();		//��ʾ����
		hotel.addBox2Items(conditions1);		//����ɸѡ���ѡ����		
		reFresh();					//��ʼ��������Ա������Ϣ������ʾ��һҳ�Ĺ���Ա
		initButton();
	}
		
	public void initButton(){
		hotel.adminB.addActionListener(this);
		hotel.clientB.addActionListener(this);
		hotel.billB.addActionListener(this);
		hotel.add.addActionListener(this);
		hotel.delete.addActionListener(this);
		hotel.update.addActionListener(this);
		hotel.refresh.addActionListener(this);
		hotel.roomB.addActionListener(this);
		hotel.search.addActionListener(this);
		hotel.left.addActionListener(this);
		hotel.right.addActionListener(this);
		hotel.theFirst.addActionListener(this);
		hotel.theLast.addActionListener(this);
		Hotel.comboBox.addActionListener(this);
		hotel.profitB.addActionListener(this);
		hotel.feeSet.addActionListener(this);
		hotel.exitB.addActionListener(this);
		hotelBox2();
		initDynamicButton();
				
	}
	
	//����ɸѡ���ѡ�����״̬��Ȼ�����ѡ������ʾ���������ġ���ť��
	public void hotelBox2(){
		Hotel.comboBox2.addItemListener(new ItemListener() {
			//��ΪCombobox�Լ��������¼��������η�Ӧ��������Ҫ����һ��itemlistener��ר�ż���Combobox��ѡ���¼�	
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
                if(e.getStateChange() == ItemEvent.SELECTED){
                	condition=Hotel.comboBox2.getSelectedItem().toString();  
                	currentPage=firstPage;
                	lastPage=dao.OperaPojo.pageNum(flag, condition);		//����ɸѡ����������
            		hotel.setTotalPage(lastPage);
            		hotel.setCurrentPage(currentPage);
                	initScrollPanelAgain();
                }
			}
		});
	}
	
	//����������еİ�ť��Ӽ�����
	public void initDynamicButton(){
		int length=hotel.buttons.length;
		for(int i=0;i<length;++i){
			hotel.buttons[i].addActionListener(this);
		}
	}
	

	/*
	 * ��Ӧ��ͬ��ť�����
	 * 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//��ȡ��������б�����İ�ť���±꣬���û�а�ť�������ô����-1
		if(flag==adminBF||flag==clientBF||flag==roomBF)
			buutonIndex(e);
		
		//������������û�а�ť���������ô��Ϣ���Ͳ���ʾ�ð�ť����Ϣ
		if(index!=-1){
			if(adminBF==flag)
				hotel.setAdminText(index);
			else if(clientBF==flag)
				hotel.setClientText(index);
			else{
				hotel.clear();		//��ձ���е�������
				hotel.setRoomText(index);
				hotel.addTableRowData(index);		//���¼���������
			}			
		}
		
		if(e!=null){
			if(e.getSource()==hotel.adminB){	
				flag=adminBF;
				hotel.clear();
				hotel.addBox2Items(conditions1);
				reFresh();
			}else if(e.getSource()==hotel.clientB){
				flag=clientBF;
				hotel.clear();
				hotel.addBox2Items(conditions2);
				reFresh();
			}else if(e.getSource()==hotel.roomB){
				flag=roomBF;
				hotel.addBox2Items(conditions3);
				reFresh();
			}else if(e.getSource()==hotel.add){
				switch(flag){					
					case adminBF:				
						operaAction.addOpera(adminBF);
						break;
					case clientBF:
						operaAction.addOpera(clientBF);
						break;
					case roomBF:
						operaAction.addOpera(roomBF);
						break;
				}
			}else if(e.getSource()==hotel.update){
				if(-1==index)
					new MyOptionPanel("��ѡ����Ҫ�޸ĵ�ѡ��", 1);
				else{
					switch(flag){
						case adminBF:							
							operaAction.updateOpera(adminBF, index);
							break;
						case clientBF:
							operaAction.updateOpera(clientBF, index);
							break;
						case roomBF:
							operaAction.updateOpera(roomBF, index);
							break;
					}
					
				}				
			}else if(e.getSource()==hotel.delete){
				if(-1==index)
					new MyOptionPanel("��ѡ����Ҫɾ����ѡ��", 1);
				else{
					if(0==MyOptionPanel.initConfirmPanel("����Ӧ�ļ�¼Ҳ�ᱻɾ���ģ�ȷ��Ҫɾ����")){
						operaAction.deletOpera(flag, index);
						refreshAfterDelete();
					}
				}
			}else if(e.getSource()==hotel.refresh){
				hotel.clear();
				reFresh();
			}else if(e.getSource()==hotel.search){
				searchOpera();	
			}else if(e.getSource()==hotel.exitB){
				if(0==MyOptionPanel.initConfirmPanel("ȷ��Ҫ�˳������Ƶ����ϵͳ��"))
					System.exit(0);
			}else if(e.getSource()==hotel.theFirst){
				if(currentPage==firstPage)
					new MyOptionPanel("�Ѿ��ǵ�һҳ�ˣ�", 1);
				else{
					currentPage=firstPage;
					hotel.setCurrentPage(currentPage);
					initScrollPanelAgain();
				}
			}else if(e.getSource()==hotel.theLast){
				if(currentPage==lastPage)
					new MyOptionPanel("�Ѿ������һҳ�ˣ�", 1);
				else{
					currentPage=lastPage;
					hotel.setCurrentPage(currentPage);
					initScrollPanelAgain();					
				}
			}else if(e.getSource()==hotel.right){
				if(currentPage==lastPage)
					new MyOptionPanel("�Ѿ������һҳ�ˣ�", 1);
				else{
					++currentPage;
					hotel.setCurrentPage(currentPage);
					initScrollPanelAgain();
				}
			}else if(e.getSource()==hotel.left){
				if(currentPage==firstPage)
					new MyOptionPanel("�Ѿ��ǵ�һҳ�ˣ�", 1);
				else{
					--currentPage;
					hotel.setCurrentPage(currentPage);
					initScrollPanelAgain();
				}
			}else if(e.getSource()==hotel.feeSet){
				new SetRoomFee();
			}else if(e.getSource()==hotel.billB){
				new OperateBillAction();
			}else if(e.getSource()==hotel.profitB){
				new Profit();
			}
		}
		
	}
	
	/*
	 * ����
	 * 1��ѡ����
	 * 2�������ѯ����Ĺؼ���Ϣ�����������ݿ��д�����Ӧ��Ϣ
	 * 3����ʾ�����������Ϣ��Ҳ�����޸�
	 */
	private void searchOpera() {
		// TODO Auto-generated method stub
		String option=Hotel.comboBox.getSelectedItem().toString();
		String input=hotel.jt.getText();
		if(Check.checkNull(input))
			new MyOptionPanel("����Ϊ��", 1);
		else{
			switch(option){
			case adminOpt:{
				if(!Check.checkExit(input, adminBF))
					new MyOptionPanel("�������Ա������!", 0);							
				else
					operaAction.showOrChange(input, adminBF);
			}
			break;
			case clientOpt:{
				if(!Check.checkExit(input, clientBF))
					new MyOptionPanel("���û������֤�����ڱ��Ƶ�", 0);
				else
					operaAction.showOrChange(input, clientBF);
			}
			break;
			case roomOpt:{
				if(!Check.checkExit(input, roomBF))
					new MyOptionPanel("�÷���Ų����ڱ��Ƶ�", 0);
				else
					operaAction.showOrChange(input, roomBF);
			}
			break;
			}
		}
	}

	
	/*
	 * ˢ�¡�״̬����Ϣ��
	 * ֻ���ڵ����ˢ�¡���������ť���Ż�ˢ��
	 */
	private void resetStatusPanel(){
		String condition=null;
		if(adminBF==flag)
			condition=adminState;
		else if(clientBF==flag)
			condition=clientStatus;
		else
			condition=roomStatus;
		hotel.initStatusPanel(flag, dao.OperaPojo.countTotal(flag), dao.OperaPojo.validRow(flag, condition));
	}
	
	
	/*
	 * Ѱ�ҹ����������һ����ť������ˣ������ظð�ť���±�
	 * �����±����ã���Ϊ�˸�������±���������ҵ���Ӧ��ʵ�����󣬴Ӷ���ȡ��Ӧ����Ϣ
	 */
	public void buutonIndex(ActionEvent e){
		int length=hotel.buttons.length;
		for(int i=0;i<length;++i){
			if(e.getSource()==hotel.buttons[i]){
				index=i;
				break;
			}
		}
	}
	
	/*
	 * ˢ��֮���������ã����������У�
	 * 1����ǰҳ���¹�1
	 * 2��ˢ�²������ж���ҳ
	 * 3��ˢ�����Ͻǵġ�״̬������Ϣ
	 * 4��ˢ�¹������İ�ť����ȻҲ��Ҫ���¸�ÿһ����ť��Ӽ�������
	 */
	private void reFresh(){
		currentPage=firstPage;
		lastPage=dao.OperaPojo.pageNum(flag);
		hotel.setCurrentPage(currentPage);
		hotel.setTotalPage(lastPage);
		resetStatusPanel();
		initScrollPanelAgain();
	}
	
	//СС�������Ż����������ɾ���ڶ�ҳ�е�Ψһ����ť����ô�ͻ��Զ�refresh�������ˢ�µ�ǰҳ
	private void refreshAfterDelete(){
		int size=0;
		switch(flag){
			case adminBF:
				size=ContainerForPojo.list_admin.size();
			break;
			case clientBF:
				size=ContainerForPojo.list_clent.size();
			break;
			case roomBF:
				size=ContainerForPojo.list_room.size();
			break;
		}
		if(1==size)
			reFresh();
		else{
			resetStatusPanel();
			initScrollPanelAgain();
		}			
	}
	
	
	/*
	 * 1�����ݵ�ǰ��ǩ��flag��ˢ�¹�����尴ť
	 * 			һ�������ݱ�ǩ�����Ӧ�����е�ʵ������
	 * ��		�����������ݿ�����ȡ��¼�����¼���ʵ������
	 * 2�������¸�ÿһ����ť��Ӽ�����
	 * 3����index����Ϊ-1������û��ѡ�й������İ�ť
	 */
	public void initScrollPanelAgain(){
		switch (flag){
			case adminBF:{
				ContainerForPojo.list_admin.clear();	
				OperaPojo.initAdminList(condition, currentPage);
				hotel.initAScrollP(ContainerForPojo.list_admin.size(),adminBF);
			}
			break;
			case clientBF:{
				ContainerForPojo.list_clent.clear();
				OperaPojo.initClientList(condition, currentPage);			
				hotel.initAScrollP(ContainerForPojo.list_clent.size(),clientBF);
			}
			break;
			case roomBF:{
				ContainerForPojo.list_room.clear();
				OperaPojo.initRoomList(condition, currentPage);	
				hotel.initAScrollP(ContainerForPojo.list_room.size(), roomBF);		
			}
			break;
		}
		hotel.initImforPanelA(flag);
		initDynamicButton();
		index=-1;
	}
	
}
