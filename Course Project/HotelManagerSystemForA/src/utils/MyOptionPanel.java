package utils;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MyOptionPanel extends JOptionPane{
	
	public static void initWrongPanel(String tip){
		showMessageDialog(null,  tip, "����",JOptionPane.ERROR_MESSAGE);
	}
	
	public static void initWarmingPanel(String tip){
		showMessageDialog(null,  tip, "����",JOptionPane.WARNING_MESSAGE);
	}
	
	public static int initConfirmPanel(String tip){
		return showConfirmDialog(null, tip, "�Ƿ����", JOptionPane.YES_NO_OPTION); 
	}
	
	public static void initSuccessPanel(String tip){
		showMessageDialog(null, tip);
	}
	
}
