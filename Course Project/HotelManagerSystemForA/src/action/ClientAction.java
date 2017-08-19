package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dao.OperaPojo;
import utils.ContainerForPojo;
import utils.MyOptionPanel;
import view.Client;
import view.SUClient;

public class ClientAction implements ActionListener{
	private Client client;
	private int currentPage=1;
	private int totalPage=1;
	private int index=-1;
	private String condition;
	public ClientAction(){
		client=new Client();
		condition=client.box.getSelectedItem().toString();
		initButtonAction();
		setPage();
		initButtons();
		initButtonsAction();
	}
	
	private void initButtonAction() {
		// TODO Auto-generated method stub
		client.close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				client.close();
			}
		});
		
		client.search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				String clientID=client.jtf.getText();
				String[] clientImfor=OperaPojo.clientImfor(clientID);
				if(null==clientImfor)
					MyOptionPanel.initWarmingPanel("并无此客户！");
				else
					new SUClient(clientImfor, false);
			}
		});
		
		client.box.addItemListener(new ItemListener() {
			//因为Combobox对监听到的事件会有两次反应，所以需要创建一个itemlistener，专门监听Combobox的选择事件	
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
                if(e.getStateChange() == ItemEvent.SELECTED){
                	condition=client.box.getSelectedItem().toString();
                	currentPage=1;               	
                	setPage();
                	initButtons();
                	initButtonsAction();
                }
			}
		});
		
		client.left.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(1==currentPage)
					MyOptionPanel.initWarmingPanel("已经是第一页了！");
				else{
					--currentPage;
					client.setCurrentPage(Integer.toString(currentPage));
					initButtons();
					initButtonsAction();
				}
			}
		});
		
		client.right.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(currentPage==totalPage)
					MyOptionPanel.initWarmingPanel("已经是最后一页了！");
				else{
					++currentPage;
					client.setCurrentPage(Integer.toString(currentPage));
					initButtons();
					initButtonsAction();
				}
			}
		});
		
		client.refresh.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				index=-1;
				setPage();
				initButtons();
				initButtonsAction();
			}
		});
		
		client.select.addActionListener(this);
		client.update.addActionListener(this);
		client.delete.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==client.select){
			new SUClient(index, false);
		}else if(e.getSource()==client.update){
			new SUClient(index, true);
		}else if(e.getSource()==client.delete){
			String clientID=ContainerForPojo.client_list.get(index).getClientID();
			if(!OperaPojo.canDeleteClient(clientID))
				MyOptionPanel.initWrongPanel("该客户还有订单未结账！无法删除！");
			else if(0==MyOptionPanel.initConfirmPanel("确定删除？该客户的记录也会被删除")){
				OperaPojo.deleteClient(clientID);
				MyOptionPanel.initSuccessPanel("成功删除！");
			}
		}
		
	}

	private void initButtonsAction() {
		// TODO Auto-generated method stub
		int length=client.buttons.length;
		for(int i=0;i<length;++i){
			client.buttons[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){					
					findIndex(e);
					if(e.getButton()==MouseEvent.BUTTON3){
						client.pop.show(client.buttons[index], e.getX(), e.getY());
					}				
				}
			});
		}
	}

	protected void findIndex(MouseEvent e) {
		// TODO Auto-generated method stub
		int length=client.buttons.length;
		for(int i=0;i<length;++i){
			if(e.getSource()==client.buttons[i]){
				index=i;
				return;
			}
		}
	}

	private void setPage() {
		// TODO Auto-generated method stub
		String cPage=Integer.toString(currentPage);
		totalPage=OperaPojo.pageAmount(condition);
		String tPage=Integer.toString(totalPage);
		client.setCurrentPage(cPage);
		client.setTotalPage(tPage);
	}

	private void initButtons() {
		// TODO Auto-generated method stub
		OperaPojo.initClientList(condition, currentPage);
		client.initScrollPanelButton();
	}

	
}
