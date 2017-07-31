package utils;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MyOptionPanel extends JOptionPane{
	public MyOptionPanel(){
		super();
	}
	
	public MyOptionPanel(String tip,int flag){
		switch(flag){
			case 0:
				initWrongPanel(tip);
				break;
			case 1:
				initWarmingPanel(tip);
				break;
			case 2:
				initConfirmPanel(tip);
				break;
			case 3:
				initSuccessPanel(tip);
				break;
				
		}
	}
	
	private static void initWrongPanel(String tip){
		showMessageDialog(null,  tip, "����",JOptionPane.ERROR_MESSAGE);
	}
	
	private static void initWarmingPanel(String tip){
		showMessageDialog(null,  tip, "����",JOptionPane.WARNING_MESSAGE);
	}
	
	public static int initConfirmPanel(String tip){
		return showConfirmDialog(null, tip, "�Ƿ����", JOptionPane.YES_NO_OPTION); 
	}
	
	private static void initSuccessPanel(String tip){
		showMessageDialog(null, tip);
	}
}
