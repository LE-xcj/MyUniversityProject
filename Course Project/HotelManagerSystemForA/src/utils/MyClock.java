package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MyClock extends JTextField implements ActionListener{
	private final static SimpleDateFormat df =new SimpleDateFormat("yyyy年 MM月 dd日 hh时 mm分 ss秒");
	private Timer t;
	Calendar c;

	public MyClock(){
		super();
		t=new Timer(1000, this);
		this.setBorder(null);
		this.setOpaque(false);
		this.addActionListener(this);
		t.start();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		c=Calendar.getInstance();
		String time=df.format(c.getTime());
		this.setText(time);
	}

}
