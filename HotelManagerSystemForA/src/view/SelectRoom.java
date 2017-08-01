package view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import action.HotelAction;
import utils.MyButton;

public class SelectRoom {
	private JFrame frame;
	private JPanel mainPanel,jp;
	private JScrollPane jsp;
	private MyButton[] buttons;
	private String[][] roomImfor;
	
	private final int ROOMIDINDEX=0;
	private final int ROOMSTATUSINDEX=1;
	private final int BEIGNX=10;
	private final int BEGINY=10;
	private final int GAPX=80;
	private final int GAPY=100;
	private final int ROWNUM=4;
	private int length=0;
	private int index=-1;
	
	
	
	private final String ABLE="可用";
	private final String BOOKING="预定";
	private final String ABLEURL="source/room/prov.gif";
	private final String BOOKINGURL="source/room/rese.gif";
	private final String USINGURL="source/room/pree.gif";
	
	
	
	public SelectRoom(String[][] roomImfor){
		this.roomImfor=roomImfor;
		this.length=roomImfor[ROOMIDINDEX].length;
		
		frame=new JFrame("合适的房间");
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		jsp=new JScrollPane();
		jsp.setBounds(10, 10, 369, 241);
		mainPanel.add(jsp);
		
		jp=new JPanel();
		jp.setLayout(null);
		jp.setPreferredSize(new Dimension(400,100*(length/ROWNUM)+150));
		
		jsp.add(jp);
		jsp.setViewportView(jp);
		
		initButtons();
		initButtonsAction();
		
		frame.setBounds(100, 100, 400, 300);
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
	}

	private void initButtonsAction() {
		// TODO Auto-generated method stub
		for(int i=0;i<length;++i){
			buttons[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					findIndex(e);
					if(e.getButton()==MouseEvent.BUTTON3){
						String aname=HotelAction.aname;
						new CreateBill(roomImfor[ROOMIDINDEX][index], aname, roomImfor[ROOMSTATUSINDEX][index]);
					}
				}
			});
		}
	}

	protected void findIndex(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<length;++i){
			if(e.getSource()==buttons[i]){
				index=i;
				return;
			}
		}
		index=-1;
	}

	private void initButtons() {
		// TODO Auto-generated method stub
		buttons=new MyButton[length];
		String url=null;
		int x=BEIGNX;
		int y=BEGINY;
		for(int i=0;i<length;++i){
			if(ABLE.equals(roomImfor[ROOMSTATUSINDEX][i]))
				url=ABLEURL;
			else if(BOOKING.equals(roomImfor[ROOMSTATUSINDEX][i]))
				url=BOOKINGURL;
			else
				url=USINGURL;
			buttons[i]=new MyButton(url, roomImfor[ROOMIDINDEX][i], 0);
			buttons[i].setBounds(x,y,65,65);
			jp.add(buttons[i]);
			if(0==(i+1)%ROWNUM){
				x=BEIGNX;
				y+=GAPY;
			}else{
				x+=GAPX;
			}
		}
	}
	
}
