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
 * 酒店操作的主界面
 * 包含以下功能：
 * 1、对管理员、用户、房间、订单可以增删查改
 * 2、设置不同房间类型的收费金额，但是不能对已经入住的房间修改收费金额
 * 3、可以根据输入的时间段进行营业额查询
 * 4、点击每一个房间都会显示还没有完成的所有订单主要信息：订单号、入住人省份证、记账人、入住时间
 */

public class HotelAction implements ActionListener{
	private Hotel hotel;
	private OperaAction operaAction=new OperaAction();
	private final int adminBF=0;		//操作标志
	private final int clientBF=1;
	private final int roomBF=2;		
	
	private int flag=adminBF;			//当前操作标志
	private int index=-1;				//滚动面板中按钮的下标
		
	private final static String adminOpt="管理员";		//搜索方向，默认第一个选择方向是“管理员”
	private final static String clientOpt="客户";
	private final static String roomOpt="房间";
	
	private final int firstPage=1;						//滚动面板的第一页
	private int lastPage=1;								
	private int currentPage=firstPage;							//当前页
	
	//状态栏的标签显示语（左上角）
	private final static String all="全部显示";			
	private final static String adminState="可用";		
	private final static String clientStatus="普通";
	private final static String roomStatus="可用";	
	
	//筛选关键字
	private static String[] conditions1={"全部显示","可用","不可用"};		
	private static String[] conditions2={"全部显示","普通","VIP"};
	private static String[] conditions3={"全部显示","可用","已占用"};
	private static String condition=all;				//当前筛选条件，默认是“全部显示”
	
	public HotelAction(){
		hotel=new Hotel();		//显示界面
		hotel.addBox2Items(conditions1);		//设置筛选框的选择项		
		reFresh();					//初始换“管理员”的信息，并显示第一页的管理员
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
	
	//监听筛选框的选择项的状态，然后根据选择项显示符合条件的“按钮”
	public void hotelBox2(){
		Hotel.comboBox2.addItemListener(new ItemListener() {
			//因为Combobox对监听到的事件会有两次反应，所以需要创建一个itemlistener，专门监听Combobox的选择事件	
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
                if(e.getStateChange() == ItemEvent.SELECTED){
                	condition=Hotel.comboBox2.getSelectedItem().toString();  
                	currentPage=firstPage;
                	lastPage=dao.OperaPojo.pageNum(flag, condition);		//满足筛选条件的总数
            		hotel.setTotalPage(lastPage);
            		hotel.setCurrentPage(currentPage);
                	initScrollPanelAgain();
                }
			}
		});
	}
	
	//给滚动面板中的按钮添加监听器
	public void initDynamicButton(){
		int length=hotel.buttons.length;
		for(int i=0;i<length;++i){
			hotel.buttons[i].addActionListener(this);
		}
	}
	

	/*
	 * 响应不同按钮被点击
	 * 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//获取滚动面板中被点击的按钮的下标，如果没有按钮被点击那么返回-1
		if(flag==adminBF||flag==clientBF||flag==roomBF)
			buutonIndex(e);
		
		//如果滚动面板中没有按钮被点击，那么信息面板就不显示该按钮的信息
		if(index!=-1){
			if(adminBF==flag)
				hotel.setAdminText(index);
			else if(clientBF==flag)
				hotel.setClientText(index);
			else{
				hotel.clear();		//清空表格中的数据项
				hotel.setRoomText(index);
				hotel.addTableRowData(index);		//重新加载数据项
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
					new MyOptionPanel("请选择你要修改的选项", 1);
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
					new MyOptionPanel("请选择你要删除的选项", 1);
				else{
					if(0==MyOptionPanel.initConfirmPanel("其相应的记录也会被删除的，确定要删除？")){
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
				if(0==MyOptionPanel.initConfirmPanel("确定要退出仲恺酒店管理系统？"))
					System.exit(0);
			}else if(e.getSource()==hotel.theFirst){
				if(currentPage==firstPage)
					new MyOptionPanel("已经是第一页了！", 1);
				else{
					currentPage=firstPage;
					hotel.setCurrentPage(currentPage);
					initScrollPanelAgain();
				}
			}else if(e.getSource()==hotel.theLast){
				if(currentPage==lastPage)
					new MyOptionPanel("已经是最后一页了！", 1);
				else{
					currentPage=lastPage;
					hotel.setCurrentPage(currentPage);
					initScrollPanelAgain();					
				}
			}else if(e.getSource()==hotel.right){
				if(currentPage==lastPage)
					new MyOptionPanel("已经是最后一页了！", 1);
				else{
					++currentPage;
					hotel.setCurrentPage(currentPage);
					initScrollPanelAgain();
				}
			}else if(e.getSource()==hotel.left){
				if(currentPage==firstPage)
					new MyOptionPanel("已经是第一页了！", 1);
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
	 * 搜索
	 * 1、选择方向
	 * 2、输入查询对象的关键信息，必须是数据库中存在相应信息
	 * 3、显示搜索对象的信息，也可以修改
	 */
	private void searchOpera() {
		// TODO Auto-generated method stub
		String option=Hotel.comboBox.getSelectedItem().toString();
		String input=hotel.jt.getText();
		if(Check.checkNull(input))
			new MyOptionPanel("不能为空", 1);
		else{
			switch(option){
			case adminOpt:{
				if(!Check.checkExit(input, adminBF))
					new MyOptionPanel("这个管理员不存在!", 0);							
				else
					operaAction.showOrChange(input, adminBF);
			}
			break;
			case clientOpt:{
				if(!Check.checkExit(input, clientBF))
					new MyOptionPanel("该用户的身份证不存在本酒店", 0);
				else
					operaAction.showOrChange(input, clientBF);
			}
			break;
			case roomOpt:{
				if(!Check.checkExit(input, roomBF))
					new MyOptionPanel("该房间号不存在本酒店", 0);
				else
					operaAction.showOrChange(input, roomBF);
			}
			break;
			}
		}
	}

	
	/*
	 * 刷新“状态栏信息”
	 * 只有在点击“刷新”，“三大按钮”才会刷新
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
	 * 寻找滚动面板中哪一个按钮被点击了，并返回该按钮的下标
	 * 返回下标作用：是为了根据这个下标从容器中找到对应的实例对象，从而提取相应的信息
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
	 * 刷新之后，重新设置；设置内容有：
	 * 1、当前页重新归1
	 * 2、刷新并设置有多少页
	 * 3、刷新左上角的“状态栏”信息
	 * 4、刷新滚动面板的按钮（当然也需要重新给每一个按钮添加监听器）
	 */
	private void reFresh(){
		currentPage=firstPage;
		lastPage=dao.OperaPojo.pageNum(flag);
		hotel.setCurrentPage(currentPage);
		hotel.setTotalPage(lastPage);
		resetStatusPanel();
		initScrollPanelAgain();
	}
	
	//小小的体验优化，就是如果删除第二页中的唯一个按钮，那么就会自动refresh，否则就刷新当前页
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
	 * 1、根据当前标签（flag）刷新滚动面板按钮
	 * 			一）、根据标签清空相应容器中的实例对象
	 * 、		二）、从数据库中提取记录，重新加载实例对象
	 * 2、并重新给每一个按钮添加监听器
	 * 3、将index设置为-1，代表没有选中滚动面板的按钮
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
