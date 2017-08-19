package utils;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MyButton extends JButton{
	public MyButton(){
		super();
		init();
	}
	
	public MyButton(Rectangle rectangle){
		super();
		this.setBounds(rectangle);
		init();
	}
	
	public MyButton(String text){
		super(text);
		init();
	}
	
	public MyButton(String url,Rectangle rectangle){
		super(new ImageIcon(url));
		this.setBounds(rectangle);
		init();
	}
	
	public MyButton(String url,String text){
		super(new ImageIcon(url));
		this.setText(text);
		set();
	}
	
	public MyButton(String url,String text,Rectangle rectangle){
		super(new ImageIcon(url));
		this.setText(text);
		this.setBounds(rectangle);
	}
	
	public MyButton(String url,String text,int flag){
		super(new ImageIcon(url));
		this.setText(text);
		set();
		init();
	}		
	
	private void set() {
		// TODO Auto-generated method stub
		this.setHorizontalTextPosition(CENTER);
		this.setVerticalTextPosition(BOTTOM);
	}
	public void init(){
		this.setBorder(null);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
	}
}

